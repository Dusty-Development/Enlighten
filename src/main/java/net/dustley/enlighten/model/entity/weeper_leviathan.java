//package net.dustley.enlighten.model.entity;
//
//import net.minecraft.client.model.ModelPart;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.entity.model.EntityModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.entity.Entity;
//
//// Made with Blockbench 4.12.2
//// Exported for Minecraft version 1.17+ for Yarn
//// Paste this class into your mod and generate all required imports
//public class weeper_leviathan extends EntityModel<Entity> {
//	private final ModelPart root;
//	private final ModelPart upper_jaw;
//	private final ModelPart lower_jaw;
//	private final ModelPart tendrils;
//	private final ModelPart center;
//	private final ModelPart antenna_left;
//	private final ModelPart middle;
//	private final ModelPart end;
//	private final ModelPart arm_left;
//	private final ModelPart forearm;
//	private final ModelPart claw;
//	public weeper_leviathan(ModelPart root) {
//		this.root = root.getChild("root");
//		this.upper_jaw = this.root.getChild("upper_jaw");
//		this.lower_jaw = this.root.getChild("lower_jaw");
//		this.tendrils = this.root.getChild("tendrils");
//		this.center = this.tendrils.getChild("center");
//		this.antenna_left = this.root.getChild("antenna_left");
//		this.middle = this.antenna_left.getChild("middle");
//		this.end = this.middle.getChild("end");
//		this.arm_left = this.root.getChild("arm_left");
//		this.forearm = this.arm_left.getChild("forearm");
//		this.claw = this.forearm.getChild("claw");
//	}
//	public static TexturedModelData getTexturedModelData() {
//		ModelData modelData = new ModelData();
//		ModelPartData modelPartData = modelData.getRoot();
//		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 35).cuboid(-12.0F, -8.0F, -16.0F, 24.0F, 24.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, 16.0F));
//
//		ModelPartData upper_jaw = root.addChild("upper_jaw", ModelPartBuilder.create().uv(0, 75).cuboid(-11.0F, -8.5F, -18.0F, 22.0F, 13.0F, 18.0F, new Dilation(0.0F))
//		.uv(82, 57).cuboid(-9.0F, 0.5F, -16.0F, 18.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, -16.0F));
//
//		ModelPartData lower_jaw = root.addChild("lower_jaw", ModelPartBuilder.create().uv(0, 0).cuboid(-13.0F, 4.5F, -20.0F, 26.0F, 14.0F, 21.0F, new Dilation(0.0F))
//		.uv(80, 35).cuboid(-10.0F, -3.5F, -17.0F, 20.0F, 4.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, -16.0F));
//
//		ModelPartData tendrils = root.addChild("tendrils", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		ModelPartData tendril_left_r1 = tendrils.addChild("tendril_left_r1", ModelPartBuilder.create().uv(94, 100).cuboid(0.0F, -1.0F, -3.5F, 0.0F, 16.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 15.0F, -7.0F, 0.3927F, 0.0F, 0.0F));
//
//		ModelPartData center = tendrils.addChild("center", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, -9.0F));
//
//		ModelPartData tendril_center_r1 = center.addChild("tendril_center_r1", ModelPartBuilder.create().uv(80, 100).cuboid(0.0F, -11.0F, -3.5F, 0.0F, 22.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.7559F, 7.3728F, 0.829F, 0.0F, 0.0F));
//
//		ModelPartData antenna_left = root.addChild("antenna_left", ModelPartBuilder.create().uv(0, 106).cuboid(-1.0F, -9.6264F, 1.1492F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -11.0F, -7.0F, -0.5672F, 0.0F, 0.0F));
//
//		ModelPartData middle = antenna_left.addChild("middle", ModelPartBuilder.create().uv(8, 106).cuboid(-0.5F, -11.153F, 3.048F, 1.0F, 13.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -0.5236F, 0.0F, 0.0F));
//
//		ModelPartData end = middle.addChild("end", ModelPartBuilder.create().uv(94, 12).cuboid(0.0F, -13.5221F, 0.4658F, 0.0F, 16.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
//
//		ModelPartData arm_left = root.addChild("arm_left", ModelPartBuilder.create().uv(80, 76).cuboid(0.0F, 0.5F, -3.5F, 24.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, 6.0F, -10.0F, 0.0F, 3.1416F, 0.0F));
//
//		ModelPartData forearm = arm_left.addChild("forearm", ModelPartBuilder.create().uv(80, 90).cuboid(0.0F, 1.5F, -2.5F, 24.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(24.0F, 0.0F, 0.0F));
//
//		ModelPartData claw = forearm.addChild("claw", ModelPartBuilder.create(), ModelTransform.pivot(21.5F, 0.0F, 0.0F));
//
//		ModelPartData cube_r1 = claw.addChild("cube_r1", ModelPartBuilder.create().uv(94, 0).cuboid(-11.0F, -6.0F, 4.0F, 22.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(9.5F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
//		return TexturedModelData.of(modelData, 256, 256);
//	}
//	@Override
//	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//	}
//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
//		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
//	}
//
//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
//
//	}
//}