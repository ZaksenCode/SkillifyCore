package me.zaksen.skillify_core.api.config.serialization

import com.destroystokyo.paper.Namespaced
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.NamespacedKeySurrogate
import org.bukkit.NamespacedKey

class NamespacedSerializer : KSerializer<Namespaced> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "com.destroystokyo.paper.NamespacedKey",
        NamespacedKeySurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Namespaced {
        val surrogate = decoder.decodeSerializableValue(NamespacedKeySurrogate.serializer())
        return NamespacedKey(surrogate.namespace, surrogate.key)
    }

    override fun serialize(encoder: Encoder, value: Namespaced) {
        val surrogate = NamespacedKeySurrogate(value.namespace, value.key)
        encoder.encodeSerializableValue(
            NamespacedKeySurrogate.serializer(),
            surrogate
        )
    }
}

/** Data type for easier serialization Namespaced */
typealias NamespacedValue = @Serializable(NamespacedSerializer::class) Namespaced