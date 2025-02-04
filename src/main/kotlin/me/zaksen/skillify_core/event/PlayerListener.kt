package me.zaksen.skillify_core.event

import me.zaksen.skillify_core.database.PlayerProfileRepository
import me.zaksen.skillify_core.database.PlayerProfileUuidSpecification
import me.zaksen.skillify_core.database.dao.PlayerProfile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import java.sql.Timestamp
import java.time.ZonedDateTime

class PlayerListener(
    private val profileRepository: PlayerProfileRepository
): Listener {

    @EventHandler
    private fun registerNewPlayers(event: PlayerLoginEvent) {
        val playerProfile = profileRepository.query(PlayerProfileUuidSpecification(event.player.uniqueId))

        if(playerProfile.isEmpty()) {
            profileRepository.add(PlayerProfile(
                -1,
                event.player.uniqueId,
                event.player.name,
                Timestamp.from(ZonedDateTime.now().toInstant())
            ))
        }
    }
}