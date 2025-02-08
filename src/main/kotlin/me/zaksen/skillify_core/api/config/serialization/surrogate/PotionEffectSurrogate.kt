package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.NamespacedKeyValue

@Serializable
@SerialName("PotionEffect")
data class PotionEffectSurrogate (
    @SerialName("amplifier")
    val amplifier: Int,

    @SerialName("duration")
    val duration: Int,

    @SerialName("type")
    val type: NamespacedKeyValue,

    @SerialName("ambient")
    val ambient: Boolean,

    @SerialName("particles")
    val particles: Boolean,

    @SerialName("icon")
    val icon: Boolean
)