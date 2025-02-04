package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.database.dao.PlayerProfile
import java.util.UUID

class PlayerProfileUuidSpecification(
    private val uuid: UUID
): PlayerProfileSpecification {
    override fun specified(value: PlayerProfile): Boolean {
        return value.uuid == uuid
    }

    override fun toSqlClauses(): String {
        return "uuid = $uuid"
    }
}