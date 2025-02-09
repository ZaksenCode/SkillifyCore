package me.zaksen.skillify_core.api.data.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackValue

@Serializable
data class CustomItemSnapshot(
    @SerialName("stack")
    val stack: ItemStackValue
)