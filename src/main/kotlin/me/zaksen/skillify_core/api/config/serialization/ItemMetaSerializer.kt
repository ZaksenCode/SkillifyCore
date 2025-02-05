package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.ItemMetaSurrogate
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ItemMetaSerializer: KSerializer<ItemMeta> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.inventory.meta.ItemMeta",
        ItemMetaSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): ItemMeta {
        val value = decoder.decodeSerializableValue(ItemMetaSurrogate.serializer())

        val meta = ItemStack.empty().itemMeta

        meta.displayName(value.displayName)
        meta.lore(value.lore)
        meta.setCustomModelData(value.customModelData)

        value.enchants.forEach {
            meta.addEnchant(it.key, it.value, false)
        }

        value.itemFlags.forEach {
            meta.itemFlags.add(it)
        }

        meta.isUnbreakable = value.unbreakable

        value.attributeModifiers?.forEach { at, atMod ->
            meta.addAttributeModifier(at, atMod)
        }

        meta.setDestroyableKeys(value.destroyableKeys)
        meta.setPlaceableKeys(value.placeableKeys)

        meta.persistentDataContainer.readFromBytes(value.persistentDataContainer.serializeToBytes())

        return meta
    }

    override fun serialize(encoder: Encoder, value: ItemMeta) {
        encoder.encodeSerializableValue(ItemMetaSurrogate.serializer(),
            ItemMetaSurrogate(
                value.displayName(),
                value.lore(),
                value.customModelData,
                value.enchants,
                value.itemFlags,
                value.isUnbreakable,
                value.attributeModifiers,
                value.destroyableKeys,
                value.placeableKeys,
                value.persistentDataContainer
            )
        )
    }
}