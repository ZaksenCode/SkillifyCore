package me.zaksen.skillify_core

import me.zaksen.skillify_core.api.config.loadConfig
import me.zaksen.skillify_core.config.CoreConfig
import me.zaksen.skillify_core.database.PlayerProfileRepository
import me.zaksen.skillify_core.event.PlayerListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File;

class SkillifyCore : JavaPlugin() {

    private lateinit var coreConfig: CoreConfig
    private lateinit var playersRepository: PlayerProfileRepository

    override fun onEnable() {
        coreConfig = loadConfig(File(dataFolder, "config.yml"))

        playersRepository = PlayerProfileRepository(
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
}
