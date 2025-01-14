package net.dustley.enlighten.network.s2c

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.particle.ModParticles
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.Vec3d


class WishKillClientPacketPayload(val pos:Vec3d, val stack: ItemStack) : CustomPayload {

    constructor(buf: RegistryByteBuf) : this(buf.readVec3d(), if (buf.readBoolean()) ItemStack.EMPTY else ItemStack.PACKET_CODEC.decode(buf))

    fun write(buf: RegistryByteBuf) {
        buf.writeVec3d(pos)
        buf.writeBoolean(stack.isEmpty)
        if (!stack.isEmpty) {
            ItemStack.PACKET_CODEC.encode(buf, stack)
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?> { return WISH_KILL_CLIENT_PACKET_ID }

    companion object {
        val WISH_KILL_CLIENT_PACKET_ID: CustomPayload.Id<WishKillClientPacketPayload> = CustomPayload.Id(Enlighten.identifier("wish_kill_client_packet"))
        val CODEC: PacketCodec<in RegistryByteBuf, WishKillClientPacketPayload> = PacketCodec.of(WishKillClientPacketPayload::write, ::WishKillClientPacketPayload)

        fun receive(payload: WishKillClientPacketPayload, context: ClientPlayNetworking.Context) {
            val pos: Vec3d = payload.pos
            val itemStack: ItemStack = payload.stack

            context.client().execute {
                val world = context.player().world
                for (i in 0..500) {
                    var vel = Vec3d(world.random.nextDouble() * 0.35,(world.random.nextDouble() * 0.75) + 0.75,0.0)
                    val radAngle = Math.toRadians(world.random.nextDouble() * 360)
                    vel = vel.rotateY(radAngle.toFloat())

                    world.addParticle(ModParticles.MIRE_DRIP_PARTICLE, true, pos.x, pos.y, pos.z, vel.x, vel.y, vel.z)
                }

                for (i in 0..50) {
                    val x = pos.x + (world.random.nextDouble() - 0.5)
                    val y = pos.y + (world.random.nextDouble() * 2)
                    val z = pos.z + (world.random.nextDouble() - 0.5)
                    world.addParticle(ParticleTypes.SQUID_INK, true, x, y, z, 0.0, 0.0, 0.0)
                }
            }
        }
    }
}