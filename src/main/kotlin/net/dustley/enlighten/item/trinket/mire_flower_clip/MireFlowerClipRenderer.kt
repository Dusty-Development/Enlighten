package net.dustley.enlighten.item.trinket.mire_flower_clip

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.client.TrinketRenderer
import net.dustley.enlighten.item.ModItems
import net.dustley.enlighten.model.item.MireRoseModel
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

class MireFlowerClipRenderer(val type: String) : TrinketRenderer {

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
        renderPart(matrices, vertexConsumers, light, type)
    }

    companion object {
        fun renderPart(matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int, type: String) {
            val consumer = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(MireRoseModel.getTexture(type)))
            matrices.scale(1.1f,1.1f,1.1f)
            MireRoseModel.render(matrices, consumer, light, 15)
        }
    }
}