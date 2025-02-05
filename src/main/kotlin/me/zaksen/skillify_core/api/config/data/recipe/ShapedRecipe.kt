package me.zaksen.skillify_core.api.config.data.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("ShapedRecipe")
data class ShapedRecipe(
    @SerialName("output")
    @Serializable(with = ItemStackSerializer::class)
    override val output: ItemStack,

    @SerialName("grid")
    val grid: List<String> = listOf(
        "AAA",
        "AAA",
        "AAA"
    ),

    @SerialName("grid_resolver")
    val gridResolve: Map<Char, @Serializable(with = ItemStackSerializer::class) ItemStack> = mapOf(
        Pair('A', ItemStack.empty())
    )
): Recipe()