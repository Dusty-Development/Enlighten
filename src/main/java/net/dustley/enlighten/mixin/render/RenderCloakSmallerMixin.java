package net.dustley.enlighten.mixin.render;

import net.dustley.enlighten.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class RenderCloakSmallerMixin {

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void hideArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci) {
        if(entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INSTANCE.getMIRE_CLOAK()) {
            if (armorSlot != EquipmentSlot.CHEST) ci.cancel();
        }
    }
}
