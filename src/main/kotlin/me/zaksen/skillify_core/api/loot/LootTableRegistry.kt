package me.zaksen.skillify_core.api.loot

import me.zaksen.skillify_core.api.data.loot.LootTable
import me.zaksen.skillify_core.api.subplugin.SubPlugin
import org.bukkit.NamespacedKey

class LootTableRegistry(
    private val subPlugins: Set<SubPlugin>
) {
    private val registeredLootTables: MutableMap<NamespacedKey, LootTable> = mutableMapOf()

    fun registerLootTable(key: NamespacedKey, table: LootTable) {
        registeredLootTables[key] = table
    }

    fun getTable(key: NamespacedKey): LootTable? {
        return registeredLootTables[key]
    }

    fun reloadRegistry() {
        registeredLootTables.clear()
        subPlugins.forEach {
            it.loadLootTables(this)
        }
    }
}