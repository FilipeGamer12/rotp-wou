package com.filipegamer12br.rotp_wou.util;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.filipegamer12br.rotp_wou.init.InitStands;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID)
public class GameplayEventHandler {
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity != null && livingEntity.isAlive() && !livingEntity.level.isClientSide()) {
            IStandPower.getStandPowerOptional(livingEntity).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                        if (wonderOfYouEntity.isCalamityEnabled()) {
                            float damageToOurWOU = event.getAmount();
                            if (event.getSource().getEntity() instanceof LivingEntity) {
                                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
                                attacker.hurt(event.getSource(), damageToOurWOU); // 100: duration (in ticks) | 1: amplifier (levels, from 0 to 255) | idk | idk | showIcon
                                attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true));
                            }
                        }
                    }
                }
            });
        }
    }
}
