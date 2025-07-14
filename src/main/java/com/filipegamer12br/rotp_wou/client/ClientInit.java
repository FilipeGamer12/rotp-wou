package com.filipegamer12br.rotp_wou.client;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.client.render.WonderOfYouRenderer;
import com.filipegamer12br.rotp_wou.client.render.CarProjectileRenderer; // Import do renderizador do carro
import com.filipegamer12br.rotp_wou.init.InitStands;
import com.filipegamer12br.rotp_wou.init.InitEntities; // Import do registro da entidade carro
import com.github.standobyte.jojo.client.ui.standstats.StandStatsRenderer;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        // Registro do renderizador para WonderOfYou
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.WONDER_OF_YOU.getEntityType(), WonderOfYouRenderer::new);

        // Registro do renderizador para CarProjectile
        RenderingRegistry.registerEntityRenderingHandler(
                InitEntities.CAR_PROJECTILE.get(), CarProjectileRenderer::new);
    }
}
