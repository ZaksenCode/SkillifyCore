package me.zaksen.skillify_core.database.dao

import java.sql.Timestamp
import java.util.UUID

data class PlayerProfile(
    var id: Long,
    var uuid: UUID,
    var name: String,
    var registerTime: Timestamp
)