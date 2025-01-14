package net.dustley.enlighten.component

import net.dustley.enlighten.particle.ModParticles
import net.minecraft.client.MinecraftClient
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.RegistryByteBuf
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.RegistryWrapper
import net.minecraft.scoreboard.Scoreboard
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.Vec3d
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent
import java.util.*


class ContractedPlayerComponent(scoreboard: Scoreboard, server:MinecraftServer?) : UUIDComponent, AutoSyncedComponent {
    private var playerUUID:UUID = UUID.randomUUID()
    private var deathCount:Int = 0
    private var isAssigned:Boolean = false

    override fun getIsAssigned(): Boolean = isAssigned
    override fun setIsAssigned(value: Boolean) { isAssigned = value }

    override fun getValue(): UUID = playerUUID
    override fun setValue(value: UUID) {
        if(!isAssigned) {
            playerUUID = value
            setIsAssigned(true)
        }
    }

    override fun getDeathCount(): Int = deathCount
    override fun setDeathCount(value: Int) { deathCount = value }

    override fun readFromNbt(tag: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) { playerUUID = tag.getUuid("contracted_player"); isAssigned = tag.getBoolean("contracted_player_assigned")}
    override fun writeToNbt(tag: NbtCompound, registryLookup: RegistryWrapper.WrapperLookup) { tag.putUuid("contracted_player", playerUUID); tag.putBoolean("contracted_player_assigned", isAssigned)}

    // Sync implementation: only sync the players' own data
    override fun shouldSyncWith(player: ServerPlayerEntity): Boolean = true

    override fun writeSyncPacket(buf: RegistryByteBuf, recipient: ServerPlayerEntity) {
        buf.writeUuid(playerUUID)
        buf.writeBoolean(isAssigned)
        buf.writeInt(deathCount)
    }

    override fun applySyncPacket(buf: RegistryByteBuf) {
        playerUUID = buf.readUuid()
        isAssigned = buf.readBoolean()
        deathCount = buf.readInt()

        val world = MinecraftClient.getInstance().world
        val player = world?.getPlayerByUuid(playerUUID)
        val pos = player?.pos

        if(world != null && player != null && pos != null) {
            for (i in 0..500) {
                var vel = Vec3d(world.random.nextDouble() * 0.35, (world.random.nextDouble() * 0.75) + 0.75, 0.0)
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