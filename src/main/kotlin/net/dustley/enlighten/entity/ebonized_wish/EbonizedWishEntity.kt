package net.dustley.enlighten.entity.ebonized_wish

import net.dustley.enlighten.EnlightenCardinalComponents.CONTRACTED_PLAYER
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.math.SpringHelper
import net.dustley.enlighten.particle.ModParticles
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.FlyingItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.joml.Vector3d

class EbonizedWishEntity(
    world: World, entityType: EntityType<out PersistentProjectileEntity>?,
) : PersistentProjectileEntity(entityType, world), FlyingItemEntity {

    //==// PARAMETERS \\==\\
    var entitySoundInstance:EbonizedWishEntitySoundInstance? = null

    //==// CONSTRUCTORS \\==\\
    constructor(world: World, spawnPos:Vec3d, itemStack: ItemStack, entityType: EntityType<out PersistentProjectileEntity>?) : this(world, entityType) {
        stack = itemStack
        setPosition(spawnPos)
    }

    //==// EVENTS \\==\\
    override fun onPlayerCollision(player: PlayerEntity?) { }

    override fun tick() {
//        if(age > 220) age = 20

        isNoClip = true
        inGround = false

        findOwner()
        spawnParticles()
        updateSounds()
        if(owner != null) {
            updateMovement()

            if(owner!!.isSneaking && distanceTo(owner!!) < 5 && age >= 20) {
                if (tryPickup(owner as PlayerEntity)) discard()
            }
        }
        super.tick()
    }

    //==// UTILITY \\==\\
    // Reassign the owner to be correct
    fun findOwner() {
        // Reset the owner
        owner = null

        // Get the contracted player
        val contractedPlayerComponentOptional = CONTRACTED_PLAYER.maybeGet(world.scoreboard)
        if (contractedPlayerComponentOptional.isPresent) {
            val playerUUID = contractedPlayerComponentOptional.get().getValue()
            owner = world.getPlayerByUuid(playerUUID)
        }
    }

    //==// SFX \\==\\
    fun updateSounds() {
        if(entitySoundInstance == null && world.isClient) {
            entitySoundInstance = MinecraftClient.getInstance().player?.let { EbonizedWishEntitySoundInstance(this, it ) }
            MinecraftClient.getInstance().soundManager.play(entitySoundInstance)
        }
    }

    //==// VFX \\==\\
    private fun spawnParticles() {
        if(random.nextBetween(0,4) == 0) {
            for (i in 0..random.nextBetween(0, 2)) {
                world.addParticle(
                    ModParticles.MIRE_DRIP_PARTICLE,
                    true,
                    eyePos.x,
                    eyePos.y,
                    eyePos.z,
                    velocity.x + ((random.nextDouble() - 0.5) * 0.25),
                    velocity.y + ((random.nextDouble() - 0.5) * 0.15),
                    velocity.z + ((random.nextDouble() - 0.5) * 0.25))
            }
        }
    }


    //==// DAMAGE \\==\\
    override fun getEntityCollision(currentPosition: Vec3d?, nextPosition: Vec3d?): EntityHitResult? = null

    //==// MOVEMENT \\==\\
    private fun updateMovement() {
        // Assert the owner as not null
        val owner = owner!!

        val distanceToOwner = distanceTo(owner).toDouble()
        val directionToOwner = owner.pos.subtract(pos).normalize()

        val rotationVector = owner.rotationVector.multiply(1.0,0.0,1.0).normalize().multiply(1.0)
        val offset = rotationVector.rotateY(Math.toRadians(-135.0).toFloat())
        val target = owner.eyePos.add(offset)//.multiply(1.0, 0.0, 1.0).add(0.0, owner.eyeY - (height * 0.5), 0.0)

        val currentPos = Vector3d(pos.x, pos.y, pos.z)
        val targetPos = Vector3d(target.x, target.y, target.z)
        val vel = Vector3d(velocity.x, velocity.y, velocity.z)

        val spring = SpringHelper.calculateSpringForceVector3d(targetPos.sub(currentPos), vel, 1.0, 2.0)

        val force = Vec3d(spring.x, spring.y, spring.z)
        addVelocity(force.multiply(0.1))

        if(velocity.length() > 5) {
            velocity = velocity.normalize().multiply(2.0)
            velocityModified = true
            velocityDirty = true
        }
//        setPosition(target)
    }

    override fun hasNoGravity(): Boolean = true
    override fun isCollidable(): Boolean = false

    override fun collidesWith(other: Entity?): Boolean = false
    override fun doesNotCollide(offsetX: Double, offsetY: Double, offsetZ: Double): Boolean = true

    //==// INVENTORY \\==\\

    // this is hopefully all the stack reference things
    override fun asItemStack(): ItemStack = itemStack.copyComponentsToNewStack(itemStack.item, 1)
    override fun getWeaponStack(): ItemStack = this.itemStack
    override fun getStack(): ItemStack = this.itemStack
    override fun getDefaultItemStack(): ItemStack = ItemStack(ModItems.EBONIZED_WISH)

}