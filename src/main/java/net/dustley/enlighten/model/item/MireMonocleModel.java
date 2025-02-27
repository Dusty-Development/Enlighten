package net.dustley.enlighten.model.item;

import net.dustley.enlighten.Enlighten;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MireMonocleModel {

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main",
				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.1F))
						.uv(0, 16)
						.cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.35F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	public static void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay) {
		int color = Integer.parseInt("#FFFFFF".substring(1), 16); // Convert hex to int
		getTexturedModelData().createModel().render(matrices, vertexConsumer, light, overlay, color);
	}

	public static Identifier getTexture() {
        return Enlighten.INSTANCE.identifier("textures/item/mire/mire_monocle.png");
    }
}