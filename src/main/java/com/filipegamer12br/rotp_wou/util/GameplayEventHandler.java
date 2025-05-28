package com.filipegamer12br.rotp_wou.util;

import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import com.filipegamer12br.rotp_wou.init.InitEntities;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
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
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.filipegamer12br.rotp_wou.action.CalamityActive.drainStamina;
import static com.filipegamer12br.rotp_wou.action.CalamityAttack.drainStamina2;

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
                            event.setAmount(damageToOurWOU * 0.85F);

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
                                        standUser.addEffect(new EffectInstance(Effects.POISON, 100, 3, false, false, true)); // Veneno III
                                        standUser.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                        standUser.addEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false, true)); // Cegueira
                                    }
                                }
                                // Se o atacante for um PlayerEntity ou MobEntity (não Stand)
                                else if (attacker instanceof PlayerEntity || attacker instanceof MobEntity) {
                                    // Aplicar os efeitos ao atacante diretamente
                                    attacker.hurt(event.getSource(), damageToOurWOU); // Dano ao atacante
                                    attacker.addEffect(new EffectInstance(Effects.POISON, 100, 3, false, false, true)); // Veneno III
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
                                        standUser.addEffect(new EffectInstance(Effects.CONFUSION, 60, 0, false, false, true)); // Nausea
                                    }
                                }
                                // Se o atacante for um PlayerEntity ou MobEntity (não Stand)
                                else if (attacker instanceof PlayerEntity || attacker instanceof MobEntity) {
                                    // Aplicar os efeitos diretamente ao atacante
                                    attacker.hurt(DamageSource.MAGIC, 2.0F); // Dano mágico ao atacante
                                    attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I
                                    attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, false, true)); // Lentidão II
                                    attacker.addEffect(new EffectInstance(Effects.CONFUSION, 60, 0, false, false, true)); // Nausea
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    /*@SubscribeEvent
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
    }*/
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
                        if (wonderOfYouEntity.isCalamityCarAttackEnabled()) {
                            // Drena 4 de stamina por tick
                            drainStamina2(power);
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
                                    nearbyPlayer.addEffect(new EffectInstance(Effects.CONFUSION, 60, 0, false, false, true)); // Nausea
                                    nearbyPlayer.addEffect(new EffectInstance(Effects.POISON, 100, 1, false, false, true)); // Veneno I

                                    nearbyPlayer.hurt(DamageSource.MAGIC, 0.5F);
                                }
                            }
                        }
                    }
                }
            });
        }
        //phantom walk ability
//        if (!player.level.isClientSide()) {
//            IStandPower.getStandPowerOptional(player).ifPresent(power -> {
//                // Verifica se o jogador tem o Stand "Wonder Of You" ativo
//                if (power.getType() == InitStands.WONDER_OF_YOU.getStandType() && power.isActive()) {
//                    if (power.getStandManifestation() instanceof WonderOfYouEntity) {
//                        WonderOfYouEntity wonderOfYouEntity = (WonderOfYouEntity) power.getStandManifestation();
//
//                        // Verifica se a habilidade Phantom Walk está ativada (enquanto o player está agachado)
//                        if (wonderOfYouEntity.isPhantomWalkEnabled()) {
//                            if (player.isShiftKeyDown()) { // Verifica se o jogador está agachado
//                                // Habilidade ativa enquanto o jogador está agachado
//                                activatePhantomWalk(wonderOfYouEntity, player);
//                            }
//                        }
//                    }
//                }
//            });
//        }
    }

    // Função que ativa a habilidade Phantom Walk para o jogador e o Stand
//    public static void activatePhantomWalk(WonderOfYouEntity wonderOfYouEntity, PlayerEntity player) {
//        // A posição do Stand (ou do jogador) para onde ele deve se mover
//        Vector3d standPos = wonderOfYouEntity.position();
//        Vector3d lookVector = wonderOfYouEntity.getLookAngle();
//
//        // Distância que o Stand ou jogador vai se mover
//        double distance = 2.0;
//        Vector3d targetPos = standPos.add(lookVector.x * distance, 0, lookVector.z * distance);
//        BlockPos targetBlockPos = new BlockPos(targetPos);
//
//        // Verifica se o bloco à frente é sólido (para atravessar a parede)
//        if (player.level.getBlockState(targetBlockPos).getMaterial().isSolid()) {
//            // Teleporta o Stand para atravessar a parede
//            wonderOfYouEntity.teleportTo(targetPos.x, targetPos.y, targetPos.z);
//
//            // Teleporta o jogador para a mesma posição
//            player.teleportTo(targetPos.x, targetPos.y, targetPos.z);
//        }
//    }



    private static WonderOfYouEntity getStandEntityForPlayer(Entity owner) {
        if (owner instanceof PlayerEntity) {
            return IStandPower.getStandPowerOptional((PlayerEntity) owner)
                    .filter(power -> power.getType() == InitStands.WONDER_OF_YOU.getStandType())
                    .map(power -> (WonderOfYouEntity) power.getStandManifestation())
                    .orElse(null);
        }
        return null;
    }

    //car calamity
    @SubscribeEvent
    public static void carSummon(LivingAttackEvent event) {
        LivingEntity user = event.getEntityLiving();

        if (!user.level.isClientSide) {
            Entity attacker = event.getSource().getEntity();

            if (user instanceof WonderOfYouEntity) {
                WonderOfYouEntity wonderOfYou = (WonderOfYouEntity) user;

                if (attacker instanceof LivingEntity && wonderOfYou.isCalamityCarAttackEnabled()) {
                    carlamity(wonderOfYou, (LivingEntity) attacker, event);
                }

            } else {
                StandType<?> WOU = InitStands.WONDER_OF_YOU.getStandType();

                IStandPower.getStandPowerOptional(user).ifPresent(power -> {
                    if (power.getType() == WOU && power.getStandManifestation() instanceof StandEntity) {
                        WonderOfYouEntity wonderOfYou = (WonderOfYouEntity) power.getStandManifestation();

                        if (wonderOfYou != null && wonderOfYou.isCalamityCarAttackEnabled() && attacker instanceof LivingEntity) {
                            carlamity(wonderOfYou, (LivingEntity) attacker, event);
                        }
                    }
                });
            }
        }
    }

    public static void carlamity(WonderOfYouEntity wonderOfYou, LivingEntity attacker, LivingAttackEvent event) {
        event.setCanceled(true);

        long now = System.currentTimeMillis();

        // Cooldown de 6 segundos (6000 ms)
        if (now - wonderOfYou.getLastCarSpawnTime() < 6000) {
            return;
        }

        if (wonderOfYou.getCarProjectile() == null) {
            double x = attacker.getX();
            double y = attacker.getY() + 7;
            double z = attacker.getZ();

            CarProjectileEntity carProjectile = new CarProjectileEntity(InitEntities.CAR_PROJECTILE.get(), wonderOfYou.level);
            LivingEntity owner = wonderOfYou.getUser() != null ? wonderOfYou.getUser() : wonderOfYou;

            carProjectile.setOwner(owner);
            carProjectile.setTarget(attacker);
            carProjectile.teleportTo(x, y, z);

            wonderOfYou.level.addFreshEntity(carProjectile);
            wonderOfYou.setCarProjectile(carProjectile);
            wonderOfYou.setLastCarSpawnTime(now);
        } else {
            wonderOfYou.getCarProjectile().setTarget(attacker);
        }
    }




}