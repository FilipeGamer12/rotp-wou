//package com.filipegamer12br.rotp_wou.entity.ia;
//
//import com.filipegamer12br.rotp_wou.entity.CarProjectileEntity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.goal.Goal;
//
//import java.util.EnumSet;
//
//public class CarExplodeGoal extends Goal {
//
//    private final CarProjectileEntity mob;
//    private LivingEntity target;
//    private int ticksWithoutTarget; // Contador para o tempo sem alvo
//
//    public CarExplodeGoal(CarProjectileEntity shearheart) {
//        this.mob = shearheart;
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
//    }
//
//    @Override
//    public boolean canUse() {
//        if(this.mob.getHits() >= this.mob.getHitsBeforeExplode()){
//            LivingEntity livingentity = this.mob.getTarget();
//            return this.mob.getSwellDir() > 0 || livingentity != null && this.mob.distanceToSqr(livingentity) < 9.0D;
//        }
//        return false;
//    }
//
//    @Override
//    public void start() {
//        this.mob.getNavigation().stop();
//        this.target = this.mob.getTarget();
//    }
//
//
//    @Override
//    public void stop() {
//        this.target = null;
//    }
//
//    @Override
//    public void tick() {
//        if (this.target == null) {
//            this.mob.setSwellDir(-1);
//        } else if (this.mob.distanceToSqr(this.target) > 49.0D) {
//            this.mob.setSwellDir(-1);
//        } else if (!this.mob.getSensing().canSee(this.target)) {
//            this.mob.setSwellDir(-1);
//        } else {
//            this.mob.setSwellDir(1);
//        }
//        if (this.ticksWithoutTarget > 100) {
//            this.mob.remove();
//        }
//    }
//}
