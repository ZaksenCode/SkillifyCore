package me.zaksen.skillify_core.api.recipe.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import me.zaksen.skillify_core.api.recipe.data.entry.MaterialEntry
import me.zaksen.skillify_core.api.recipe.data.entry.RecipeEntry
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("ShapedRecipe")
data class ShapedRecipe(
    @SerialName("output")
    override val output: RecipeEntry,

    @SerialName("grid")
    val grid: List<String> = listOf(
        "AAA",
        "AAA",
        "AAA"
    ),

    @SerialName("grid_resolver")
    val gridResolve: Map<Char, RecipeEntry> = mapOf(
        Pair('A', MaterialEntry(Material.STICK))
    )
): Recipe()