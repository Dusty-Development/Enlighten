package net.dustley.enlighten.item.tool.volumetric_anchor

import net.dustley.enlighten.item.ModItemComponents
import net.minecraft.block.Blocks
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.network.packet.s2c.play.PositionFlag
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.World.NETHER
import net.minecraft.world.World.OVERWORLD


/*
 * The volumetric anchor allows the user to move through the nether / over world easily
 * If you have a flint and steel and the item is charged with glow stone you can teleport between dimensions keeping your place
 *
 */
class VolumetricAnchorItem : Item(createItemSettings()) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val otherHand = if(hand == Hand.OFF_HAND) Hand.MAIN_HAND else Hand.OFF_HAND
        val otherItemStack = user.getStackInHand(otherHand)
        val itemStack = user.getStackInHand(hand)

        // Add charge if holding a available item
        val availableCharge = getChargeAmountFromItem(otherItemStack)
        if(availableCharge > 0 && getChargeCount(itemStack) < MAX_CHARGE_COUNT) {
            setChargeCount(itemStack, getChargeCount(itemStack) + availableCharge)
            otherItemStack.decrement(1)
            world.playSound(user, user.x, user.y, user.z, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS)
            return TypedActionResult.consume(itemStack)
        }

        // Check if we have a starter stack (flint and steel or a fire charge
        var fireStack:ItemStack = ItemStack.EMPTY
        for (i in 0 until user.inventory.size()) {
            val invStack: ItemStack = user.inventory.getStack(i)
            if (invStack.isOf(Items.FLINT_AND_STEEL) || invStack.isOf(Items.FIRE_CHARGE)) {
                fireStack = invStack
                break
            }
        }

        // Damage the stack and teleport
        val hasEnoughCharge = getChargeCount(itemStack) >= 1
        if (!fireStack.isEmpty && hasEnoughCharge) {
            if(fireStack.isOf(Items.FIRE_CHARGE)) fireStack.decrementUnlessCreative(1, user)
            if(fireStack.isOf(Items.FLINT_AND_STEEL)) fireStack.damage(1, user, EquipmentSlot.MAINHAND)
            setChargeCount(itemStack, getChargeCount(itemStack) - 1)

            // Teleport the wish_contract
            if(world is ServerWorld) {
                val server = world.server
                val earthWorld = server.getWorld(OVERWORLD)
                val netherWorld = server.getWorld(NETHER)

                if(world == earthWorld) netherWorld?.let { teleportUserToWorld(it, user, 1.0/netherWorld.dimension.coordinateScale) }
                if(world == netherWorld) earthWorld?.let { teleportUserToWorld(it, user, netherWorld.dimension.coordinateScale) }
            }
        } else {
            world.playSound(user, user.x, user.y, user.z, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS)
        }

        return super.use(world, user, hand)
    }

    fun teleportUserToWorld(world: ServerWorld, user: PlayerEntity, scale: Double) {
        val tpSpot = findClosestSafeSpot(world, BlockPos.ofFloored(user.pos.multiply(scale).multiply(1.0,0.0,1.0).add(0.0,Math.clamp(user.pos.getY(), world.bottomY + 5.0, world.topY + 5.0),0.0)), 50).up().toCenterPos()

        val flags: Set<PositionFlag> = setOf()
        user.teleport(world, tpSpot.x, tpSpot.y, tpSpot.z, flags, user.yaw, user.pitch)
    }

    fun findClosestSafeSpot(world: ServerWorld, startPos: BlockPos, maxDistance: Int): BlockPos {
        for (radius in 0..maxDistance) {
            for (pos in BlockPos.iterateOutwards(startPos, radius, radius, radius)) {
                if (isSafeToTeleport(world, pos)) {
                    return pos
                }
            }
        }

        // No spot found within range, clear blocks at start position
        clearBlocks(world, startPos)
        return startPos
    }

    private fun isSafeToTeleport(world: ServerWorld, pos: BlockPos): Boolean {
        // Check if block is solid ground and there's enough space above
        return world.getBlockState(pos).isSolidBlock(world, pos) &&
                world.isAir(pos.up(1)) &&
                world.isAir(pos.up(2))
    }

    private fun clearBlocks(world: ServerWorld, startPos: BlockPos) {
        for (y in 0..1) {
            val clearPos = startPos.up(y)
            world.breakBlock(clearPos, true)
        }
        if(world.isAir(startPos.down())) world.setBlockState(startPos.down(), Blocks.COBBLESTONE.defaultState)
    }

    fun getChargeAmountFromItem(otherStack: ItemStack):Int {
        if(otherStack.isOf(Items.GLOWSTONE_DUST)) return 1
        if(otherStack.isOf(Items.GLOWSTONE)) return 4
        return 0
    }

    // Charge count
    fun getChargeCount(stack: ItemStack): Int = stack.get(ModItemComponents.CHARGE_COUNT) ?: 0
    fun setChargeCount(stack: ItemStack, value: Int) = stack.set(ModItemComponents.CHARGE_COUNT, Math.clamp(value.toLong(), 0, MAX_CHARGE_COUNT))

    companion object {
        const val MAX_CHARGE_COUNT = 4

        fun createItemSettings(): Settings = Settings()
            .component(ModItemComponents.CHARGE_COUNT, 0)
            .rarity(Rarity.EPIC)
            .maxDamage(1024)

    }
}