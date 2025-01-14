package net.dustley.enlighten.entity.ebonized_wish

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.sound.MovingSoundInstance
import net.minecraft.client.sound.SoundInstance
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.MathHelper

class EbonizedWishEntitySoundInstance(
    private var entity: EbonizedWishEntity,
    private var player: ClientPlayerEntity
) : MovingSoundInstance(SoundEvents.BLOCK_BEACON_AMBIENT, SoundCategory.AMBIENT, SoundInstance.createRandom()) {
    private var tickCount = 0

    init{
        this.repeat = true
        this.repeatDelay = 0
        this.volume = 1.0f
    }

    override fun tick() {

        ++this.tickCount
        if (!player.isRemoved && !entity.isRemoved ) {
            this.x = (entity.x.toFloat()).toDouble()
            this.y = (entity.y.toFloat()).toDouble()
            this.z = (entity.z.toFloat()).toDouble()

            val speed = entity.velocity.lengthSquared().toFloat()

            if (speed.toDouble() >= 1.0E-7) this.volume = MathHelper.clamp(speed / 4.0f, 0.0f, 1.0f) else this.volume = 0.0f

            val distance = entity.distanceTo(player)
            this.volume = 1.0f / (distance * 0.5f)

//            if(wish_contract.velocity.length() < 0.001) volume = 0.0f

            if (this.volume > 0.8f) this.pitch = 1.0f + (this.volume - 0.1f) else this.pitch = 1.0f

        } else {
            this.setDone()
        }
    }

}