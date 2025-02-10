package me.zaksen.skillify_core.api.config.serialization.surrogate

import com.google.common.collect.Multimap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.*
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.inventory.ItemFlag

@Serializable
@SerialName("ItemMeta")
data class ItemMetaSurrogate(
    @SerialName("display_name")
    val displayName: ComponentValue?,

    @SerialName("lore")
    val lore: List<ComponentValue>?,

    @SerialName("custom_model_data")
    val customModelData: Int,

    @SerialName("enchants")
    val enchants: Map<EnchantmentValue, Int>,

    @SerialName("item_flags")
    val itemFlags: Set<ItemFlag>,

    @SerialName("unbreakable")
    val unbreakable: Boolean,

    @SerialName("attribute_modifiers")
    val attributeModifiers: Multimap<
            Attribute,
            AttributeModifierValue
    >?,

    @SerialName("destroyable_keys")
    val destroyableKeys: Set<NamespacedValue>,

    @SerialName("placeable_keys")
    val placeableKeys: Set<NamespacedValue>,

    @SerialName("persistent_data_container")
    val persistentDataContainer: PersistentDataContainerValue
)
