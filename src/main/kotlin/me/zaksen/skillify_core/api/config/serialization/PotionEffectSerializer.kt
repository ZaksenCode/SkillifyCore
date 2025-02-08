package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import me.zaksen.skillify_core.api.config.serialization.surrogate.PotionEffectSurrogate
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PotionEffectSerializer: KSerializer<PotionEffect> {
    override val descriptor: SerialDescriptor = SerialDescriptor(
        "org.bukkit.potion.PotionEffect",
        PotionEffectSurrogate.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): PotionEffect {
        val value = decoder.decodeSerializableValue(PotionEffectSurrogate.serializer())

        return PotionEffect(
            PotionEffectType.getByKey(value.type)!!,
            value.duration,
            value.amplifier,
            value.ambient,
            value.particles,
            value.icon
        )
    }

    override fun serialize(encoder: Encoder, value: PotionEffect) {
        encoder.encodeSerializableValue(PotionEffectSurrogate.serializer(), PotionEffectSurrogate(
            value.amplifier,
            value.duration,
            value.type.key,
            value.isAmbient,
            value.hasParticles(),
            value.hasIcon()
        ))
    }
}

/** Data type for easier serialization PotionEffect */
typealias PotionEffectValue = @Serializable(PotionEffectSerializer::class) PotionEffect