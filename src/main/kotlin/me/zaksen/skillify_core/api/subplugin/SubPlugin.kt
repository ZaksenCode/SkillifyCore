package me.zaksen.skillify_core.api.subplugin

import me.zaksen.skillify_core.api.item.CustomItemRegistry
import me.zaksen.skillify_core.api.loot.LootTableRegistry
import me.zaksen.skillify_core.api.recipe.RecipeRegistry

/**
 * Interface that addons for SkillifyCore should inherit
 * Used by the kernel to create some semblance of a CallBack's
 */
interface SubPlugin {
    /**
     * Mostly a utilitarian method for some logs.
     * @return SkillifyCore sub plugin name
     */
    fun getName(): String
    /**
     * Method to be called when loading or reloading recipes.
     * @param registry A recipe registry where you can register recipes.
     *
     * @see RecipeRegistry
     */
    fun loadRecipes(registry: RecipeRegistry) {}
    /**
     * Method to be called when loading or reloading loot tables.
     * @param registry A loot table registry where you can register loot tables.
     *
     * @see LootTableRegistry
     */
    fun loadLootTables(registry: LootTableRegistry) {}
    /**
     * Method to be called when loading or reloading custom items.
     * @param registry A item registry where you can register custom items and default stacks.
     *
     * @see CustomItemRegistry
     */
    fun loadItems(registry: CustomItemRegistry) {}
}