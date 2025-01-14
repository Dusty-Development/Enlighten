package net.dustley.enlighten.entity.ebonized_wish

import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import org.joml.Quaternionf

class EbonizedWishEntityRenderer(ctx: EntityRendererFactory.Context) : FlyingItemEntityRenderer<EbonizedWishEntity>(ctx) {


    private var itemRenderer: ItemRenderer? = null

    init { this.itemRenderer = ctx.itemRenderer }

    override fun render(
        entityIn: EbonizedWishEntity, entityYaw: Float, partialTicks: Float, matrixStackIn: MatrixStack,
        bufferIn: VertexConsumerProvider?, packedLightIn: Int,
    ) {
        matrixStackIn.push()

        matrixStackIn.translate(0.0,1.0,0.0)

        val rotation = Quaternionf()
        rotation.rotateZ(Math.toRadians(90.0).toFloat())
        rotation.rotateX(Math.toRadians(-(entityIn.owner?.yaw?.toDouble()?: 0.0)).toFloat())
        matrixStackIn.multiply(rotation)

        itemRenderer?.renderItem(
            entityIn.stack,
            ModelTransformationMode.HEAD,
            packedLightIn,
            OverlayTexture.DEFAULT_UV,
            matrixStackIn,
            bufferIn,
            entityIn.world,
            entityIn.id
        )

        matrixStackIn.pop()
    }


}