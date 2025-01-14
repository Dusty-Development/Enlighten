package net.dustley.enlighten.item.tool.shard_cannon

import net.dustley.enlighten.item.tool.shard_cannon.module.EmptyShardCannonModule
import net.dustley.enlighten.item.tool.shard_cannon.module.GlowstoneDustCannonModule
import net.dustley.enlighten.item.tool.shard_cannon.module.IShardCannonModule
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object ShardCannonAmmoHelper {

    fun getModuleFromStack(itemStack: ItemStack) : IShardCannonModule {
        return when {
            itemStack.isOf(Items.ECHO_SHARD)        -> EmptyShardCannonModule
            itemStack.isOf(Items.AMETHYST_SHARD)    -> EmptyShardCannonModule
            itemStack.isOf(Items.PRISMARINE_SHARD)  -> EmptyShardCannonModule
            itemStack.isOf(Items.DIAMOND)           -> EmptyShardCannonModule
            itemStack.isOf(Items.GLOWSTONE_DUST)    -> GlowstoneDustCannonModule
            itemStack.isOf(Items.QUARTZ)            -> EmptyShardCannonModule
            else -> EmptyShardCannonModule
        }
    }

}