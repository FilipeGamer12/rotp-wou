package com.rotpaddon.exampleaddon.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.rotpaddon.exampleaddon.entity.ExamplePickaxeEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ExamplePickaxeModel extends EntityModel<ExamplePickaxeEntity> {
	private final ModelRenderer pickaxe;

	public ExamplePickaxeModel() {
		texWidth = 32;
		texHeight = 32;

        pickaxe = new ModelRenderer(this);
        pickaxe.setPos(0.0F, -8.0F, 0.0F);
        pickaxe.texOffs(10, 10).addBox(-0.5F, 5.5F, 4.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        pickaxe.texOffs(20, 17).addBox(-0.5F, 4.5F, 3.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(8, 19).addBox(-0.5F, 3.5F, 2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(0, 19).addBox(-0.5F, 2.5F, 1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(18, 11).addBox(-0.5F, 1.5F, 0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(18, 7).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(18, 0).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(0, 14).addBox(-0.5F, -1.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(10, 10).addBox(-0.5F, -2.5F, -6.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        pickaxe.texOffs(0, 0).addBox(-0.5F, -1.5F, -6.5F, 1.0F, 4.0F, 3.0F, 0.0F, false);
        pickaxe.texOffs(5, 0).addBox(-0.5F, 2.5F, -5.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        pickaxe.texOffs(14, 17).addBox(-0.5F, -3.5F, -5.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        pickaxe.texOffs(0, 10).addBox(-0.5F, -4.5F, -5.5F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        pickaxe.texOffs(0, 0).addBox(-0.5F, -5.5F, -5.5F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        pickaxe.texOffs(11, 0).addBox(-0.5F, -6.5F, -2.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ExamplePickaxeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	    pickaxe.yRot = netHeadYaw * ((float)Math.PI / 180F);
	    pickaxe.xRot = ageInTicks * 2F;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		pickaxe.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}