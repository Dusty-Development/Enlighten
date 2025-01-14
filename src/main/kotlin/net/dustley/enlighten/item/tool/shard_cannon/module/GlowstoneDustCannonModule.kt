package net.dustley.enlighten.item.tool.shard_cannon.module

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.UseAction
import net.minecraft.world.World

object GlowstoneDustCannonModule : IShardCannonModule {

    override fun isAuto(): Boolean = true

    override fun onFireStart(world: World, user: PlayerEntity, stack: ItemStack) { println("start") }
    override fun onFireTick(world: World, user: PlayerEntity, stack: ItemStack) { println("tick") }
    override fun onFireStop(world: World, user: PlayerEntity, stack: ItemStack) { println("end") }

    override fun getVariant(stack: ItemStack): Float = 0.1f

    override fun getUseAction(stack: ItemStack): UseAction = UseAction.CROSSBOW

}