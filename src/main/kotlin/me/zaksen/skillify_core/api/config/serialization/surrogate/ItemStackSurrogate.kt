package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemMetaValue

@Serializable
@SerialName("ItemStack")
data class ItemStackSurrogate(
    @SerialName("type")
    val type: String,

    @SerialName("amount")
    val amount: Int,

    @SerialName("meta")
    val meta: ItemMetaValue
)
