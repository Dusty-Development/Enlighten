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
public class ScaleShownArmorMixin {

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void hideArmorStart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci) {
        if(entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INSTANCE.getMIRE_CLOAK() || entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.INSTANCE.getMIRE_ROSE_CLIP()) {
            matrices.push();
            matrices.scale(0.9f, 0.9f, 0.9f);
        }
    }

    @Inject(method = "renderArmor", at = @At("TAIL"), cancellable = true)
    private void hideArmorEnd(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci) {
        if(entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INSTANCE.getMIRE_CLOAK() || entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.INSTANCE.getMIRE_ROSE_CLIP()) {
            matrices.pop();
        }
    }

}
