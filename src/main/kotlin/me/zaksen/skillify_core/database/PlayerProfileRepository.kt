package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.api.database.Repository
import me.zaksen.skillify_core.database.dao.PlayerProfile
import org.slf4j.Logger
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class PlayerProfileRepository(
    private val logger: Logger,
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
        PlayerProfileTableBuilder(connection, table).buildTable()
        try {
            val statement = connection.prepareStatement("""
                SELECT * FROM $table
            """)
            val set = statement.executeQuery()

            // Load values into cache
            while (set.next()) {
                val id = set.getLong("player_id")
                val uuid = set.getString("player_uuid")
                val name = set.getString("player_name")
                val registerTime = set.getTimestamp("player_register_time")
                cache.add(PlayerProfile(id, UUID.fromString(uuid), name, registerTime))
            }

            logger.info("Loaded ${cache.size} profiles")
        } catch (_: Exception) {
            logger.warn("An error was received during the database initialization process.")
        }
    }

    override fun add(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                INSERT INTO $table (player_uuid, player_name, player_register_time)
                VALUES (?, ?, ?);
            """)
            statement.setString(1, value.uuid.toString())
            statement.setString(2, value.name)
            statement.setTimestamp(3, value.registerTime)
            statement.execute()
            statement.close()
            cache.add(value)
        } catch (_: Exception) {
            logger.warn("Received an error when adding data to the database to a {$table} table.")
        }
    }

    override fun remove(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                DELETE FROM $table 
                WHERE player_id = ?;
            """)
            statement.setLong(1, value.id)
            statement.execute()
            statement.close()
            cache.remove(value)
        } catch (_: Exception) {
            logger.warn("Received an error when deleting data from the database from a {$table} table.")
        }
    }

    override fun update(value: PlayerProfile) {
        try {
            val statement = connection.prepareStatement("""
                UPDATE $table
                SET player_uuid = ?, player_name = ?, player_register_time = ?
                WHERE player_id = ?; 
            """)
            statement.setString(1, value.uuid.toString())
            statement.setString(2, value.name)
            statement.setTimestamp(3, value.registerTime)
            statement.setLong(4, value.id)
            statement.executeUpdate()
            statement.close()
            cache.removeIf { it.id == value.id }
            cache.add(value)
        } catch (_: Exception) {
            logger.warn("Received an error when updating data from the database to a {$table} table.")
        }
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