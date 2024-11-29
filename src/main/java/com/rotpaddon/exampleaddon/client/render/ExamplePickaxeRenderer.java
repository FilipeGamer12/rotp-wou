package com.rotpaddon.exampleaddon.client.render;

import com.github.standobyte.jojo.client.render.entity.renderer.SimpleEntityRenderer;
import com.rotpaddon.exampleaddon.AddonMain;
import com.rotpaddon.exampleaddon.entity.ExamplePickaxeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class ExamplePickaxeRenderer extends SimpleEntityRenderer<ExamplePickaxeEntity, ExamplePickaxeModel> {

    public ExamplePickaxeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ExamplePickaxeModel(), 
                new ResourceLocation(AddonMain.MOD_ID, "textures/entity/example_pickaxe.png"));
    }
}
