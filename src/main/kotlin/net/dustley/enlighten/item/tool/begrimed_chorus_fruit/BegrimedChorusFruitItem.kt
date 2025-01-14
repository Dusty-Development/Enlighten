package net.dustley.enlighten.item.tool.begrimed_chorus_fruit

import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.world.ModDimensions
import net.minecraft.block.ShapeContext
import net.minecraft.component.type.FoodComponents
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ChorusFruitItem
import net.minecraft.item.ItemStack
import net.minecraft.network.packet.s2c.play.PositionFlag
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext
import net.minecraft.world.World

class BegrimedChorusFruitItem : ChorusFruitItem(Settings().food(FoodComponents.CHORUS_FRUIT)) {

    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        val returnStack = super.finishUsing(stack, world, user)

        if(world is ServerWorld) {
            var server = world.server
            var earthWorld = server.overworld
            var abyssWorld = server.getWorld(ModDimensions.WORLD_KEY)

            var armorStack = user.getEquippedStack(EquipmentSlot.CHEST)

            if(armorStack.isOf(ModItems.MIRE_CLOAK)) useWithCloakOn(stack, world, user, earthWorld, abyssWorld) else useWithoutCloak(stack, world, user, earthWorld, abyssWorld)
        }

        return returnStack
    }

    fun useWithCloakOn(stack: ItemStack, world: World, user: LivingEntity, earthWorld: ServerWorld, abyssWorld: ServerWorld?) {
        if(world == abyssWorld) teleportUser(user, earthWorld) else abyssWorld?.let { teleportUser(user, it) }
    }

    fun useWithoutCloak(stack: ItemStack, world: World, user: LivingEntity, earthWorld: ServerWorld, abyssWorld: ServerWorld?) {
        if(world == abyssWorld) {
            teleportUser(user,earthWorld)
        } else {
            user.kill()
            if (abyssWorld != null) teleportUser(user, abyssWorld)
        }
    }


    companion object {

        // In here because I couldn't be fucked making a util class
        // This is how we teleport to places with the falling and everything
        fun teleportUser(user: LivingEntity, world: ServerWorld, position: Vec3d) {
            // Do a ray cast from the top of the world to the bottom to find the top block
            val start = position.multiply(1.0,0.0,1.0).add(0.0,world.topY.toDouble(),0.0)
            val end = position.multiply(1.0,0.0,1.0).add(0.0,world.bottomY.toDouble(),0.0)
            val context = RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, ShapeContext.of(user))
            val y = world.raycast(context).pos.y + 100.0
            // Teleport the wish_contract
            val flags: Set<PositionFlag> = setOf()
            user.teleport(world, position.x, y, position.z, flags, user.yaw, user.pitch)
            user.addStatusEffect(StatusEffectInstance(StatusEffects.SLOW_FALLING, 260, 0, true, false))
        }
        fun teleportUser(user: LivingEntity, world: ServerWorld) = teleportUser(user,world,user.pos)


    }

}