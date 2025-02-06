package me.zaksen.skillify_core.api.item

import me.zaksen.skillify_core.api.util.extensions.toNamespacedKey
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.function.Supplier

/**
 * It is used to conditionally register your own items.
 * It does not actually register any items, but only adds event handling for ItemStack containing item_id field in nbt.
 * Use this class in the onLoad method.
 * It is best to use `SkillifyCore.getCore().itemRegistry`.
 * @see org.bukkit.plugin.Plugin.onLoad
 * @see me.zaksen.skillify_core.SkillifyCore.getCore
 */
class CustomItemRegistry(
    private val itemIdKey: NamespacedKey
) {
    private val registeredItems: MutableMap<NamespacedKey, CustomItem> = mutableMapOf()
    private val defaultStacks: MutableMap<NamespacedKey, Supplier<ItemStack>> = mutableMapOf()

    /**
     * Saves the CustomItem class object by key to work with it.
     * @param key The identifier by which the CustomItem object will be accessed and which should be written to the pdc for the ItemStack.
     * @param item An object of the CustomItem class that will be used as an event handler.
     *
     * @see org.bukkit.persistence.PersistentDataContainer
     * @see CustomItem
     * @see NamespacedKey
     */
    fun registerItem(key: NamespacedKey, item: CustomItem) {
        registeredItems[key] = item
    }

    /**
     * Saves the ItemStack supplier by key to work with it.
     * Need for the function to get the standard ItemStack for CustomItem.
     * @param key The identifier by which the ItemStack object will be accessed and which should be written to the pdc for the ItemStack.
     * @param stack Function for getting ItemStack
     *
     * @see org.bukkit.persistence.PersistentDataContainer
     * @see ItemStack
     * @see NamespacedKey
     */
    fun registerStack(key: NamespacedKey, stack: Supplier<ItemStack>) {
        defaultStacks[key] = stack
    }

    /**
     * Combining registerItem and registerStack functions
     * @param key The identifier by which the objects will be accessed and which should be written to the pdc for the ItemStack.
     * @param item An object of the CustomItem class that will be used as an event handler.
     * @param stack Function for getting ItemStack
     *
     * @see registerItem
     * @see registerStack
     */
    fun registerItemWithStack(key: NamespacedKey, item: CustomItem, stack: Supplier<ItemStack>) {
        registerItem(key, item)
        registerStack(key, stack)
    }

    /**
     * Function to get an instance of CustomItem class.
     * @param key Key by which the object is registered.
     * @return An instance of the CustomItem class
     */
    fun getItem(key: NamespacedKey): CustomItem? {
        return registeredItems[key]
    }

    /**
     * Function to get an instance of ItemStack class.
     * @param key Key by which the stack is registered.
     * @return An instance of the ItemStack class
     */
    fun getStack(key: NamespacedKey): ItemStack? {
        val stackSupplier = defaultStacks[key] ?: return null
        return stackSupplier.get()
    }

    /**
     * Function to get an instance of CustomItem class directly from ItemStack.
     * @param stack Stack for which we want to get an object of CustomItem class
     * @return An instance of the CustomItem class
     */
    fun getItem(stack: ItemStack): CustomItem? {
        if(!stack.hasItemMeta()) {
            return null
        }

        val pdc = stack.itemMeta.persistentDataContainer
        val key = pdc.get(itemIdKey, PersistentDataType.STRING) ?: return null

        return getItem(key.toNamespacedKey())
    }

    /**
     * Stores the custom item key in ItemStack's PDC
     * @param id The key to be stored
     * @param stack Stack where the key should be stored
     * @see org.bukkit.persistence.PersistentDataContainer
     * @see ItemStack
     */
    fun writeItemId(id: NamespacedKey, stack: ItemStack) {
        stack.editMeta {
            it.persistentDataContainer.set(itemIdKey, PersistentDataType.STRING, id.toString())
        }
    }

    fun getRegisteredItems(): Map<NamespacedKey, CustomItem> {
        return registeredItems
    }
}