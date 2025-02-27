package net.dustley.enlighten.mixin.parry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public class PlayerParryProjectileMixin {

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void attack(Entity target, CallbackInfo ci) {
        final PlayerEntity self = PlayerEntity.class.cast(this);
        if(target instanceof ProjectileEntity) {
            Vec3d pos = self.getEyePos().add(self.getRotationVector().normalize().multiply(0.25));
            target.setPosition(pos);
            self.getWorld().addParticle(ParticleTypes.FLASH, pos.getX(), pos.getY(), pos.getZ(), self.getVelocity().x, self.getVelocity().y, self.getVelocity().z);
            self.playSound(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), 1F, 1.2F);

            Vec3d vel = self.getRotationVector().normalize().multiply(Math.max(1.5, target.getVelocity().length())).multiply(2.0);
            target.setVelocity(vel);

            ((ProjectileEntity) target).setOwner(self);
            ci.cancel();
        }
    }

}
