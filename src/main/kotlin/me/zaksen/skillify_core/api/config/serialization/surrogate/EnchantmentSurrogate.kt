package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.NamespacedKeySerializer
import org.bukkit.NamespacedKey

@Serializable
data class EnchantmentSurrogate(
    @SerialName("key")
    @Serializable(with = NamespacedKeySerializer::class)
    val key: NamespacedKey
)
