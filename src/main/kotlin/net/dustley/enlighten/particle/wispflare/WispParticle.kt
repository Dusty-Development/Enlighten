package net.dustley.enlighten.particle.wispflare

import foundry.veil.api.client.render.VeilRenderSystem
import foundry.veil.api.client.render.light.PointLight
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.command.argument.ColorArgumentType.color
import net.minecraft.particle.SimpleParticleType
import java.awt.Color


class WispParticle(world: ClientWorld?, x: Double, y: Double, z: Double, private var sprites: SpriteProvider?, velocityX: Double, velocityY: Double, velocityZ: Double) : SpriteBillboardParticle(world, x, y, z, velocityX, velocityY, velocityZ) {

    var light: PointLight? = null

     init {
         this.velocityX = velocityX
         this.velocityY = velocityY
         this.velocityZ = velocityZ
         this.gravityStrength = 0.001f
         this.collidesWithWorld = true;
         this.maxAge = ((64.0 / (Math.random() * 0.8 + 0.2)).toInt())
         this.setSpriteForAge(sprites)

         val hue = (random.nextFloat() * 0.25f) + 0.5f
         val color = Color.getHSBColor(Math.clamp(hue, 0f, 1f),1f, 1f)

         this.light = PointLight()
         light!!.setBrightness(random.nextFloat() * 0.25f + 0.75f)
         light!!.setColor(color.rgb)
         light!!.setRadius((random.nextFloat() * 2f) + 4f)
         light!!.setPosition(x, y, z)
         VeilRenderSystem.renderer().lightRenderer.addLight(light)
    }

    override fun tick() {
        this.prevPosX = this.x
        this.prevPosY = this.y
        this.prevPosZ = this.z

        light?.setPosition(this.x, this.y, this.z);

        this.updateAge()
        if (!this.dead) {
            this.velocityY -= gravityStrength.toDouble()
            this.move(this.velocityX, this.velocityY, this.velocityZ)
        } else {
            VeilRenderSystem.renderer().lightRenderer.removeLight(light)
        }
    }

    override fun getRotator(): Rotator = Rotator.ALL_AXIS

    fun updateAge() {
        age++
        if (age >= maxAge) {
            this.markDead()
        }

        this.setSpriteForAge(sprites)
    }

    override fun markDead() {
        super.markDead()
        VeilRenderSystem.renderer().lightRenderer.removeLight(light)
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    companion object {
        @Environment(EnvType.CLIENT)
        class Factory(private val sprites: SpriteProvider) : ParticleFactory<SimpleParticleType> {
            override fun createParticle(
                particleType: SimpleParticleType?, level: ClientWorld, x: Double, y: Double, z: Double,
                dx: Double, dy: Double, dz: Double,
            ): Particle {
                return WispParticle(level, x, y, z, this.sprites, dx, dy, dz)
            }
        }
    }

}