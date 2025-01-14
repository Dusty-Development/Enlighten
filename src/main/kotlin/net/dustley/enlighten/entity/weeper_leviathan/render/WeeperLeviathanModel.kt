package net.dustley.enlighten.entity.weeper_leviathan.render

import net.dustley.enlighten.entity.weeper_leviathan.WeeperLeviathanEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack

class WeeperLeviathanModel(model:ModelPart) : EntityModel<WeeperLeviathanEntity>() {

    // ModelParts
    val root: ModelPart = model.getChild("root")
    val upper_jaw: ModelPart = this.root.getChild("upper_jaw")
    val lower_jaw: ModelPart = this.root.getChild("lower_jaw")
    val tendrils: ModelPart = this.root.getChild("tendrils")
    val center: ModelPart = tendrils.getChild("center")
    val antenna_left: ModelPart = this.root.getChild("antenna_left")
    val middle: ModelPart = antenna_left.getChild("middle")
    val end: ModelPart = middle.getChild("end")
    val arm_left: ModelPart = this.root.getChild("arm_left")
    val forearm: ModelPart = arm_left.getChild("forearm")
    val claw: ModelPart = forearm.getChild("claw")

    // TextureData
    fun getTexturedModelData(): TexturedModelData {
        val modelData: ModelData = ModelData()
        val modelPartData: ModelPartData = modelData.root
        val root: ModelPartData = modelPartData.addChild(
            "root",
            ModelPartBuilder.create().uv(0, 35).cuboid(-12.0f, -12.0f, -16.0f, 24.0f, 24.0f, 16.0f, Dilation(0.0f)),
            ModelTransform.pivot(0.0f, 8.0f, 16.0f)
        )

        val upper_jaw: ModelPartData = root.addChild(
            "upper_jaw",
            ModelPartBuilder.create().uv(0, 75).cuboid(-11.0f, -12.5f, -18.0f, 22.0f, 13.0f, 18.0f, Dilation(0.0f))
                .uv(82, 57).cuboid(-9.0f, 0.5f, -16.0f, 18.0f, 3.0f, 16.0f, Dilation(0.0f)),
            ModelTransform.of(0.0f, 0.5f, -16.0f, -0.2618f, 0.0f, 0.0f)
        )

        val lower_jaw: ModelPartData = root.addChild(
            "lower_jaw",
            ModelPartBuilder.create().uv(0, 0).cuboid(-13.0f, 0.5f, -20.0f, 26.0f, 14.0f, 21.0f, Dilation(0.0f))
                .uv(80, 35).cuboid(-10.0f, -3.5f, -17.0f, 20.0f, 4.0f, 18.0f, Dilation(0.0f)),
            ModelTransform.of(0.0f, 0.5f, -16.0f, 0.1309f, 0.0f, 0.0f)
        )

        val tendrils: ModelPartData =
            root.addChild("tendrils", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 0.0f, 0.0f))

        val tendril_left_r1: ModelPartData = tendrils.addChild(
            "tendril_left_r1",
            ModelPartBuilder.create().uv(94, 100).cuboid(0.0f, -1.0f, -3.5f, 0.0f, 16.0f, 7.0f, Dilation(0.0f)),
            ModelTransform.of(-7.0f, 11.0f, -7.0f, 0.3927f, 0.0f, 0.0f)
        )

        val center: ModelPartData =
            tendrils.addChild("center", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 19.2388f, -7.1732f))

        val tendril_center_r1: ModelPartData = center.addChild(
            "tendril_center_r1",
            ModelPartBuilder.create().uv(80, 100).cuboid(0.0f, -1.0f, -3.5f, 0.0f, 22.0f, 7.0f, Dilation(0.0f)),
            ModelTransform.of(0.0f, -9.2388f, -3.8268f, 0.829f, 0.0f, 0.0f)
        )

        val antenna_left: ModelPartData = root.addChild(
            "antenna_left",
            ModelPartBuilder.create().uv(0, 106).cuboid(-1.0f, -13.0f, -1.0f, 2.0f, 13.0f, 2.0f, Dilation(0.0f)),
            ModelTransform.of(-6.5f, -11.0f, -7.0f, -0.5672f, 0.0f, 0.0f)
        )

        val middle: ModelPartData = antenna_left.addChild(
            "middle",
            ModelPartBuilder.create().uv(8, 106).cuboid(-0.5f, -13.0f, -0.5f, 1.0f, 13.0f, 1.0f, Dilation(0.0f)),
            ModelTransform.of(0.0f, -13.0f, 0.0f, -0.5236f, 0.0f, 0.0f)
        )

        val end: ModelPartData = middle.addChild(
            "end",
            ModelPartBuilder.create().uv(94, 12).cuboid(0.0f, -13.0f, -3.5f, 0.0f, 16.0f, 7.0f, Dilation(0.0f)),
            ModelTransform.of(0.0f, -13.0f, 0.0f, -0.6109f, 0.0f, 0.0f)
        )

        val arm_left: ModelPartData = root.addChild(
            "arm_left",
            ModelPartBuilder.create().uv(80, 76).cuboid(0.0f, -3.5f, -3.5f, 24.0f, 7.0f, 7.0f, Dilation(0.0f)),
            ModelTransform.of(-12.0f, 6.0f, -10.0f, 0.0f, 3.1416f, 0.0f)
        )

        val forearm: ModelPartData = arm_left.addChild(
            "forearm",
            ModelPartBuilder.create().uv(80, 90).cuboid(0.0f, -2.5f, -2.5f, 24.0f, 5.0f, 5.0f, Dilation(0.0f)),
            ModelTransform.pivot(24.0f, 0.0f, 0.0f)
        )

        val claw: ModelPartData =
            forearm.addChild("claw", ModelPartBuilder.create(), ModelTransform.pivot(21.5f, 0.0f, 0.0f))

        val cube_r1: ModelPartData = claw.addChild(
            "cube_r1",
            ModelPartBuilder.create().uv(94, 0).cuboid(-11.0f, -6.0f, 0.0f, 22.0f, 12.0f, 0.0f, Dilation(0.0f)),
            ModelTransform.of(9.5f, 0.0f, 0.0f, -1.5708f, 0.0f, 0.0f)
        )
        return TexturedModelData.of(modelData, 256, 256)
    }

    override fun render(matrices: MatrixStack, vertices: VertexConsumer, light: Int, overlay: Int, color: Int) = root.render(matrices, vertices, light, overlay, color)
    override fun setAngles(entity: WeeperLeviathanEntity, limbAngle: Float, limbDistance: Float, animationProgress: Float, headYaw: Float, headPitch: Float) { }

}