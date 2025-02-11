package me.zaksen.skillify_core.api.config.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer

// TODO - Made PRD serialization into human readable format
class PersistentDataContainerSerializer: KSerializer<PersistentDataContainer> {
    private val delegateSerializer = ByteArraySerializer()
    override val descriptor: SerialDescriptor = delegateSerializer.descriptor

    override fun deserialize(decoder: Decoder): PersistentDataContainer {
        val byteArray = decoder.decodeSerializableValue(delegateSerializer)
        val pdc = ItemStack(Material.BEDROCK).itemMeta.persistentDataContainer

        pdc.readFromBytes(byteArray, true)
        return pdc
    }

    override fun serialize(encoder: Encoder, value: PersistentDataContainer) {
        encoder.encodeSerializableValue(delegateSerializer, value.serializeToBytes())
    }
}

/** Data type for easier serialization PersistentDataContainer */
typealias PersistentDataContainerValue = @Serializable(PersistentDataContainerSerializer::class) PersistentDataContainer