package me.zaksen.skillify_core

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class SkillifyKeys(
    plugin: JavaPlugin
) {
    val itemId: NamespacedKey = NamespacedKey(plugin, "item_id")
}