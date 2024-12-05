package com.filipegamer12br.rotp_wou.util;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitStands;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID)
public class GameplayEventHandler {
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity != null && livingEntity.isAlive() && !livingEntity.level.isClientSide()) {
            IStandPower.getStandPowerOptional(livingEntity).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                        if (wonderOfYouEntity.isCalamityEnabled()) {
                            // Dano negado em 85% (recebe apenas 15% do dano)
                            float damageToOurWOU = event.getAmount();
                            event.setAmount(damageToOurWOU * 0.15F); // 15% do dano original

                            if (event.getSource().getEntity() instanceof LivingEntity) {
                                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
                                // Reflete o dano de volta ao atacante
                                attacker.hurt(event.getSource(), damageToOurWOU); // Reflete o dano original
                                // Aplica efeitos negativos ao atacante
                                attacker.addEffect(new EffectInstance(Effects.POISON, 100, 2, false, false, true)); // Veneno II.
                                attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II.
                                attacker.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, false, false, true)); // Cegueira.
                            }
                        }
                    }
                }
            });
        }
    }
}
