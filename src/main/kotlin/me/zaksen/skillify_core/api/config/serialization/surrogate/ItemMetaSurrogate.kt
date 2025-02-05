package me.zaksen.skillify_core.api.config.serialization.surrogate

import com.destroystokyo.paper.Namespaced
import com.google.common.collect.Multimap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.*
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.persistence.PersistentDataContainer

@Serializable
@SerialName("ItemMeta")
data class ItemMetaSurrogate(
    @SerialName("display_name")
    val displayName: Component?,

    @SerialName("lore")
    val lore: List<Component>?,

    @SerialName("custom_model_data")
    val customModelData: Int,

    @SerialName("enchants")
    val enchants: Map<@Serializable(with = EnchantmentSerializer::class) Enchantment, Int>,

    @SerialName("item_flags")
    val itemFlags: Set<ItemFlag>,

    @SerialName("unbreakable")
    val unbreakable: Boolean,

    @SerialName("attribute_modifiers")
    val attributeModifiers: Multimap<
            Attribute,
            @Serializable(with = AttributeModifierSerializer::class) AttributeModifier
    >?,

    @SerialName("destroyable_keys")
    val destroyableKeys: Set<@Serializable(with = NamespacedSerializer::class) Namespaced>,

    @SerialName("placeable_keys")
    val placeableKeys: Set<@Serializable(with = NamespacedSerializer::class) Namespaced>,

    @SerialName("persistent_data_container")
    @Serializable(with = PersistentDataContainerSerializer::class)
    val persistentDataContainer: PersistentDataContainer
)
