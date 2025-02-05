package me.zaksen.skillify_core.api.config.serialization.surrogate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("NamespacedKey")
data class NamespacedKeySurrogate(
    val namespace: String,
    val key: String
)
