package com.filipegamer12br.rotp_wou.util;

import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import com.filipegamer12br.rotp_wou.init.InitEntities;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
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
import com.github.standobyte.jojo.potion.ImmobilizeEffect;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static com.filipegamer12br.rotp_wou.action.CalamityActive.drainStamina;
import static sun.audio.AudioPlayer.player;

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
    }

    // Car Calamity


    @SubscribeEvent
    public static void onCarProjectileTick(TickEvent.WorldTickEvent event) {
        if (event.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.world;

            // Obter todas as entidades do tipo CarProjectileEntity
            List<CarProjectileEntity> carProjectiles = serverWorld.getEntitiesOfClass(
                    CarProjectileEntity.class,
                    new AxisAlignedBB(-50, -50, -50, 50, 50, 50)
            );

            for (CarProjectileEntity carProjectile : carProjectiles) {
                handleCarProjectile(carProjectile, serverWorld);
            }
        }
    }

    private static void handleCarProjectile(CarProjectileEntity carProjectile, ServerWorld world) {
        // Verificar se o carro ainda está em movimento
        if (carProjectile.owner != null) {
            // Acelerar o movimento em direção ao alvo
            Vector3d direction = getDirectionToTarget(carProjectile, world);
            carProjectile.setDeltaMovement(carProjectile.getDeltaMovement().add(direction.scale(0.05))); // Aceleração

            // Movimenta o carro
            carProjectile.move(MoverType.SELF, carProjectile.getDeltaMovement());

            // Verificar colisão com entidades
            AxisAlignedBB aabb = carProjectile.getBoundingBox().inflate(0.5);
            List<LivingEntity> hitEntities = world.getEntitiesOfClass(LivingEntity.class, aabb, entity -> entity != carProjectile.owner);

            for (LivingEntity hitEntity : hitEntities) {
                if (hitEntity.isAlive()) {
                    applyCarImpactEffects(carProjectile, hitEntity);
                    createExplosion(carProjectile, world);
                    carProjectile.remove();
                    return;
                }
            }
        }
    }

    private static void applyCarImpactEffects(CarProjectileEntity carProjectile, LivingEntity target) {
        // Aplica o efeito de imobilização (stun) no alvo
        target.addEffect(new EffectInstance(new ImmobilizeEffect(0x0000FF), 100, 0, false, false, true));

        // Se a habilidade Calamity estiver ativa, causar dano adicional
        WonderOfYouEntity wouEntity = getStandEntityForPlayer(carProjectile.owner);
        if (wouEntity != null && wouEntity.isCalamityCarAttackEnabled()) {
            target.hurt(DamageSource.MAGIC, 5.0F); // Dano adicional
        }
    }

    private static WonderOfYouEntity getStandEntityForPlayer(Entity owner) {
        if (owner instanceof PlayerEntity) {
            return IStandPower.getStandPowerOptional((PlayerEntity) owner)
                    .filter(power -> power.getType() == InitStands.WONDER_OF_YOU.getStandType())
                    .map(power -> (WonderOfYouEntity) power.getStandManifestation())
                    .orElse(null);
        }
        return null;
    }

    private static void createExplosion(CarProjectileEntity carProjectile, ServerWorld world) {
        // Cria a explosão no local do carro
        world.explode(
                carProjectile,
                carProjectile.getX(),
                carProjectile.getY(),
                carProjectile.getZ(),
                4.0F,
                Explosion.Mode.NONE
        );
    }

    private static Vector3d getDirectionToTarget(CarProjectileEntity carProjectile, ServerWorld world) {
        // A posição do alvo (último alvo do jogador dono do carro)
        if (carProjectile.owner instanceof LivingEntity) {
            LivingEntity livingOwner = (LivingEntity) carProjectile.owner;
            LivingEntity target = livingOwner.getLastHurtMob(); // Agora você pode acessar o método
            if (target != null && target.isAlive()) {
                // Aplicar efeitos de impacto no alvo
                spawnCarAndStunTarget(world, target, livingOwner); // Chamar o método para tratar o stun
            }

            // Calcula a direção do carro para o alvo
            Vector3d targetPosition = (target != null && target.isAlive()) ? target.position() : carProjectile.position();
            return targetPosition.subtract(carProjectile.position()).normalize(); // Normaliza a direção para garantir que o carro se mova em linha reta
        }
        return Vector3d.ZERO; // Retorna uma direção nula se não houver dono ou alvo
    }

    private static void spawnCarAndStunTarget(ServerWorld world, LivingEntity target, LivingEntity owner) {
        // Aqui você pode adicionar a lógica de spawn do carro e aplicar o efeito de stun
        target.addEffect(new EffectInstance(new ImmobilizeEffect(0x0000FF), 100, 0, false, false, true));

        // Spawn da entidade CarProjectileEntity
        CarProjectileEntity carProjectile = new CarProjectileEntity(InitEntities.CAR_PROJECTILE.get(), world);
        carProjectile.setOwner(owner); // Define o dono do carro como o jogador ou entidade controladora
        carProjectile.setPos(target.getX(), target.getY(), target.getZ()); // Define a posição inicial do carro próximo ao alvo
        world.addFreshEntity(carProjectile); // Adiciona a entidade no mundo
    }
}