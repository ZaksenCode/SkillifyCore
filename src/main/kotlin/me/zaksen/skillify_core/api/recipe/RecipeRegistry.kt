package me.zaksen.skillify_core.api.recipe

import me.zaksen.skillify_core.api.config.data.recipe.CookingRecipe
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.*

/**
 * Mostly a utilitarian class, for conveniently adding recipes and revealing them to the player.
 */
class RecipeRegistry {

    private val registeredRecipes: MutableSet<NamespacedKey> = mutableSetOf()

    /**
     * Creates an object of the RecipeChoice class
     * @param stack ItemStack for which you need to make a RecipeChoice
     * @param isExact If true, only an ItemStack with exactly the same Nbt will be able to participate in the recipe.
     * @return object of RecipeChoice class containing ItemStack
     */
    fun getRecipeChoice(stack: ItemStack, isExact: Boolean): RecipeChoice {
        if(isExact) {
            return RecipeChoice.ExactChoice(stack)
        }
        return RecipeChoice.MaterialChoice(stack.type)
    }

    /**
     * Creates a ShapedRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param shape A grid for a recipe in the form of a list of symbols.
     * @param shapeResolver Decoding for grid symbols
     *
     * @see ShapedRecipe
     */
    fun registerShapedRecipe(
        key: NamespacedKey,
        output: ItemStack,
        shape: List<String>,
        shapeResolver: Map<Char, ItemStack>
    ) {
        val shapedRecipe = ShapedRecipe(key, output)
        shapedRecipe.shape(*shape.toTypedArray())

        shapeResolver.forEach {
            shapedRecipe.setIngredient(it.key, it.value)
        }

        Bukkit.addRecipe(shapedRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a ShapedRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see ShapedRecipe
     */
    fun registerShapedRecipe(key: NamespacedKey, recipe: me.zaksen.skillify_core.api.config.data.recipe.ShapedRecipe) {
        this.registerShapedRecipe(key, recipe.output, recipe.grid, recipe.gridResolve)
    }

    /**
     * Creates a ShapelessRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param ingredients Ingredients needed for crafting.
     *
     * @see ShapelessRecipe
     */
    fun registerShapelessRecipe(
        key: NamespacedKey,
        output: ItemStack,
        ingredients: List<ItemStack>
    ) {
        val shapelessRecipe = ShapelessRecipe(key, output)

        ingredients.forEach {
            shapelessRecipe.addIngredient(it)
        }

        Bukkit.addRecipe(shapelessRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a ShapelessRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see ShapelessRecipe
     */
    fun registerShapelessRecipe(key: NamespacedKey, recipe: me.zaksen.skillify_core.api.config.data.recipe.ShapelessRecipe) {
        this.registerShapelessRecipe(key, recipe.output, recipe.ingredients)
    }

    /**
     * Creates a FurnaceRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param input The subject from which we will be getting output
     * @param experience The experience the player will gain as a result of the recipe
     * @param cookingTime Time required for cooking (in ticks)
     * @param exactChoice If true, only an ItemStack with exactly the same Nbt will be able to participate in the recipe.
     *
     * @see FurnaceRecipe
     * @see getRecipeChoice
     */
    fun registerFurnaceRecipe(
        key: NamespacedKey,
        output: ItemStack,
        input: ItemStack,
        experience: Float,
        cookingTime: Int,
        exactChoice: Boolean
    ) {
        val choice: RecipeChoice = getRecipeChoice(input, exactChoice)
        val furnaceRecipe = FurnaceRecipe(key, output, choice, experience, cookingTime)
        Bukkit.addRecipe(furnaceRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a FurnaceRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see FurnaceRecipe
     */
    fun registerFurnaceRecipe(key: NamespacedKey, recipe: CookingRecipe) {
        this.registerFurnaceRecipe(key, recipe.output, recipe.input, recipe.experience, recipe.cookingTime, recipe.exactMatch)
    }

    /**
     * Creates a CampfireRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param input The subject from which we will be getting output
     * @param experience The experience the player will gain as a result of the recipe
     * @param cookingTime Time required for cooking (in ticks)
     * @param exactChoice If true, only an ItemStack with exactly the same Nbt will be able to participate in the recipe.
     *
     * @see CampfireRecipe
     * @see getRecipeChoice
     */
    fun registerCampfireRecipe(
        key: NamespacedKey,
        output: ItemStack,
        input: ItemStack,
        experience: Float,
        cookingTime: Int,
        exactChoice: Boolean
    ) {
        val choice: RecipeChoice = getRecipeChoice(input, exactChoice)
        val campfireRecipe = CampfireRecipe(key, output, choice, experience, cookingTime)
        Bukkit.addRecipe(campfireRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a CampfireRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see CampfireRecipe
     */
    fun registerCampfireRecipe(key: NamespacedKey, recipe: CookingRecipe) {
        this.registerCampfireRecipe(key, recipe.output, recipe.input, recipe.experience, recipe.cookingTime, recipe.exactMatch)
    }

    /**
     * Creates a BlastingRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param input The subject from which we will be getting output
     * @param experience The experience the player will gain as a result of the recipe
     * @param cookingTime Time required for cooking (in ticks)
     * @param exactChoice If true, only an ItemStack with exactly the same Nbt will be able to participate in the recipe.
     *
     * @see BlastingRecipe
     * @see getRecipeChoice
     */
    fun registerBlastingRecipe(
        key: NamespacedKey,
        output: ItemStack,
        input: ItemStack,
        experience: Float,
        cookingTime: Int,
        exactChoice: Boolean
    ) {
        val choice: RecipeChoice = getRecipeChoice(input, exactChoice)
        val blastingRecipe = BlastingRecipe(key, output, choice, experience, cookingTime)
        Bukkit.addRecipe(blastingRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a BlastingRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see BlastingRecipe
     */
    fun registerBlastingRecipe(key: NamespacedKey, recipe: CookingRecipe) {
        this.registerBlastingRecipe(key, recipe.output, recipe.input, recipe.experience, recipe.cookingTime, recipe.exactMatch)
    }

    /**
     * Creates a SmokingRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param output The item we will get as a result of the recipe
     * @param input The subject from which we will be getting output
     * @param experience The experience the player will gain as a result of the recipe
     * @param cookingTime Time required for cooking (in ticks)
     * @param exactChoice If true, only an ItemStack with exactly the same Nbt will be able to participate in the recipe.
     *
     * @see SmokingRecipe
     * @see getRecipeChoice
     */
    fun registerSmokingRecipe(
        key: NamespacedKey,
        output: ItemStack,
        input: ItemStack,
        experience: Float,
        cookingTime: Int,
        exactChoice: Boolean
    ) {
        val choice: RecipeChoice = getRecipeChoice(input, exactChoice)
        val smokingRecipe = SmokingRecipe(key, output, choice, experience, cookingTime)
        Bukkit.addRecipe(smokingRecipe)
        registeredRecipes.add(key)
    }

    /**
     * Creates a SmokingRecipe and registers it in the given register.
     * @param key The key by which the recipe will be registered.
     * @param recipe Recipe object
     *
     * @see SmokingRecipe
     */
    fun registerSmokingRecipe(key: NamespacedKey, recipe: CookingRecipe) {
        this.registerSmokingRecipe(key, recipe.output, recipe.input, recipe.experience, recipe.cookingTime, recipe.exactMatch)
    }

    /**
     * Reveals all registered recipes to the player.
     * @param player The player to whom the recipes will be revealed
    */
    fun revealRecipes(player: Player) {
        player.discoverRecipes(registeredRecipes)
    }

    /**
     * @return All recipes registered through this register
     */
    fun getRegisteredRecipes(): Set<NamespacedKey> {
        return registeredRecipes
    }
}