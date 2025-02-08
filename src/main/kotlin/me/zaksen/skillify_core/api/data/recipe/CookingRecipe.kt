package me.zaksen.skillify_core.api.data.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import org.bukkit.inventory.ItemStack

@Serializable
data class CookingRecipe(
    @SerialName("output")
    @Serializable(with = ItemStackSerializer::class)
    override val output: ItemStack,

    @SerialName("input")
    @Serializable(with = ItemStackSerializer::class)
    val input: ItemStack,

    @SerialName("experience")
    val experience: Float,

    @SerialName("cooking_time")
    val cookingTime: Int,

    @SerialName("exact_match")
    val exactMatch: Boolean
): Recipe()