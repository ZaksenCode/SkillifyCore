package me.zaksen.skillify_core.api.util.extensions

import org.bukkit.NamespacedKey

fun String.toNamespacedKey(): NamespacedKey {
    if(!this.matches(Regex.fromLiteral("[a-zA-Z_]+:[a-zA-Z_]+"))) {
        throw IllegalArgumentException("To create NamespacedKey string should match regex: '[a-zA-Z_]+:[a-zA-Z_]+'")
    }
    val split = this.split(":")

    if(split.size != 2) {
        throw IllegalArgumentException("Wrong string for creating NamespacedKey")
    }

    return NamespacedKey(split.first(), split.last())
}