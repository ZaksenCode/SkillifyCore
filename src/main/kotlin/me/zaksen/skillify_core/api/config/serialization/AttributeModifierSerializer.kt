package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.AttributeModifierSurrogate
import org.bukkit.attribute.AttributeModifier

class AttributeModifierSerializer : KSerializer<AttributeModifier> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.attribute.AttributeModifier",
        AttributeModifierSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): AttributeModifier {
        val surrogate = decoder.decodeSerializableValue(AttributeModifierSurrogate.serializer())
        return AttributeModifier(
            surrogate.uuid,
            surrogate.name,
            surrogate.amount,
            surrogate.operation,
            surrogate.slot
        )
    }

    override fun serialize(encoder: Encoder, value: AttributeModifier) {
        val surrogate = AttributeModifierSurrogate(
            value.uniqueId,
            value.name,
            value.amount,
            value.operation,
            value.slot
        )

        encoder.encodeSerializableValue(
            AttributeModifierSurrogate.serializer(),
            surrogate
        )
    }
}

/** Data type for easier serialization AttributeModifier */
typealias AttributeModifierValue = @Serializable(AttributeModifierSerializer::class) AttributeModifier