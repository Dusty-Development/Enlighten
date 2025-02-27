package net.dustley.enlighten.item.trinket.mire_monocle

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.client.TrinketRenderer
import net.dustley.enlighten.item.trinket.mire_flower_clip.MireFlowerClipRenderer.Companion.renderPart
import net.dustley.enlighten.model.item.MireMonocleModel
import net.dustley.enlighten.model.item.MireRoseModel
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

class MireMonocleRenderer() : TrinketRenderer {

    override fun render(
        stack: ItemStack,
        slotReference: SlotReference,
        contextModel: EntityModel<out LivingEntity>,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        entity: LivingEntity,
        limbAngle: Float,
        limbDistance: Float,
        tickDelta: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float,
    ) {
        TrinketRenderer.translateToFace(matrices, contextModel as PlayerEntityModel<AbstractClientPlayerEntity>, entity as AbstractClientPlayerEntity, headYaw, headPitch)

        matrices.translate(0f,-1.35f,0.3f)
        renderPart(matrices, vertexConsumers, light)
    }

    companion object {
        fun renderPart(matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
            val consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(MireMonocleModel.getTexture()))
            matrices.scale(1.1f,1.1f,1.1f)
            MireMonocleModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV)
        }
    }
}