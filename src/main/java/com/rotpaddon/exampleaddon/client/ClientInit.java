package com.rotpaddon.exampleaddon.client;

import com.rotpaddon.exampleaddon.AddonMain;
import com.rotpaddon.exampleaddon.client.render.ExamplePickaxeRenderer;
import com.rotpaddon.exampleaddon.client.render.ExampleStandRenderer;
import com.rotpaddon.exampleaddon.init.InitEntities;
import com.rotpaddon.exampleaddon.init.InitStands;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = AddonMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.STAND_EXAMPLE_STAND.getEntityType(), ExampleStandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(
                InitEntities.EXAMPLE_PICKAXE.get(), manager -> new ExamplePickaxeRenderer(manager));
    }
}
