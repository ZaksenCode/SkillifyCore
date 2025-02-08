package me.zaksen.skillify_core.api.data.loot.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.data.entity.LivingEntitySnapshot
import org.bukkit.Location
import org.bukkit.entity.Entity

@Serializable
@SerialName("living_entity")
data class LivingEntityEntry(
    @SerialName("value")
    val livingEntity: LivingEntitySnapshot
): LootTableEntry() {
    override fun onSpawn(location: Location): Entity {
        return livingEntity.spawn(location)
    }
}

