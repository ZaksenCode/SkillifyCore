package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.api.database.Repository
import me.zaksen.skillify_core.database.dao.PlayerProfile
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class PlayerProfileRepository(
    host: String,
    port: String,
    base: String,
    private val table: String,
    user: String,
    pass: String
): Repository<PlayerProfile, PlayerProfileSpecification> {
    private val cache: MutableSet<PlayerProfile> = mutableSetOf()
    private val connection: Connection = DriverManager.getConnection("jdbc:mysql://$host:$port/$base", user, pass)

    init {
        try {
            val statement = connection.prepareStatement("""
                SELECT * FROM $table
            """)
            val set = statement.executeQuery()

            // Load values into cache
            while (set.next()) {
                val id = set.getLong("id")
                val uuid = set.getString("uuid")
                val name = set.getString("name")
                val registerTime = set.getTimestamp("register_time")
                cache.add(PlayerProfile(id, UUID.fromString(uuid), name, registerTime))
            }

        } catch (_: Exception) {}
    }

    override fun add(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                INSERT INTO $table (uuid, name, register_time)
                VALUES (?, ?, ?);
            """)
            statement.setString(1, value.uuid.toString())
            statement.setString(2, value.name)
            statement.setTimestamp(3, value.registerTime)
            statement.execute()
            statement.close()
            cache.add(value)
        } catch (_: Exception) {}
    }

    override fun remove(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                DELETE FROM $table 
                WHERE id = ?;
            """)
            statement.setLong(1, value.id)
            statement.execute()
            statement.close()
            cache.remove(value)
        } catch (_: Exception) {}
    }

    override fun update(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                UPDATE $table
                SET uuid = ?, name = ?, register_time = ?
                WHERE id = ?; 
            """)
            statement.setString(1, value.uuid.toString())
            statement.setString(2, value.name)
            statement.setTimestamp(3, value.registerTime)
            statement.setLong(4, value.id)
            statement.executeUpdate()
            statement.close()
            cache.removeIf { it.id == value.id }
            cache.add(value)
        } catch (_: Exception) {}
    }

    override fun query(specification: PlayerProfileSpecification): Set<PlayerProfile> {
        val result = mutableSetOf<PlayerProfile>()

        result.addAll(cache.filter { specification.specified(it) })

        // Using cache
//        try {
//            val statement = connection.prepareStatement("""
//                SELECT FROM $table
//                WHERE ${specification.toSqlClauses()};
//            """)
//            val set = statement.executeQuery()
//
//            while(set.next()) {
//                val id = set.getLong("id")
//                val uuid = set.getString("uuid")
//                val name = set.getString("name")
//                val registerTime = set.getTimestamp("register_time")
//                result.add(PlayerProfile(id, UUID.fromString(uuid), name, registerTime))
//            }
//
//            statement.closeOnCompletion()
//        } catch (_: Exception) {}

        return result
    }
}