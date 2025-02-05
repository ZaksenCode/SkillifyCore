package me.zaksen.skillify_core.api.config.data.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.ItemStackSerializer
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("ShapelessRecipe")
data class ShapelessRecipe(
    @SerialName("output")
    @Serializable(with = ItemStackSerializer::class)
    override val output: ItemStack,

    @SerialName("ingredients")
    val ingredients: List<@Serializable(with = ItemStackSerializer::class) ItemStack> = listOf(
        ItemStack.empty()
    )
): Recipe()
