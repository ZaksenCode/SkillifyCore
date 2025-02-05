package me.zaksen.skillify_core.api.config.serialization

import com.destroystokyo.paper.Namespaced
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.ItemStackSurrogate
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemStackSerializer : KSerializer<ItemStack> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.inventory.ItemStack",
        ItemStackSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): ItemStack {
        val value = decoder.decodeSerializableValue(ItemStackSurrogate.serializer())

        val result = ItemStack(Material.valueOf(value.type.uppercase()), value.amount)
        result.itemMeta = value.meta

        return result
    }

    override fun serialize(encoder: Encoder, value: ItemStack) {
        encoder.encodeSerializableValue(ItemStackSurrogate.serializer(), ItemStackSurrogate(
            value.type.toString(),
            value.amount,
            value.itemMeta
        ))
    }
}

/** Data type for easier serialization ItemStack */
typealias ItemStackValue = @Serializable(ItemStackSerializer::class) ItemStack