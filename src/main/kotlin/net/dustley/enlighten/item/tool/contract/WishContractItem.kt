package net.dustley.enlighten.item.tool.contract

import net.dustley.enlighten.EnlightenCardinalComponents
import net.dustley.enlighten.block.ModBlocks
import net.dustley.enlighten.entity.ModEntities
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntity
import net.dustley.enlighten.item.ModItems
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.RespawnAnchorBlock
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Rarity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.jvm.optionals.getOrNull


class WishContractItem : Item(createItemSettings()) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val component = EnlightenCardinalComponents.CONTRACTED_PLAYER.maybeGet(context.world.scoreboard)
        if(component.isPresent) {
            println(component.get().getValue())
        }

        if(isPedestalComplete(context)) onSuccess(context) else onFailure(context)
        return super.useOnBlock(context)
    }

    private fun onSuccess(context: ItemUsageContext) {
        val world = context.world
        val player = context.player

        player?.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE)
        player?.playSound(SoundEvents.BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE)

        val component = EnlightenCardinalComponents.CONTRACTED_PLAYER.maybeGet(context.world.scoreboard)
        if(component.isPresent) {
            if(!component.get().getIsAssigned()) {
                val entity = EbonizedWishEntity(world, context.blockPos.toCenterPos(), ModItems.EBONIZED_WISH.defaultStack, ModEntities.EBONIZED_WISH)
                world.spawnEntity(entity)
            }

            player?.let { component.get().setValue(it.uuid) }
            component.get().setDeathCount(0)
        }

        if(!world.isClient) {
            EnlightenCardinalComponents.CONTRACTED_PLAYER.sync(world.scoreboard)
        }

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

    // Checks if the pedestal you interact with is completed
    private fun isPedestalComplete(context: ItemUsageContext):Boolean {
        val world = context.world
        val blockPos = context.blockPos

        var isValid =
            getIsBlock(world, blockPos, Blocks.LODESTONE) &&
            world.getBlockState(blockPos.down(1)).isOf(ModBlocks.LIGHT_CHROMATIC_PILLAR) &&
            world.getBlockState(blockPos.down(2)).isOf(ModBlocks.CHROMATIC_PILLAR) &&
            world.getBlockState(blockPos.down(3)).isOf(ModBlocks.DARK_CHROMATIC_PILLAR) &&
            world.getBlockState(blockPos.down(4)).isOf(Blocks.RESPAWN_ANCHOR) &&
            world.getBlockState(blockPos.down(4)).get(RespawnAnchorBlock.CHARGES) == 3

        val component = EnlightenCardinalComponents.CONTRACTED_PLAYER.maybeGet(context.world.scoreboard).getOrNull()
        if(component?.getIsAssigned() == true && context.player != world.getPlayerByUuid(component.getValue())) { isValid = false }

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