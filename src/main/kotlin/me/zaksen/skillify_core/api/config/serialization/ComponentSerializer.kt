package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

class ComponentSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Component", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Component {
        val deSerialized = decoder.decodeString()
        return MiniMessage.miniMessage().deserialize(deSerialized)
    }

    override fun serialize(encoder: Encoder, value: Component) {
        val serialized = MiniMessage.miniMessage().serialize(value).replace("\\", "")
        encoder.encodeString(serialized)
    }
}

/** Data type for easier serialization Component */
typealias ComponentValue = @Serializable(ComponentSerializer::class) Component