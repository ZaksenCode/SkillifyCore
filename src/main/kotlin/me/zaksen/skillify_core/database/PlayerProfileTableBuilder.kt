package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.api.database.TableBuilder
import java.sql.Connection

class PlayerProfileTableBuilder(
    private val connection: Connection,
    private val table: String
): TableBuilder {
    override fun buildTable() {
        try {
            val statement = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS $table (
                    player_id                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
                    player_uuid                 TEXT NOT NULL,
                    player_name                 TEXT NOT NULL,
                    player_register_time        TIMESTAMP NOT NULL,
                    PRIMARY KEY (player_id)
                );
            """)
            statement.execute()
            statement.close()
        } catch (_: Exception) {}
    }
}