package net.dustley.enlighten.mixin.wish_contract;


import net.dustley.enlighten.EnlightenCardinalComponents;
import net.dustley.enlighten.entity.ModEntities;
import net.dustley.enlighten.entity.ebonized_wish.EbonizedWishEntity;
import net.dustley.enlighten.item.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class EbonizedWishAntiPickupMixin {

    @Shadow public abstract ItemStack getStack();

    @Shadow public abstract void setNeverDespawn();

    @Shadow private int itemAge;

    @Shadow public abstract void setOwner(@Nullable UUID owner);

    @Shadow public abstract void setCovetedItem();

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void preTick(CallbackInfo ci) {
        final ItemEntity self = ItemEntity.class.cast(this);

        if(getStack().isOf(ModItems.INSTANCE.getEBONIZED_WISH())) {
            setNeverDespawn();
            setCovetedItem();

            var contractedPlayerComponentOptional = EnlightenCardinalComponents.INSTANCE.getCONTRACTED_PLAYER().maybeGet(self.getWorld().getScoreboard());
            if(contractedPlayerComponentOptional.isPresent() && contractedPlayerComponentOptional.get().getIsAssigned()) {
                UUID playerUUID = contractedPlayerComponentOptional.get().getValue();
                setOwner(playerUUID);

                if(self.getWorld().getPlayerByUuid(playerUUID) != null) {
                    if(self.getWorld().getPlayerByUuid(playerUUID).isSneaking()) {
                        EbonizedWishEntity entity = new EbonizedWishEntity(self.getWorld(), self.getPos().add(0.0,1.0,0.0), self.getStack(), ModEntities.INSTANCE.getEBONIZED_WISH());
                        self.getWorld().spawnEntity(entity);
                        self.discard();
                    }
                }

            }
        }
    }

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    private void playerCollision(PlayerEntity player, CallbackInfo ci) {
        final ItemEntity self = ItemEntity.class.cast(this);

        var contractedPlayerComponentOptional = EnlightenCardinalComponents.INSTANCE.getCONTRACTED_PLAYER().maybeGet(self.getWorld().getScoreboard());
        if(contractedPlayerComponentOptional.isPresent()) {
            UUID playerUUID = contractedPlayerComponentOptional.get().getValue();

            if(getStack().isOf(ModItems.INSTANCE.getEBONIZED_WISH()) && player.getUuid() != playerUUID) {
                ci.cancel();
            }
        }
    }

}
