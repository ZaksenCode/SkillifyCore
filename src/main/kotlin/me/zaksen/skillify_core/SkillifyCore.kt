package me.zaksen.skillify_core

import me.zaksen.skillify_core.api.config.loadConfig
import me.zaksen.skillify_core.config.CoreConfig
import me.zaksen.skillify_core.database.PlayerProfileRepository
import me.zaksen.skillify_core.event.PlayerListener
import me.zaksen.skillify_core.api.recipe.RecipeRegistry
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File;

class SkillifyCore : JavaPlugin() {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var coreConfig: CoreConfig
    lateinit var playersRepository: PlayerProfileRepository
    val defaultRecipeRegistry: RecipeRegistry = RecipeRegistry()

    override fun onEnable() {
        INSTANCE = this
        coreConfig = loadConfig(File(dataFolder, "config.yml"))

        playersRepository = PlayerProfileRepository(
            logger,
            coreConfig.mysql.host,
            coreConfig.mysql.port,
            coreConfig.mysql.base,
            coreConfig.mysql.table,
            coreConfig.mysql.user,
            coreConfig.mysql.pass
        )

        Bukkit.getPluginManager().registerEvents(PlayerListener(playersRepository), this)
    }

    override fun onDisable() {

    }

    companion object {
        private lateinit var INSTANCE: SkillifyCore

        fun getCore(): SkillifyCore {
            return INSTANCE
        }
    }
}
