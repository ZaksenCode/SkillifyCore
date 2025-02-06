package me.zaksen.skillify_core.api.util.extensions

import org.bukkit.NamespacedKey

fun String.toNamespacedKey(): NamespacedKey {
    if(!this.contains(Regex.fromLiteral("([A-Z]:[A-Z])"))) {
        throw IllegalArgumentException("To create NamespacedKey string should match regex: '([A-Z]:[A-Z])'")
    }
    val split = this.split(":")

    if(split.size != 2) {
        throw IllegalArgumentException("Wrong string for creating NamespacedKey")
    }

    return NamespacedKey(split.first(), split.last())
}