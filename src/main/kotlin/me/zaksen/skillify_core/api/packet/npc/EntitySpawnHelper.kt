package me.zaksen.skillify_core.api.packet.npc

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.UUID

class EntitySpawnHelper(
    private val protocolManager: ProtocolManager,
    private val uuid: UUID
) {
    fun spawnEntity(player: Player, type: EntityType, location: Location) {
        val spawned = protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN)

        spawned.integers.write(0, -1).writeSafely(1, 122)
        spawned.uuiDs.write(0, uuid)

        spawned.entityTypeModifier.write(0, type)

        spawned.doubles
            .write(0, location.x)
            .write(1, location.y)
            .write(2, location.z)

        spawned.bytes.write(0, 0).write(1, 0)

        protocolManager.sendServerPacket(player, spawned)
    }
}