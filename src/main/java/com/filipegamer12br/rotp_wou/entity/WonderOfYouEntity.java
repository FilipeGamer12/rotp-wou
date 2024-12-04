package com.filipegamer12br.rotp_wou.entity;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class WonderOfYouEntity extends StandEntity {
    private static final DataParameter<Boolean> IS_CALAMITY_ENABLED =
            EntityDataManager.defineId(WonderOfYouEntity.class, DataSerializers.BOOLEAN);

    public WonderOfYouEntity(StandEntityType<WonderOfYouEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_CALAMITY_ENABLED, false);
    }
    
    public boolean isCalamityEnabled() {
        return entityData.get(IS_CALAMITY_ENABLED);
    }
    
    public void setIsCalamityEnabled(boolean status) {
        entityData.set(IS_CALAMITY_ENABLED, status);
    }
}
