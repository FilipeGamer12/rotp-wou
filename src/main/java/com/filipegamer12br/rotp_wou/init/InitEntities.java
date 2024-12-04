package com.filipegamer12br.rotp_wou.init;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;

import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, WonderOfYouAddon.MOD_ID);
};
