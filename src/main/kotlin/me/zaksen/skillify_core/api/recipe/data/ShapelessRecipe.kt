package me.zaksen.skillify_core.api.recipe.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import me.zaksen.skillify_core.api.recipe.data.entry.RecipeEntry
import me.zaksen.skillify_core.api.recipe.data.entry.StackEntry
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("ShapelessRecipe")
data class ShapelessRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("ingredients")
    val ingredients: List<RecipeEntry> = listOf(
        StackEntry(ItemStack(Material.STONE))
    )
): Recipe()
