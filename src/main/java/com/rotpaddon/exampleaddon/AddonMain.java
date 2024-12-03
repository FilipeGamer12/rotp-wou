package com.rotpaddon.exampleaddon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rotpaddon.exampleaddon.init.InitEntities;
import com.rotpaddon.exampleaddon.init.InitSounds;
import com.rotpaddon.exampleaddon.init.InitStands;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(AddonMain.MOD_ID)
public class AddonMain {
    public static final String MOD_ID = "rotp-wou";
    public static final Logger LOGGER = LogManager.getLogger();

    public AddonMain() {
        // Registra os eventos no Forge EventBus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registra as ações e o Stand no Forge EventBus (movido para InitStands)
        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
    }
}