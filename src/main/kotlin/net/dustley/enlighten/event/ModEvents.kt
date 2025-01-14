package net.dustley.enlighten.event

import net.dustley.enlighten.EnlightenCardinalComponents
import net.dustley.enlighten.item.ModItemComponents
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.item.tool.begrimed_chorus_fruit.BegrimedChorusFruitItem
import net.dustley.enlighten.item.tool.snare_stone.SnareStoneItem.Companion.MAX_DISTANCE
import net.dustley.enlighten.world.ModDimensions
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World


object ModEvents {

    private fun onPlayerRespawn(oldPlayer: ServerPlayerEntity, player: ServerPlayerEntity) {
        val server = player.server ?: return
        val targetWorld = server.getWorld(ModDimensions.WORLD_KEY) ?: return

        if(oldPlayer.world.registryKey == ModDimensions.WORLD_KEY) {
            val deathPos = if(oldPlayer.lastDeathPos.isPresent) oldPlayer.lastDeathPos.get().pos.toCenterPos() else oldPlayer.pos
            BegrimedChorusFruitItem.teleportUser(player, targetWorld, deathPos)
        }

        val maybeComponent = EnlightenCardinalComponents.CONTRACTED_PLAYER.maybeGet(player.world.scoreboard)
        if(maybeComponent.isPresent) {
            val component = maybeComponent.get()
            if(component.getValue() == player.uuid && component.getIsAssigned()){
                player.sendMessage(Text.of("Deaths until Wish voided: ${5-component.getDeathCount()}"), true)
                component.setDeathCount(component.getDeathCount() + 1)
                if(component.getDeathCount() > 5) {
                    component.setDeathCount(0)
                    component.setIsAssigned(false)
                }
            }
        }

    }

    fun tick(world: World) {
        world.players.forEach { player ->
            val mainStack = player.mainHandStack
            val offStack = player.offHandStack

            if(mainStack.isOf(ModItems.SNARE_STONE)) updateSnareStone(world,player,mainStack)
            if(offStack.isOf(ModItems.SNARE_STONE)) updateSnareStone(world,player,offStack)
        }
    }

    fun updateSnareStone(world:World, entity: PlayerEntity, stack:ItemStack) {
        val id = stack.get(ModItemComponents.ENTITY_ID) ?: -1

        val snaredEntity = world.getEntityById(id)

        if(snaredEntity != null && entity.isHolding(ModItems.SNARE_STONE)) {
            val entityPos = snaredEntity.pos
            val distance = entityPos.distanceTo(entity.pos)
            val direction = entityPos.subtract(entity.pos).normalize()

            if (distance >= MAX_DISTANCE) {
                snaredEntity.velocity = reflectVelocity(snaredEntity.velocity, direction)

                val correction = direction.multiply((MAX_DISTANCE - distance))
                val pos = entityPos.add(correction)
                snaredEntity.setPos(pos.x, pos.y, pos.z)
            }

            val particles = distance * 3
            for (i in 0..particles.toInt()) {
                val step = distance / particles
                val particleDir = entityPos.add(0.0, snaredEntity.height * 0.5, .0).subtract(entity.pos.add(0.0, entity.height * 0.5, .0)).normalize()
                val particlePos = entity.pos.add(0.0, entity.height * 0.5, .0).add(particleDir.multiply(i * step))
                val speed = particleDir.multiply((world.random.nextDouble() - 0.5) * 1)
                world.addParticle(ParticleTypes.ENCHANT, particlePos.x, particlePos.y, particlePos.z, speed.x, speed.y, speed.z)
            }

            if(distance >= MAX_DISTANCE * 2) stack.set(ModItemComponents.ENTITY_ID, -1)
        } else {
            stack.set(ModItemComponents.ENTITY_ID, -1)
        }
    }


    private fun reflectVelocity(velocity: Vec3d, normal: Vec3d): Vec3d {
        // Normalize the normal to ensure proper reflection
        val normalizedNormal = normal.normalize()
        // Calculate the reflection using the formula
        return velocity.subtract(normalizedNormal.multiply(2 * velocity.dotProduct(normalizedNormal)))
    }

    fun registerModEvents() {
        ServerPlayerEvents.AFTER_RESPAWN.register { oldPlayer, newPlayer, _ -> onPlayerRespawn(oldPlayer, newPlayer) }
        ServerTickEvents.START_WORLD_TICK.register { world -> if(!world.isClient) tick(world) }
    }

    fun registerClientModEvents() {
        ClientTickEvents.START_WORLD_TICK.register { world -> if(world.isClient) tick(world) }
    }

}