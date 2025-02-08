package me.zaksen.skillify_core.api.data.loot.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.Location
import org.bukkit.entity.Entity

@Serializable
@SerialName("LootTableEntry")
abstract class LootTableEntry {
    abstract fun onSpawn(location: Location): Entity
}
