package com.filipegamer12br.rotp_wou.client.render;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CarProjectileRenderer extends EntityRenderer<CarProjectileEntity> {

    // Caminho para a textura da entidade
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(WonderOfYouAddon.MOD_ID, "textures/entity/car_projectile.png");

    public CarProjectileRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(CarProjectileEntity entity) {
        return TEXTURE;
    }
}