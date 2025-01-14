package net.dustley.enlighten.item.tool.shard_cannon

import net.dustley.enlighten.item.ModItemComponents
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class ShardCannonItem: Item(createItemSettings()) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val weaponStack = user.getStackInHand(hand)
        val ammoStack = getLoadedStack(weaponStack)
        val module = ShardCannonAmmoHelper.getModuleFromStack(ammoStack)

        println("B")
        module.onFireStart(world, user, weaponStack)
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        setFiringTicks(weaponStack, 0)
        setIsFiring(weaponStack, true)
        return super.use(world, user, hand)
    }

    override fun onStoppedUsing(weaponStack: ItemStack, world: World, user: LivingEntity, remainingUseTicks: Int) {
        super.onStoppedUsing(weaponStack, world, user, remainingUseTicks)
        println("S")

        val ammoStack = getLoadedStack(weaponStack)
        val module = ShardCannonAmmoHelper.getModuleFromStack(ammoStack)

        module.onFireStop(world, user as PlayerEntity, weaponStack)
        setFiringTicks(weaponStack, -1)
        setIsFiring(weaponStack, false)
    }

    override fun usageTick(world: World, user: LivingEntity, weaponStack: ItemStack, remainingUseTicks: Int) {
        super.usageTick(world, user, weaponStack, remainingUseTicks)

        println("A")
        val ammoStack = getLoadedStack(weaponStack)
        val module = ShardCannonAmmoHelper.getModuleFromStack(ammoStack)

        if(module.isAuto() && getIsFiring(weaponStack)) {
            module.onFireTick(world, user as PlayerEntity, weaponStack)
            setFiringTicks(weaponStack, getFiringTicks(weaponStack) + 1)
        }
    }

    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        println("T")
        return super.finishUsing(stack, world, user)
    }

    override fun getMaxUseTime(stack: ItemStack, user: LivingEntity): Int = 72000
    override fun isUsedOnRelease(stack: ItemStack): Boolean = super.isUsedOnRelease(stack)

    // Handle the components
    private fun getLoadedStack(weaponStack: ItemStack):ItemStack = weaponStack.get(ModItemComponents.CANNON_STACK) ?: Items.GLOWSTONE_DUST.defaultStack.copyWithCount(64)
    private fun setLoadedStack(weaponStack: ItemStack, ammo: ItemStack) { weaponStack.set(ModItemComponents.CANNON_STACK, ammo) }

    private fun getIsFiring(weaponStack: ItemStack):Boolean = weaponStack.get(ModItemComponents.IS_CANNON_FIRING) ?: false
    private fun setIsFiring(weaponStack: ItemStack, isFiring: Boolean) { weaponStack.set(ModItemComponents.IS_CANNON_FIRING, isFiring) }

    private fun getFiringTicks(weaponStack: ItemStack):Int = weaponStack.get(ModItemComponents.CANNON_FIRING_TICKS) ?: -1
    private fun setFiringTicks(weaponStack: ItemStack, tick:Int) { weaponStack.set(ModItemComponents.CANNON_FIRING_TICKS, tick) }

    companion object {

        fun createItemSettings(): Settings = Settings()
            .component(ModItemComponents.CANNON_STACK, Items.GLOWSTONE_DUST.defaultStack.copyWithCount(64)) // TODO: make a way to change this
            .component(ModItemComponents.IS_CANNON_FIRING, false)
            .component(ModItemComponents.CANNON_FIRING_TICKS, -1)
            .rarity(Rarity.EPIC)
            .maxDamage(1024)

    }
}