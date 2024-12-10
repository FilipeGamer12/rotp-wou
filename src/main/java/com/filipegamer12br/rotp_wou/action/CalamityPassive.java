package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.world.World;

public class CalamityPassive extends StandEntityAction {
    public CalamityPassive(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            WonderOfYouEntity wouEntity = (WonderOfYouEntity) standEntity;
            if (wouEntity != null){
                wouEntity.setIsCalamityPassiveEnabled(!wouEntity.isCalamityPassiveEnabled());
                if(wouEntity.isCalamityPassiveEnabled()){ // Special thanks to Hoo
                    standEntity.playSound(InitSounds.CALAMITY.get(),0.3F,1);
                }
                System.out.println(wouEntity.isCalamityPassiveEnabled());
            }
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        if (power != null && power.getStandManifestation() instanceof WonderOfYouEntity){
            return ((WonderOfYouEntity) power.getStandManifestation()).isCalamityPassiveEnabled();
        }
        return false;
    }
}
