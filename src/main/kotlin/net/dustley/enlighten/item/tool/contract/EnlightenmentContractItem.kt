package net.dustley.enlighten.item.tool.contract

import net.dustley.enlighten.item.tool.begrimed_chorus_fruit.BegrimedChorusFruitItem
import net.dustley.enlighten.particle.ModParticles
import net.dustley.enlighten.world.ModDimensions
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Rarity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World


class EnlightenmentContractItem : Item(createItemSettings()) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if(isPedestalComplete(context)) onSuccess(context) else onFailure(context)
        return super.useOnBlock(context)
    }

    private fun onSuccess(context: ItemUsageContext) {
        val player = context.player

        player?.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE)
        player?.playSound(SoundEvents.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE)
        spawnSuccessParticles(context)

        player?.kill()

        val server = player?.server ?: return
        val targetWorld = server.getWorld(ModDimensions.WORLD_KEY) ?: return
        BegrimedChorusFruitItem.teleportUser(player, targetWorld, player.pos)

        context.player?.sendMessage(Text.literal("Binding Complete!").fillStyle(Style.EMPTY.withColor(0xd696c5)), true)
    }

    private fun onFailure(context: ItemUsageContext) {
        val world = context.world
        val player = context.player
        val pos = context.player?.pos ?: Vec3d.ZERO

        player?.playSound(SoundEvents.ITEM_TOTEM_USE)
        world.createExplosion(player, pos.x, pos.y, pos.z, 2f, World.ExplosionSourceType.BLOCK)

        context.player?.sendMessage(Text.literal("Binding Incomplete!").fillStyle(Style.EMPTY.withColor(0xd696c5)), true)
    }

    private fun spawnSuccessParticles(context: ItemUsageContext) {
        val world = context.world
        val pos = context.player!!.pos

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

    // Checks if the pedestal you interact with is completed
    private fun isPedestalComplete(context: ItemUsageContext):Boolean {
        val world = context.world
        val blockPos = context.blockPos

        val isValid = getIsBlock(world, blockPos, Blocks.LODESTONE)
        return isValid
    }

    // Simple utility to make code shorter
    private fun getIsBlock(world: World, blockPos: BlockPos, block: Block):Boolean = world.getBlockState(blockPos).isOf(block)

    companion object {
        fun createItemSettings(): Settings = Settings()
            .rarity(Rarity.EPIC)
            .maxDamage(1024)
    }
}