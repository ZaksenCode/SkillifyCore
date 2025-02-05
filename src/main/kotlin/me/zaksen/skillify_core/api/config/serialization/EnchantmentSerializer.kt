package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.EnchantmentSurrogate
import org.bukkit.enchantments.Enchantment

class EnchantmentSerializer: KSerializer<Enchantment> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.enchantments.Enchantment",
        EnchantmentSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Enchantment {
        val surrogate = decoder.decodeSerializableValue(EnchantmentSurrogate.serializer())
        return Enchantment.getByKey(surrogate.key)!!
    }

    override fun serialize(encoder: Encoder, value: Enchantment) {
        val surrogate = EnchantmentSurrogate.serializer()
        encoder.encodeSerializableValue(surrogate, EnchantmentSurrogate(value.key))
    }
}