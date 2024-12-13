package com.filipegamer12br.rotp_wou.util;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.TickEvent;
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
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.filipegamer12br.rotp_wou.action.CalamityActive.drainStamina;

@Mod.EventBusSubscriber(modid = WonderOfYouAddon.MOD_ID)
public class GameplayEventHandler {

    // Calamity Passive
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();

        // Certificar-se de que a entidade é válida e estamos no lado do servidor
        if (livingEntity != null && livingEntity.isAlive() && !livingEntity.level.isClientSide()) {
            IStandPower.getStandPowerOptional(livingEntity).ifPresent(power -> {
                // Verificar se o Stand do Wonder Of U está ativo e passivo habilitado
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                        if (wonderOfYouEntity.isCalamityPassiveEnabled()) {
                            float damageToOurWOU = event.getAmount();
                            // Reduzir o dano recebido
                            event.setAmount(damageToOurWOU * 0.15F);

                            // Verificar se o atacante é uma entidade viva
                            if (event.getSource().getEntity() instanceof LivingEntity) {
                                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();

                                // Se o atacante for um Stand, então precisamos pegar o usuário do Stand
                                if (attacker instanceof StandEntity) {
                                    StandEntity standAttacker = (StandEntity) attacker;

                                    // Acessando o usuário do Stand atacante
                                    LivingEntity standUser = standAttacker.getUser();  // Método para obter o usuário do Stand

                                    // Verifique se o usuário do Stand é válido
                                    if (standUser != null) {
                                        // Aplicar as penalidades ao usuário do Stand adversário
                                        standUser.hurt(event.getSource(), damageToOurWOU); // Dano ao usuário do Stand
                                        standUser.addEffect(new EffectInstance(Effects.POISON, 100, 2, false, false, true)); // Veneno II
                                        standUser.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                        standUser.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                                    }
                                }
                                // Se o atacante for um PlayerEntity ou MobEntity (não Stand)
                                else if (attacker instanceof PlayerEntity || attacker instanceof MobEntity) {
                                    // Aplicar os efeitos ao atacante diretamente
                                    attacker.hurt(event.getSource(), damageToOurWOU); // Dano ao atacante
                                    attacker.addEffect(new EffectInstance(Effects.POISON, 100, 2, false, false, true)); // Veneno II
                                    attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                    attacker.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    // Calamity Active
    @SubscribeEvent
    public static void onSetAttackTarget(LivingSetAttackTargetEvent event) {
        LivingEntity target = event.getTarget();
        if (target != null && target.isAlive() && !target.level.isClientSide()) {
            IStandPower.getStandPowerOptional(target).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                        if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                            // Aplicar efeitos a entidades próximas
                            for (Entity nearbyEntity : target.level.getEntities(target, target.getBoundingBox().inflate(6.0D))) {
                                if (nearbyEntity instanceof LivingEntity && nearbyEntity != target) {
                                    LivingEntity livingEntity = (LivingEntity) nearbyEntity;
                                    livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 3, false, false, true)); // Lentidão III
                                    livingEntity.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, false, false, true)); // Cegueira
                                }
                            }
                            // Verificar se o atacante é uma entidade viva
                            if (event.getEntityLiving() instanceof LivingEntity) {
                                LivingEntity attacker = event.getEntityLiving();

                                // Verificar se o atacante é um Stand
                                if (attacker instanceof StandEntity) {
                                    StandEntity standAttacker = (StandEntity) attacker;

                                    // Acessando o usuário do Stand atacante
                                    LivingEntity standUser = standAttacker.getUser();  // Método para obter o usuário do Stand

                                    // Verifique se o usuário do Stand é válido
                                    if (standUser != null) {
                                        // Aplicar as penalidades ao usuário do Stand adversário
                                        standUser.hurt(DamageSource.MAGIC, 2.0F); // Dano mágico ao usuário do Stand adversário
                                        standUser.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I
                                        standUser.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                        standUser.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                                    }
                                }
                                // Se o atacante for um PlayerEntity ou MobEntity (não Stand)
                                else if (attacker instanceof PlayerEntity || attacker instanceof MobEntity) {
                                    // Aplicar os efeitos diretamente ao atacante
                                    attacker.hurt(DamageSource.MAGIC, 2.0F); // Dano mágico ao atacante
                                    attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I
                                    attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                    attacker.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                                }
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
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        // Verifica se o projétil é uma instância de ProjectileEntity (ou seja, qualquer projétil)
        if (event.getEntity() instanceof ProjectileEntity) {
            ProjectileEntity projectile = (ProjectileEntity) event.getEntity(); // Faz o cast da entidade para ProjectileEntity

            // Verifica se o projétil atingiu uma entidade
            if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
                Entity entityHit = ((EntityRayTraceResult) event.getRayTraceResult()).getEntity();

                // Verifica se a entidade atingida é o alvo com "Wonder of You"
                if (entityHit instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) entityHit;

                    IStandPower.getStandPowerOptional(target).ifPresent(power -> {
                        if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                            if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                                WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
                                if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                                    // Remove o projétil do mundo
                                    projectile.remove();  // Remover o projétil do mundo

                                    // Cancela o impacto para que o projétil não cause dano
                                    event.setCanceled(true);
                                }
                            }
                        }
                    });
                }
            }
        }
    }
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        // Verifica se o jogador está no servidor e se a habilidade CalamityActive está ativada
        if (!event.player.level.isClientSide()) {
            IStandPower.getStandPowerOptional(event.player).ifPresent(power -> {
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();

                        // Verifica se a habilidade CalamityActive está ativa
                        if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                            // Drena 2 de stamina por tick
                            drainStamina(power);
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;

        // Certificar-se de que estamos no lado do servidor
        if (!player.level.isClientSide()) {
            IStandPower.getStandPowerOptional(player).ifPresent(power -> {
                // Verifica se o jogador tem o Stand "Wonder Of You" ativo
                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();

                        // Verifica se a habilidade "CalamityActive" está ativada
                        if (wonderOfYouEntity.isCalamityActiveEnabled()) {
                            // Itera sobre todas as entidades próximas
                            for (Entity nearbyEntity : player.level.getEntities(player, player.getBoundingBox().inflate(6.0D))) {
                                if (nearbyEntity instanceof PlayerEntity && nearbyEntity != player) {
                                    PlayerEntity nearbyPlayer = (PlayerEntity) nearbyEntity;

                                    // Aplicar os efeitos negativos
                                    nearbyPlayer.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 3, false, false, true)); // Lentidão III
                                    nearbyPlayer.addEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, false, false, true)); // Cegueira
                                    nearbyPlayer.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I

                                    nearbyPlayer.hurt(DamageSource.MAGIC, 0.5F);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}