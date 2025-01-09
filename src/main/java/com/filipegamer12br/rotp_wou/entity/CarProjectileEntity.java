package com.filipegamer12br.rotp_wou.entity;

import com.filipegamer12br.rotp_wou.entity.ia.CarAttackMeleeGoal;
import com.filipegamer12br.rotp_wou.entity.ia.CarExplodeGoal;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class CarProjectileEntity extends TameableEntity {
    private int oldSwell;
    private static final DataParameter<Integer> DATA_SWELL_DIR = EntityDataManager.defineId(CarProjectileEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_HITS = EntityDataManager.defineId(CarProjectileEntity.class, DataSerializers.INT);
    private int hitsBeforeExplode = 2;
    private int maxSwell = 2;
    private int explosionRadius = 3;
    private int swell;

    public CarProjectileEntity(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }


    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }


    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("hits",entityData.get(DATA_HITS));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_HITS,0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new CarAttackMeleeGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new CarExplodeGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 7.5D)
                ;
    }

    @Override
    public void tick() {
        if(!this.level.isClientSide){
            if (this.isAlive()) {
                if(this.getTarget() != null){

                    if(!this.getTarget().isAlive()){
                        this.setTarget(null);
                        setDataHits(0);
                    }

                }

                this.oldSwell = this.swell;


                int i = this.getSwellDir();
                if (i > 0 && this.swell == 0) {

                }

                this.swell += i;
                if (this.swell < 0) {
                    this.swell = 0;
                }

                if (this.swell >= this.maxSwell) {
                    this.swell = this.maxSwell;
                    this.explodeCar();

                }

                if(this.getOwner() != null){
                    if(this.getTarget() == this.getOwner()){
                        this.remove();
                    }
                    IStandPower.getStandPowerOptional(this.getOwner()).ifPresent(power -> {
                        if(power.getStandManifestation() instanceof WonderOfYouEntity){
                            WonderOfYouEntity wonderOfYou = (WonderOfYouEntity) power.getStandManifestation();
                            if(wonderOfYou.getCarProjectile() != this){
                                wonderOfYou.setCarProjectile(this);
                            }
                        }
                    });
                }
            }
        }

        super.tick();
    }


    private void explodeCar() {
        if (!this.level.isClientSide) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.dead = true;

            LivingEntity explo = this.getOwner() == null?this:this.getOwner();

            this.level.explode(explo, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius, explosion$mode);
            if(this.getTarget() != null){
                if(!this.getTarget().isAlive()){
                    this.remove();
                }
            }

        }

    }

    public int getHitsBeforeExplode() {
        return hitsBeforeExplode;
    }

    public int getHits(){
        return this.entityData.get(DATA_HITS);
    }

    public void setDataHits(int hits){
        this.entityData.set(DATA_HITS, hits);
    }


    public void setSwellDir(int p_70829_1_) {
        this.entityData.set(DATA_SWELL_DIR, p_70829_1_);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return InitSounds.CAR_HURT.get();
    }
}