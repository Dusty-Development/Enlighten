package net.dustley.enlighten.particle.mire

import net.dustley.enlighten.particle.ModParticles
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType

class MireDripParticle(world: ClientWorld?, x: Double, y: Double, z: Double, private var sprites: SpriteProvider?, velocityX: Double, velocityY: Double, velocityZ: Double) : SpriteBillboardParticle(world, x, y, z, velocityX, velocityY, velocityZ) {

     init {
         this.velocityX = velocityX
         this.velocityY = velocityY
         this.velocityZ = velocityZ
         this.gravityStrength = 0.07f
         this.collidesWithWorld = true;
         this.maxAge = ((64.0 / (Math.random() * 0.8 + 0.2)).toInt())
         this.setSpriteForAge(sprites)
    }

    override fun tick() {
        this.prevPosX = this.x
        this.prevPosY = this.y
        this.prevPosZ = this.z

        this.updateAge()
        if (!this.dead) {
            this.velocityY -= gravityStrength.toDouble()
            this.move(this.velocityX, this.velocityY, this.velocityZ)
            this.updateVelocity()
        }
    }

    override fun getRotator(): Rotator = Rotator.ALL_AXIS

    fun updateAge() {
        if (maxAge-- <= 0) {
            this.markDead()
        }
    }

    fun updateVelocity() {
        if (this.onGround) {
            this.markDead()
            world.addParticle(ModParticles.MIRE_SPLAT_PARTICLE, this.x, this.y, this.z, 0.0, 0.0, 0.0)
        }
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    companion object {
        @Environment(EnvType.CLIENT)
        class Factory(private val sprites: SpriteProvider) : ParticleFactory<SimpleParticleType> {
            override fun createParticle(
                particleType: SimpleParticleType?, level: ClientWorld, x: Double, y: Double, z: Double,
                dx: Double, dy: Double, dz: Double,
            ): Particle {
                return MireDripParticle(level, x, y, z, this.sprites, dx, dy, dz)
            }
        }
    }

}