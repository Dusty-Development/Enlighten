package net.dustley.enlighten.network.s2c

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.item.tool.snatch_slate.SnatchSlateItem
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload


class SnatchSlateClientPacketPayload(val idTo:Int, val idFrom:Int, val stack: ItemStack) : CustomPayload {

    constructor(buf: RegistryByteBuf) : this(buf.readInt(), buf.readInt(), if (buf.readBoolean()) ItemStack.EMPTY else ItemStack.PACKET_CODEC.decode(buf))

    fun write(buf: RegistryByteBuf) {
        buf.writeInt(idTo)
        buf.writeInt(idFrom)
        buf.writeBoolean(stack.isEmpty)
        if (!stack.isEmpty) ItemStack.PACKET_CODEC.encode(buf, stack)
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?> { return SNATCH_SLATE_CLIENT_PACKET_ID }

    companion object {
        val SNATCH_SLATE_CLIENT_PACKET_ID: CustomPayload.Id<SnatchSlateClientPacketPayload> = CustomPayload.Id(Enlighten.identifier("snatch_slate_client_packet"))
        val CODEC: PacketCodec<in RegistryByteBuf, SnatchSlateClientPacketPayload> = PacketCodec.of(SnatchSlateClientPacketPayload::write, ::SnatchSlateClientPacketPayload)

        fun receive(payload: SnatchSlateClientPacketPayload, context: ClientPlayNetworking.Context) {
            val to = payload.idTo
            val from = payload.idFrom
            val itemStack: ItemStack = payload.stack

            context.client().execute {
                val world = context.player().world
                val entityFrom = world.getEntityById(from) as PlayerEntity
                val entityTo = world.getEntityById(to) as PlayerEntity

                SnatchSlateItem.snatch(entityFrom, entityTo, itemStack, world)
            }
        }
    }
}