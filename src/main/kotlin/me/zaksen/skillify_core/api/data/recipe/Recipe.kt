package me.zaksen.skillify_core.api.data.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import org.bukkit.inventory.ItemStack

@Serializable
abstract class Recipe {
    @SerialName("output")
    @Serializable(with = ItemStackSerializer::class)
    abstract val output: ItemStack
}