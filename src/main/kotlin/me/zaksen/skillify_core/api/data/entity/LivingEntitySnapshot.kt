package me.zaksen.skillify_core.api.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackValue
import me.zaksen.skillify_core.api.config.serialization.PersistentDataContainerValue
import me.zaksen.skillify_core.api.config.serialization.PotionEffectValue
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.EquipmentSlot

@Serializable
data class LivingEntitySnapshot(
    @SerialName("type")
    val entityType: EntityType,

    @SerialName("maximum_air")
    val maximumAir: Int,

    @SerialName("maximum_no_damage_ticks")
    val maximumNoDamageTicks: Int,

    @SerialName("effects")
    val effects: Set<PotionEffectValue>,

    @SerialName("equipment")
    val equipment: Map<EquipmentSlot, ItemStackValue>,

    @SerialName("can_pickup_items")
    val canPickupItems: Boolean = false,

    @SerialName("has_ai")
    val hasAI: Boolean = true,

    @SerialName("invisible")
    val isInvisible: Boolean = true,

    @SerialName("persistent_data_container")
    val pdc: PersistentDataContainerValue
) {
    fun spawn(location: Location): LivingEntity {
        val world = location.world
        val entity = world.spawnEntity(location, entityType) as LivingEntity

        entity.maximumAir = this.maximumAir
        entity.maximumNoDamageTicks = this.maximumNoDamageTicks

        this.effects.forEach {
            entity.addPotionEffect(it)
        }

        val entityEquipment = entity.equipment
        if(entityEquipment != null) {
            equipment.forEach {
                entityEquipment.setItem(it.key, it.value)
            }
        }

        entity.canPickupItems = this.canPickupItems
        entity.setAI(this.hasAI)
        entity.isInvisible = this.isInvisible

        entity.persistentDataContainer.readFromBytes(pdc.serializeToBytes(), true)

        return entity
    }
}
