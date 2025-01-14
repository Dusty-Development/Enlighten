package net.dustley.enlighten.item.tool.snatch_slate

import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.network.s2c.SnatchSlateClientPacketPayload
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Box
import net.minecraft.world.World


class SnatchSlateItem : Item(createItemSettings()) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if(!world.isClient) {
            val to = user.id
            val fromEntity = getClosestPlayerEntity(user)
            val from = fromEntity?.id
            val stack = fromEntity?.mainHandStack

            if(from != null && stack?.isEmpty == false) {
                for (player in world.players) {
                    ServerPlayNetworking.send(player as ServerPlayerEntity?, SnatchSlateClientPacketPayload(to, from, stack))
                    snatch(fromEntity, user, stack, world)
                }
            }
        }

        return super.use(world, user, hand)
    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        if(entity is PlayerEntity) {
            val fromEntity = getClosestPlayerEntity(entity)
            if(fromEntity != null) entity.sendMessage(Text.of("Found player: ${fromEntity.nameForScoreboard}"), true)
        }
    }

    companion object {

        fun createItemSettings(): Settings = Settings()
            .rarity(Rarity.EPIC)
            .maxDamage(1024)

        fun snatch(playerFrom:PlayerEntity, playerTo:PlayerEntity, stack: ItemStack, world: World) {
            playerFrom.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY)
            playerTo.setStackInHand(Hand.MAIN_HAND, stack)

            val distance = playerFrom.distanceTo(playerTo)
            val particles = distance * 5
            for (i in 0..particles.toInt()) {
                val step = distance / particles
                val particleDir = playerFrom.pos.add(0.0, playerTo.height * 0.5, .0).subtract(playerTo.pos.add(0.0, playerTo.height * 0.5, .0)).normalize()
                val particlePos = playerFrom.pos.add(0.0, playerFrom.height * 0.5, .0).add(particleDir.multiply((i * step).toDouble()))
                val speed = particleDir.multiply((world.random.nextDouble() - 0.5) * 1)
                world.addParticle(ParticleTypes.SOUL, particlePos.x, particlePos.y, particlePos.z, speed.x, speed.y, speed.z)
            }

            playerTo.itemCooldownManager.set(ModItems.SNATCH_SLATE, (20 * 60) * 30) // (20 * 60) * 30
        }

        fun getClosestPlayerEntity(player: PlayerEntity): PlayerEntity? {
            val playerPos = player.pos
            val searchBox = Box(playerPos.subtract(15.0,15.0,15.0), playerPos.add(15.0,15.0,15.0))
            return player.world.getEntitiesByClass(
                PlayerEntity::class.java, searchBox
            ) { e: PlayerEntity -> e !== player }.stream()
                .min { e1: PlayerEntity?, e2: PlayerEntity? ->
                    e1!!.squaredDistanceTo(player).compareTo(e2!!.squaredDistanceTo(player))
                }
                .orElse(null)
        }

    }
}