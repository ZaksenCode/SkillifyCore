package me.zaksen.skillify_core.api.data.loot.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackValue
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item

@Serializable
@SerialName("item_stack")
data class ItemStackEntry(
    @SerialName("value")
    val stack: ItemStackValue
): LootTableEntry() {
    override fun onSpawn(location: Location): Entity {
        val world = location.world
        val itemEntity = world.spawnEntity(location, EntityType.DROPPED_ITEM) as Item
        itemEntity.itemStack = this.stack
        return itemEntity
    }
}
