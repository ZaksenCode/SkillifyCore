package me.zaksen.skillify_core.api.data.loot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.zaksen.skillify_core.api.data.loot.entry.LootTableEntry

@Serializable
data class LootTable (
    @SerialName("rolls")
    val rolls: Int = 1,

    @SerialName("entries")
    val entries: Set<LootTableEntry>
)