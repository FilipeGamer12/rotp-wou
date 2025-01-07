package com.filipegamer12br.rotp_wou.entity;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class WonderOfYouEntity extends StandEntity {
    private static final DataParameter<Boolean> IS_CALAMITY_PASSIVE_ENABLED =
            EntityDataManager.defineId(WonderOfYouEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_CALAMITY_ACTIVE_ENABLED =
            EntityDataManager.defineId(WonderOfYouEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_CALAMITY_CAR_ATTACK_ENABLED =
            EntityDataManager.defineId(WonderOfYouEntity.class, DataSerializers.BOOLEAN); // Novo parâmetro para CalamityAttack

    private CarProjectileEntity carProjectile;


    public WonderOfYouEntity(StandEntityType<WonderOfYouEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_CALAMITY_PASSIVE_ENABLED, false);
        entityData.define(IS_CALAMITY_ACTIVE_ENABLED, false);
        entityData.define(IS_CALAMITY_CAR_ATTACK_ENABLED, false); // Definindo o estado inicial de CalamityAttack
    }

    @Override
    public void tick() {
        super.tick();
        if(!level.isClientSide()){
            if(carProjectile != null && !carProjectile.isAlive()){
                carProjectile = null;
            }
        }
    }

    public boolean isCalamityPassiveEnabled() {
        return entityData.get(IS_CALAMITY_PASSIVE_ENABLED);
    }

    public void setIsCalamityPassiveEnabled(boolean status) {
        entityData.set(IS_CALAMITY_PASSIVE_ENABLED, status);
    }

    public boolean isCalamityActiveEnabled() {
        return entityData.get(IS_CALAMITY_ACTIVE_ENABLED);
    }

    public void setIsCalamityActiveEnabled(boolean status) {
        entityData.set(IS_CALAMITY_ACTIVE_ENABLED, status);
    }

    // Métodos para CalamityAttack
    public boolean isCalamityCarAttackEnabled() {
        return entityData.get(IS_CALAMITY_CAR_ATTACK_ENABLED);
    }

    public void setIsCalamityCarAttackEnabled(boolean status) {
        entityData.set(IS_CALAMITY_CAR_ATTACK_ENABLED, status);
    }


    public CarProjectileEntity getCarProjectile() {
        return this.carProjectile;
    }

    public void setCarProjectile(CarProjectileEntity carProjectile) {
        this.carProjectile = carProjectile;
    }

}