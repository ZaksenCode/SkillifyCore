package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.inventory.meta.ItemMeta

@Serializable
@SerialName("ItemStack")
data class ItemStackSurrogate(
    @SerialName("type")
    val type: String,

    @SerialName("amount")
    val amount: Int,

    @SerialName("meta")
    @Serializable
    val meta: ItemMeta
)
