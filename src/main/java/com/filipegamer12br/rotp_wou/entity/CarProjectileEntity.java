package com.filipegamer12br.rotp_wou.entity;

import com.filipegamer12br.rotp_wou.init.InitSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class CarProjectileEntity extends TameableEntity {
    private static final DataParameter<Integer> DATA_HITS = EntityDataManager.defineId(CarProjectileEntity.class, DataSerializers.INT);
    private int explosionRadius = 6;

    public CarProjectileEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HITS, 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level.isClientSide && this.isAlive()) {
            if (this.onGround || this.horizontalCollision || this.verticalCollision) {
                this.explodeCar();
            }
        }
    }

    private void explodeCar() {
        if (!this.level.isClientSide) {
            Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius, mode);

            ServerWorld serverWorld = (ServerWorld) this.level;
            serverWorld.sendParticles(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 200, 3.0, 3.0, 3.0, 0.1);
            serverWorld.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 100, 3.0, 3.0, 3.0, 0.1);

            this.remove();
        }
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D);
//                .add(Attributes.MOVEMENT_SPEED, 0.6D)
//                .add(Attributes.ATTACK_DAMAGE, 10.0D)
//                .add(Attributes.FOLLOW_RANGE, 64.0D)
//                .add(Attributes.ATTACK_KNOCKBACK, 7.5D);
    }

    @Override
    public boolean isNoGravity() {
        return false;
    }

//    public void setDataHits(int hits) {
//        this.entityData.set(DATA_HITS, hits);
//    }

    public int getHits() {
        return this.entityData.get(DATA_HITS);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("hits", getHits());
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return InitSounds.CAR_HURT.get();
    }

//    public void setOwner(LivingEntity owner) {
//    }

    //@Override
    public ITextComponent getDeathMessage(CombatTracker tracker) {
        return new StringTextComponent("");
    }

}

