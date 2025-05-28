package com.filipegamer12br.rotp_wou.client.render;

import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CarProjectileModel extends EntityModel<CarProjectileEntity> {

    private final ModelRenderer RealCar;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;

    public CarProjectileModel() {
        texWidth = 256;
        texHeight = 256;

        RealCar = new ModelRenderer(this);
        RealCar.setPos(0.0F, 24.0F, 0.0F);
        RealCar.texOffs(98, 89).addBox(-32.0F, -16.0F, -15.0F, 14.0F, 11.0F, 31.0F, 0.0F, false);
        RealCar.texOffs(0, 48).addBox(-6.0F, -28.0F, -15.0F, 25.0F, 10.0F, 31.0F, 0.0F, false);
        RealCar.texOffs(88, 131).addBox(-35.0F, -8.0F, -17.0F, 5.0F, 4.0F, 35.0F, 0.0F, false);
        RealCar.texOffs(78, 170).addBox(-29.0F, -18.0F, -14.0F, 11.0F, 2.0F, 29.0F, 0.0F, false);
        RealCar.texOffs(188, 89).addBox(-29.0F, -10.0F, -18.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
        RealCar.texOffs(16, 215).addBox(17.0F, -11.0F, 17.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(150, 201).addBox(-32.0F, -13.0F, -17.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(30, 215).addBox(-32.0F, -13.0F, 16.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(200, 83).addBox(-30.0F, -13.0F, -17.0F, 12.0F, 2.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(0, 215).addBox(-18.0F, -13.0F, -18.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(38, 207).addBox(-30.0F, -13.0F, 16.0F, 12.0F, 2.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(88, 128).addBox(-18.0F, -5.0F, -18.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(158, 170).addBox(-20.0F, -13.0F, -18.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(204, 217).addBox(-20.0F, -13.0F, 18.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(210, 213).addBox(-18.0F, -13.0F, 17.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(184, 31).addBox(-16.0F, -7.0F, -18.0F, 33.0F, 3.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(184, 36).addBox(-16.0F, -7.0F, 17.0F, 33.0F, 3.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(188, 125).addBox(17.0F, -13.0F, -18.0F, 14.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(8, 215).addBox(17.0F, -11.0F, -18.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(38, 214).addBox(31.0F, -13.0F, -18.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(188, 128).addBox(17.0F, -13.0F, 18.0F, 14.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(46, 214).addBox(31.0F, -13.0F, 17.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        RealCar.texOffs(112, 48).addBox(33.0F, -8.0F, -18.0F, 7.0F, 4.0F, 37.0F, 0.0F, false);
        RealCar.texOffs(158, 173).addBox(-34.0F, -12.0F, -16.0F, 2.0F, 4.0F, 33.0F, 0.0F, false);
        RealCar.texOffs(78, 201).addBox(-34.0F, -15.0F, -7.0F, 2.0F, 3.0F, 15.0F, 0.0F, false);
        RealCar.texOffs(112, 209).addBox(-33.0F, -15.0F, 9.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
        RealCar.texOffs(132, 209).addBox(-33.0F, -15.0F, -16.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
        RealCar.texOffs(54, 214).addBox(8.0F, -28.0F, -16.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(152, 210).addBox(37.0F, -16.0F, -18.0F, 3.0F, 3.0F, 7.0F, 0.0F, false);
        RealCar.texOffs(172, 210).addBox(37.0F, -16.0F, 12.0F, 3.0F, 3.0F, 7.0F, 0.0F, false);
        RealCar.texOffs(66, 207).addBox(37.0F, -16.0F, -11.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        RealCar.texOffs(192, 217).addBox(37.0F, -16.0F, 9.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        RealCar.texOffs(24, 215).addBox(8.0F, -28.0F, 16.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(184, 41).addBox(-6.0F, -28.0F, -16.0F, 25.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(184, 44).addBox(-6.0F, -28.0F, 16.0F, 25.0F, 2.0F, 1.0F, 0.0F, false);
        RealCar.texOffs(218, 125).addBox(-14.0F, -19.0F, -18.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        RealCar.texOffs(0, 0).addBox(-18.0F, -18.0F, -17.0F, 57.0F, 13.0F, 35.0F, 0.0F, false);
        RealCar.texOffs(218, 213).addBox(-14.0F, -19.0F, 16.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        RealCar.texOffs(188, 107).addBox(20.0F, -10.0F, -18.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
        RealCar.texOffs(200, 47).addBox(-29.0F, -10.0F, 11.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);
        RealCar.texOffs(200, 65).addBox(20.0F, -10.0F, 11.0F, 10.0F, 10.0F, 8.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-12.5F, -18.0F, 20.0F);
        RealCar.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.4363F, 0.0F);
        cube_r1.texOffs(68, 218).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-13.0F, -18.0F, -18.0F);
        RealCar.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.4363F, 0.0F);
        cube_r2.texOffs(60, 218).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(22.0F, -23.0F, 4.0F);
        RealCar.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
        cube_r3.texOffs(0, 207).addBox(-1.0F, -1.2F, -20.0F, 18.0F, 7.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(112, 201).addBox(-1.0F, -1.2F, 12.0F, 18.0F, 7.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(0, 89).addBox(-1.0F, -1.2F, -19.0F, 18.0F, 8.0F, 31.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(18.2F, -26.0F, 0.0F);
        RealCar.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.829F);
        cube_r4.texOffs(192, 213).addBox(-0.93F, -1.95F, -16.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(60, 211).addBox(-0.93F, -1.95F, 16.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(0, 168).addBox(-0.93F, -1.95F, -15.0F, 8.0F, 8.0F, 31.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(-11.0F, -23.0F, 6.0F);
        RealCar.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.7854F);
        cube_r5.texOffs(38, 211).addBox(-8.75F, -1.55F, 10.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r5.texOffs(192, 210).addBox(-8.75F, -1.55F, -22.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r5.texOffs(0, 128).addBox(-11.75F, -1.55F, -21.0F, 13.0F, 9.0F, 31.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(-6.0F, -27.0F, -2.0F);
        RealCar.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, -0.5236F);
        cube_r6.texOffs(60, 215).addBox(-5.5F, -0.9F, 18.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r6.texOffs(214, 210).addBox(-5.5F, -0.9F, -14.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        cube_r6.texOffs(168, 131).addBox(-5.5F, -0.9F, -13.0F, 6.0F, 11.0F, 31.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(-29.0F, -15.0F, 13.0F);
        RealCar.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
        cube_r7.texOffs(184, 0).addBox(-0.9F, -2.1F, -27.0F, 3.0F, 2.0F, 29.0F, 0.0F, false); }

    @Override
    public void setupAnim(CarProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.RealCar.yRot = netHeadYaw;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        RealCar.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}