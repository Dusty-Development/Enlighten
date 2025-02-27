package net.dustley.enlighten.mixin.render;

import dev.emi.trinkets.api.TrinketsApi;
import net.dustley.enlighten.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class HideArmorMixin {

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void hideArmorStart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci) {
        var client = MinecraftClient.getInstance();

        var canRender = true;

        if(client.player != null) {
            var trinketProviderClient = TrinketsApi.getTrinketComponent(client.player);
            if(trinketProviderClient.isPresent()) {
                var trinket = trinketProviderClient.get();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_MONOCLE())) canRender = false;
            }
        }

        if(canRender) {
            var trinketProvider = TrinketsApi.getTrinketComponent(entity);
            if(trinketProvider.isPresent() && entity instanceof PlayerEntity) {
                var trinket = trinketProvider.get();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_ROSE_CLIP())) ci.cancel();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_DANDELION_CLIP())) ci.cancel();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_PEONY_CLIP())) ci.cancel();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_LILY_CLIP())) ci.cancel();
                if(trinket.isEquipped(ModItems.INSTANCE.getMIRE_WISP_CLIP())) ci.cancel();
            }
        }

        if(entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INSTANCE.getMIRE_CLOAK()) {
            if (armorSlot != EquipmentSlot.CHEST) ci.cancel();
        }
    }

}
