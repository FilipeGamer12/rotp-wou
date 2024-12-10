package com.filipegamer12br.rotp_wou.util;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitStands;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID)
public class GameplayEventHandler {

    // Calamity Passive - Reflete dano apenas quando o Stand for atacado
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity != null && livingEntity.isAlive() && !livingEntity.level.isClientSide()) {
            IStandPower.getStandPowerOptional(livingEntity).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                        if (wonderOfYouEntity.isCalamityPassiveEnabled()) {
                            // Calamity Passive - Reflete dano apenas quando o Stand for atacado
                            float damageToOurWOU = event.getAmount();
                            event.setAmount(damageToOurWOU * 0.15F); // 15% do dano original (negado 85%)

                            if (event.getSource().getEntity() instanceof LivingEntity) {
                                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
                                // Reflete o dano de volta ao atacante
                                attacker.hurt(event.getSource(), damageToOurWOU); // Reflete o dano original
                                // Aplica efeitos negativos ao atacante
                                attacker.addEffect(new EffectInstance(Effects.POISON, 100, 2, false, false, true)); // Veneno II.
                                attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II.
                                attacker.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira.
                            }
                        }
                    }
                }
            });
        }
    }

    // Calamity Active - Aplica efeitos em área ao redor do portador e 50% de negação de dano
    @SubscribeEvent
    public static void onSetAttackTarget(LivingSetAttackTargetEvent event) {
        LivingEntity target = event.getTarget();
        if (target != null && target.isAlive() && !target.level.isClientSide()) {
            IStandPower.getStandPowerOptional(target).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();

                        // Calamity Active - Aplica efeitos ao redor do usuário do Stand
                        if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                            // Aplica os efeitos de lentidão e cegueira a entidades dentro de um raio de 6 blocos
                            for (Entity nearbyEntity : target.level.getEntities(target, target.getBoundingBox().inflate(6.0D))) {
                                if (nearbyEntity instanceof LivingEntity && nearbyEntity != target) {
                                    LivingEntity livingEntity = (LivingEntity) nearbyEntity;
                                    livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 3, false, false, true)); // Lentidão III
                                    livingEntity.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, false, false, true)); // Cegueira
                                }
                            }

                            // Refletir dano ao atacante
                            if (event.getEntityLiving() instanceof LivingEntity) {
                                LivingEntity attacker = (LivingEntity) event.getEntityLiving();

                                DamageSource damageSource;
                                if (attacker instanceof PlayerEntity) {
                                    damageSource = DamageSource.playerAttack((PlayerEntity) attacker);
                                } else {
                                    damageSource = DamageSource.mobAttack(attacker);
                                }

                                // Aplica o dano fixo de 2.0F ao atacante
                                attacker.hurt(damageSource, 2.0F);

                                // Aplica efeitos negativos ao atacante
                                attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I
                                attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                attacker.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity != null && !entity.level.isClientSide()) {
            IStandPower.getStandPowerOptional(entity).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();

                        // Verifica se a habilidade CalamityActive está ativa
                        if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                            // Reduz o dano recebido pelo usuário para 15% do valor original
                            float originalDamage = event.getAmount();
                            event.setAmount(originalDamage * 0.15F); // 15% do dano original
                        }
                    }
                }
            });
        }
    }
}