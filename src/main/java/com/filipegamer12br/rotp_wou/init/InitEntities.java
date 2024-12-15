package com.filipegamer12br.rotp_wou.init;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, WonderOfYouAddon.MOD_ID);

    public static final RegistryObject<EntityType<CarProjectileEntity>> CAR_PROJECTILE = ENTITIES.register("car_projectile",
            () -> EntityType.Builder.<CarProjectileEntity>of(CarProjectileEntity::new, EntityClassification.MISC)
                    .sized(1.0F, 1.0F) // Define o tamanho da hitbox (largura x altura)
                    .setTrackingRange(64) // Distância em blocos para sincronização com o cliente
                    .setUpdateInterval(20) // Intervalo em ticks para atualização
                    .setShouldReceiveVelocityUpdates(true) // Define se atualiza movimento no cliente
                    .build(WonderOfYouAddon.MOD_ID + ":car_projectile"));
    }