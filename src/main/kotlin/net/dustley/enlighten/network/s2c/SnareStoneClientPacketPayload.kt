package net.dustley.enlighten.network.s2c

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.item.tool.snare_stone.SnareStoneItem.Companion.setSnaredEntityID
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

class SnareStoneClientPacketPayload(val snatchedID:Int, val snatcherID:Int, val stack: ItemStack) : CustomPayload {

    constructor(buf: RegistryByteBuf) : this(buf.readInt(), buf.readInt(), if (buf.readBoolean()) ItemStack.EMPTY else ItemStack.PACKET_CODEC.decode(buf))

    fun write(buf: RegistryByteBuf) {
        buf.writeInt(snatchedID)
        buf.writeInt(snatcherID)
        buf.writeBoolean(stack.isEmpty)
        if (!stack.isEmpty) ItemStack.PACKET_CODEC.encode(buf, stack)
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?> { return SNARE_STONE_CLIENT_PACKET_ID }

    companion object {
        val SNARE_STONE_CLIENT_PACKET_ID: CustomPayload.Id<SnareStoneClientPacketPayload> = CustomPayload.Id(Enlighten.identifier("snare_stone_client_packet"))
        val CODEC: PacketCodec<in RegistryByteBuf, SnareStoneClientPacketPayload> = PacketCodec.of(SnareStoneClientPacketPayload::write, ::SnareStoneClientPacketPayload)

        fun receive(payload: SnareStoneClientPacketPayload, context: ClientPlayNetworking.Context) {
            val snatched = payload.snatchedID
            val itemStack: ItemStack = payload.stack

            context.client().execute {
                setSnaredEntityID(itemStack, snatched)
            }
        }
    }
}