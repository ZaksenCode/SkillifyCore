package me.zaksen.skillify_core.api.recipe.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import me.zaksen.skillify_core.api.recipe.data.entry.RecipeEntry
import org.bukkit.inventory.ItemStack

@Serializable
abstract class Recipe {
    @SerialName("output")
    abstract val output: RecipeEntry
}