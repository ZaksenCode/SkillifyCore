package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.database.dao.PlayerProfile
import java.util.UUID

class PlayerProfileNameSpecification(
    private val name: String
): PlayerProfileSpecification {
    override fun specified(value: PlayerProfile): Boolean {
        return value.name == name
    }

    override fun toSqlClauses(): String {
        return "name = $name"
    }
}