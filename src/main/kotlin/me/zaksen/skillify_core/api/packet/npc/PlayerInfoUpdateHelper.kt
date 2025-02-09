package me.zaksen.skillify_core.api.packet.npc

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.wrappers.EnumWrappers
import com.comphenix.protocol.wrappers.PlayerInfoData
import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedGameProfile
import com.comphenix.protocol.wrappers.WrappedSignedProperty
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashSet

class PlayerInfoUpdateHelper(
    private val protocolManager: ProtocolManager,
    private val uuid: UUID
) {
    fun updateInfo(player: Player) {
        val entity = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO)
        val playerInfoActionSet = HashSet<EnumWrappers.PlayerInfoAction>()

        val wrappedProfile = WrappedGameProfile(uuid, "NPC")

        /*
        Create a new property about player's skin

        In the first leave as is and don't touch or nothing works thuth.
        In the second and third, indicate the values of your needs. how to get this data?

        Go to https://minecraftuuid.com/ and find the skin you want.
        If your found the skin, copy his Player UUID.

        Type a search in your browser, but don't press Enter, you'll need to edit that URL;
        https://sessionserver.mojang.com/session/minecraft/profile/uuid?unsigned=false

        Delete a word "uuid" and paste the player's uuid that you copied.
        And now you need to press Enter and you will get the result

        Copy the value and paste to second constructor arguments.
        Copy the signature and paster to third constructor arguments.

         */
        val skinProperty = WrappedSignedProperty(
            "textures",
            "ewogICJ0aW1lc3RhbXAiIDogMTczOTA5MDAzMDM5MCwKICAicHJvZmlsZUlkIiA6ICJkODc2ZDQ4OWFkOTU0NzgwYWVmNmE5MTgyZmY1YjI2MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJaYWtzZW5fIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRmMGI1Zjg5NDUwZWJjOWRjYTI4M2U0Mjc5M2M1MDFkZmRkN2E0M2NhYzRmYzQ3YzJmZTQ3MDhmNjBhMTdkNTEiCiAgICB9LAogICAgIkNBUEUiIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NiNDBhOTJlMzJiNTdmZDczMmEwMGZjMzI1ZTdhZmIwMGE3Y2E3NDkzNmFkNTBkOGU4NjAxNTJlNDgyY2ZiZGUiCiAgICB9CiAgfQp9",
            "P4ARPvfnS1L58qp/yIZD0GOOVLWyRktal8guBCXNX8Usua074p09h6hK/zQxTLxYkdR7MR808GMtUMIUt8yzugsI3evFAzaMhFpHI19qVs4MfQzBOPdVl66IMVb+Nqrm1WmgENNrDqKyPVtggImsR8Qlip7jjzJ+0l1TKJVEVElq7Bolt4xomuJEJjKNBCzra/bD2lWAzfoopdw8n7ZeTTAzfTyxNLDjTafuUMnbPIDb0wG/uSARiXM6VvAt4ZW+xcHSCvCkWJlPrpj8OMg3soQxOClPgMND/AgOrdlFoo/YVgvum7F0goMKGsVmOOUuLZq1VeJaKethe/X8pn7VlIoe4SJZ1t3WhhmCMJ5jI+wVjZX1T8sFLZ77G7LgnK/F81NaExwwW5l3jyi1tawJZp13PF7hvMo/JlZgbnekCW1bcWnXYimhQ7ZlTJlcUtPVYZdlg/XhviSCx673KC2zc6rvQuFmdbBKwHRdSydcikrToy4qmcVye7i4NUeaL/ESxoA0tQEsYstK9OKzyPyHWv2DeZaefh8BSDCiTFAVj+mDsiNNogVEohOE51joFwTpJTpZULQXMnpTc+pOyAHLE16i1O2srOWACZ0azFyvtg1ExcJlym+1UR615AOhoyKmlTB/nDjKwNt2FV5L6Om9QyoNbecRODdgqFqlfovNTUI="
        )

        wrappedProfile.properties.clear()
        wrappedProfile.properties.put("textures", skinProperty)

        val infoData = PlayerInfoData(
            wrappedProfile,
            0,
            EnumWrappers.NativeGameMode.CREATIVE,
            WrappedChatComponent.fromText("name")
        )

        val infoDataList = listOf(infoData)
        playerInfoActionSet.add(EnumWrappers.PlayerInfoAction.ADD_PLAYER)

        for(i in playerInfoActionSet.indices) {
            entity.playerInfoAction.write(i, playerInfoActionSet.elementAt(i))
        }
        entity.playerInfoDataLists.write(1, infoDataList)

        protocolManager.sendServerPacket(player, entity)
    }
}