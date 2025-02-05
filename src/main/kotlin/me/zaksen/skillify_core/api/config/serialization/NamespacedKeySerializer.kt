package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.NamespacedKeySurrogate
import org.bukkit.NamespacedKey

class NamespacedKeySerializer : KSerializer<NamespacedKey> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.NamespacedKey",
        NamespacedKeySurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): NamespacedKey {
        val surrogate = decoder.decodeSerializableValue(NamespacedKeySurrogate.serializer())
        return NamespacedKey(surrogate.namespace, surrogate.key)
    }

    override fun serialize(encoder: Encoder, value: NamespacedKey) {
        val surrogate = NamespacedKeySurrogate(value.namespace, value.key)
        encoder.encodeSerializableValue(
            NamespacedKeySurrogate.serializer(),
            surrogate
        )
    }
}

/** Data type for easier serialization NamespacedKey */
typealias NamespacedKeyValue = @Serializable(NamespacedKeySerializer::class) NamespacedKey