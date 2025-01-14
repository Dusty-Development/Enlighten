package net.dustley.enlighten.item.tool.shard_cannon.module

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.UseAction
import net.minecraft.world.World

interface IShardCannonModule {

    // Damage
    fun isAuto():Boolean // should the tick run
    fun onFireStart(world: World, user: PlayerEntity, stack: ItemStack)
    fun onFireTick(world: World, user: PlayerEntity, stack: ItemStack)
    fun onFireStop(world: World, user: PlayerEntity, stack: ItemStack)

    // Visual
    fun getVariant(stack: ItemStack) : Float
    fun getUseAction(stack: ItemStack): UseAction

}