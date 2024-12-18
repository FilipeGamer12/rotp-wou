package com.filipegamer12br.rotp_wou.client.render;

import com.github.standobyte.jojo.client.render.entity.model.stand.StandEntityModel;
import com.github.standobyte.jojo.client.render.entity.model.stand.StandModelRegistry;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;
import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WonderOfYouRenderer extends StandEntityRenderer<WonderOfYouEntity, StandEntityModel<WonderOfYouEntity>> {
    
    public WonderOfYouRenderer(EntityRendererManager renderManager) {
        super(renderManager, 
                StandModelRegistry.registerModel(new ResourceLocation(WonderOfYouAddon.MOD_ID, "wonder_of_you"), WonderOfYouModel::new),
                new ResourceLocation(WonderOfYouAddon.MOD_ID, "textures/entity/stand/wonder_of_you.png"), 0);
    }
}