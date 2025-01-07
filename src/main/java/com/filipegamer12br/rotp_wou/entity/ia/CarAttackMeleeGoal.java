package com.filipegamer12br.rotp_wou.entity.ia;

import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;

public class CarAttackMeleeGoal extends Goal {
    protected final CarProjectileEntity mob;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private final int attackInterval = 20;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public CarAttackMeleeGoal(CarProjectileEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
        this.mob = p_i1636_1_;
        this.speedModifier = p_i1636_2_;
        this.followingTargetEvenIfNotSeen = p_i1636_4_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if(this.mob.getHits() < this.mob.getHitsBeforeExplode()){
            long i = this.mob.level.getGameTime();
            if (i - this.lastCanUseCheck < 20L) {
                return false;
            } else {
                this.lastCanUseCheck = i;
                LivingEntity livingentity = this.mob.getTarget();
                if (livingentity == null) {
                    return false;
                } else if (!livingentity.isAlive()) {
                    return false;
                } else {
                    if (canPenalize) {
                        if (--this.ticksUntilNextPathRecalculation <= 0) {
                            this.path = this.mob.getNavigation().createPath(livingentity, 0);
                            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                            return this.path != null;
                        } else {
                            return true;
                        }
                    }
                    this.path = this.mob.getNavigation().createPath(livingentity, 0);
                    if (this.path != null) {
                        return true;
                    } else {
                        return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                    }
                }
            }
        }else {return false;}

    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.mob.getNavigation().isDone();
        } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
            return false;
        } else {
            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
        }
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.mob.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        LivingEntity livingentity = this.mob.getTarget();
        if (!EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.mob.setTarget((LivingEntity)null);
        }

        this.mob.setAggressive(false);
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);

        // Calcular a direção da rotação necessária para o alvo
        double deltaX = livingentity.getX() - this.mob.getX();
        double deltaZ = livingentity.getZ() - this.mob.getZ();
        float targetRotation = (float) Math.atan2(deltaZ, deltaX);

        // Ajuste suave da rotação
        float deltaRotation = MathHelper.degreesDifference(this.mob.yRot, targetRotation);
        this.mob.yRot += deltaRotation * 0.1F; // Ajuste a suavização com o fator desejado

        double distanceToTarget = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);

        if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().canSee(livingentity)) &&
                this.ticksUntilNextPathRecalculation <= 0 &&
                (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D ||
                        livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D ||
                        this.mob.getRandom().nextFloat() < 0.05F)) {

            this.pathedTargetX = livingentity.getX();
            this.pathedTargetY = livingentity.getY();
            this.pathedTargetZ = livingentity.getZ();
            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
            if (this.canPenalize) {
                this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                if (this.mob.getNavigation().getPath() != null) {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                    if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                } else {
                    failedPathFindingPenalty += 10;
                }
            }

            if (distanceToTarget > 1024.0D) {
                this.ticksUntilNextPathRecalculation += 10;
            } else if (distanceToTarget > 256.0D) {
                this.ticksUntilNextPathRecalculation += 5;
            }

            if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                this.ticksUntilNextPathRecalculation += 15;
            }
        }

        this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        // Verifica se está na distância de ataque
        if (distanceToTarget <= this.getAttackReachSqr(livingentity)) {
            if (this.ticksUntilNextAttack <= 0) {
                this.resetAttackCooldown();
                this.mob.swing(Hand.MAIN_HAND);
                this.mob.doHurtTarget(livingentity);
                this.mob.setDataHits(this.mob.getHits() + 1);  // Aumenta os hits
            }
        }
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = 20;
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected int getAttackInterval() {
        return 20;
    }

    protected double getAttackReachSqr(LivingEntity p_179512_1_) {
        return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
    }
}
