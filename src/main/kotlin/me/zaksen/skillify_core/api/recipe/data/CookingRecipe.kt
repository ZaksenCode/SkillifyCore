package me.zaksen.skillify_core.api.recipe.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import me.zaksen.skillify_core.api.recipe.data.entry.RecipeEntry
import org.bukkit.inventory.ItemStack

@Serializable
data class CookingRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("input")
    val input: RecipeEntry,

    @SerialName("experience")
    val experience: Float,

    @SerialName("cooking_time")
    val cookingTime: Int,

    @SerialName("exact_match")
    val exactMatch: Boolean
): Recipe()