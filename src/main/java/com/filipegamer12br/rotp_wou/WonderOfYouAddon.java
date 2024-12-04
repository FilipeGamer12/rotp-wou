package com.filipegamer12br.rotp_wou;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.filipegamer12br.rotp_wou.init.InitEntities;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.filipegamer12br.rotp_wou.init.InitStands;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WonderOfYouAddon.MOD_ID)
public class WonderOfYouAddon {
    public static final String MOD_ID = "rotp_wou";
    public static final Logger LOGGER = LogManager.getLogger();

    public WonderOfYouAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
    }
}