package net.dustley.enlighten.entity.weeper_leviathan.render

import net.dustley.enlighten.Enlighten
import net.dustley.enlighten.entity.weeper_leviathan.WeeperLeviathanEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.util.Identifier
import org.joml.Quaternionf

class WeeperLeviathanEntityRenderer(ctx: EntityRendererFactory.Context, model: WeeperLeviathanModel) : MobEntityRenderer<WeeperLeviathanEntity, WeeperLeviathanModel>(ctx, model, 1f) {

    override fun render(
        entity: WeeperLeviathanEntity, yaw: Float, tickDelta:Float, matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider?, light: Int,
    ) {
        matrices.push()

        val scale = 1.5f
        matrices.scale(scale,scale,scale)
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.pop()
    }

    override fun getTexture(entity: WeeperLeviathanEntity?): Identifier = Enlighten.identifier("textures/entity/weeper_leviathan/weeper_leviathan.png")


}