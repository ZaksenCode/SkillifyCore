package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.UUIDSerializer
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import java.util.UUID

@Serializable
@SerialName("AttributeModifier")
data class AttributeModifierSurrogate(
    @SerialName("uuid")
    @Serializable(with = UUIDSerializer::class)
    val uuid: UUID,

    @SerialName("name")
    val name: String,

    @SerialName("amount")
    val amount: Double,

    @SerialName("operation")
    val operation: AttributeModifier.Operation,

    @SerialName("slot")
    val slot: EquipmentSlot?
)
