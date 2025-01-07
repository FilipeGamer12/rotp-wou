package com.filipegamer12br.rotp_wou.event;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import com.filipegamer12br.rotp_wou.init.InitEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttribues(EntityAttributeCreationEvent event){
        event.put(InitEntities.CAR_PROJECTILE.get(), CarProjectileEntity.createAttributes().build());
    }

}
