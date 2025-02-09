package me.zaksen.skillify_core.api.packet.npc

import com.comphenix.protocol.ProtocolManager
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*

class NPC(
    private val protocolManager: ProtocolManager,
    private val uuid: UUID
) {
    fun spawn(player: Player, location: Location) {
        val updateInfo = PlayerInfoUpdateHelper(protocolManager, uuid)
        val spawn = EntitySpawnHelper(protocolManager, uuid)

        updateInfo.updateInfo(player)
        spawn.spawnEntity(player, EntityType.PLAYER, location)
    }
}