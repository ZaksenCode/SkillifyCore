package me.zaksen.skillify_core

import me.zaksen.skillify_core.api.config.loadConfig
import me.zaksen.skillify_core.api.item.CustomItemRegistry
import me.zaksen.skillify_core.api.loot.LootTableRegistry
import me.zaksen.skillify_core.config.CoreConfig
import me.zaksen.skillify_core.database.PlayerProfileRepository
import me.zaksen.skillify_core.event.PlayerListener
import me.zaksen.skillify_core.api.recipe.RecipeRegistry
import me.zaksen.skillify_core.api.subplugin.SubPlugin
import me.zaksen.skillify_core.command.CustomItemsCommand
import me.zaksen.skillify_core.command.SkillifyReloadCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File;

class SkillifyCore : JavaPlugin() {

    private val subPlugins: MutableSet<SubPlugin> = mutableSetOf()

    private val logger: Logger = LoggerFactory.getLogger("SkillifyCore")
    private lateinit var coreConfig: CoreConfig
    lateinit var playersRepository: PlayerProfileRepository

    private val keys: SkillifyKeys = SkillifyKeys(this)

    private val recipeRegistry: RecipeRegistry = RecipeRegistry(subPlugins)
    private val lootTableRegistry: LootTableRegistry = LootTableRegistry(subPlugins)
    private val itemRegistry: CustomItemRegistry = CustomItemRegistry(subPlugins, keys.itemId)

    override fun onLoad() {
        INSTANCE = this
    }

    override fun onEnable() {
        coreConfig = loadConfig(File(dataFolder, "config.yml"))

        subPlugins.forEach {
            it.loadLootTables(lootTableRegistry)
            it.loadItems(itemRegistry, keys.itemId)
            it.loadRecipes(recipeRegistry)
        }

        registerCommands()

        playersRepository = PlayerProfileRepository(
            logger,
            coreConfig.mysql.host,
            coreConfig.mysql.port,
            coreConfig.mysql.base,
            coreConfig.mysql.table,
            coreConfig.mysql.user,
            coreConfig.mysql.pass
        )

        Bukkit.getPluginManager().registerEvents(PlayerListener(playersRepository, itemRegistry, recipeRegistry), this)
    }

    private fun registerCommands() {
        getCommand("custom_items").let {
            val processor = CustomItemsCommand(itemRegistry)
            val cmd = checkNotNull(it)
            cmd.setExecutor(processor)
            cmd.tabCompleter = processor
        }
        getCommand("skillify_reload").let {
            val processor = SkillifyReloadCommand(this)
            val cmd = checkNotNull(it)
            cmd.setExecutor(processor)
        }
    }

    fun registerSubPlugin(plugin: SubPlugin) {
        subPlugins.add(plugin)
        logger.debug("Registered new sub plugin (${plugin.getName()})!")
    }

    fun reload() {
        lootTableRegistry.reloadRegistry()
        itemRegistry.reloadRegistry()
        recipeRegistry.reloadRegistry()
    }

    override fun onDisable() {

    }

    companion object {
        private lateinit var INSTANCE: SkillifyCore

        /**
         * Returns Instance to an object of class SkillifyCore
         */
        fun getCore(): SkillifyCore {
            return INSTANCE
        }
    }
}
