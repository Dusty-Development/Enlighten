package net.dustley.enlighten.item.tool.shard_cannon.module

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.UseAction
import net.minecraft.world.World

object EmptyShardCannonModule : IShardCannonModule {

    override fun isAuto(): Boolean = false

    override fun onFireStart(world: World, user: PlayerEntity, stack: ItemStack) { }
    override fun onFireTick(world: World, user: PlayerEntity, stack: ItemStack) { }
    override fun onFireStop(world: World, user: PlayerEntity, stack: ItemStack) { }

    override fun getVariant(stack: ItemStack): Float = 0f

    override fun getUseAction(stack: ItemStack): UseAction = UseAction.NONE

}