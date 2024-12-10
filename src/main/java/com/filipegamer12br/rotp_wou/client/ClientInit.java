package com.filipegamer12br.rotp_wou.client;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.client.render.WonderOfYouRenderer;
import com.filipegamer12br.rotp_wou.init.InitStands;
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
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.WONDER_OF_YOU.getEntityType(), WonderOfYouRenderer::new);
        StandStatsRenderer.overrideCosmeticStats(
                InitStands.WONDER_OF_YOU.getStandType().getRegistryName(),
                new StandStatsRenderer.ICosmeticStandStats() {
                    @Override
                    public double statConvertedValue(StandStatsRenderer.StandStat stat, IStandPower standData, StandStats stats, float statLeveling) {
                        return 0;
                    }
                });
    }
}
