package me.zaksen.skillify_core.api.recipe.data.entry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.inventory.ItemStack

@Serializable
@SerialName("Entry")
abstract class RecipeEntry {
    abstract fun asStack(): ItemStack
}
