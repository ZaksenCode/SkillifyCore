package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.config.serialization.NamespacedKeyValue

@Serializable
data class EnchantmentSurrogate(
    @SerialName("key")
    val key: NamespacedKeyValue
)
