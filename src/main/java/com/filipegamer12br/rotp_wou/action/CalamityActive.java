package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.world.World;

public class CalamityActive extends StandEntityAction {
    public CalamityActive(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            WonderOfYouEntity wouEntity = (WonderOfYouEntity) standEntity;
            if (wouEntity != null) {
                // Ativa a habilidade "Calamity Active"
                wouEntity.setIsCalamityActiveEnabled(!wouEntity.isCalamityActiveEnabled());
                System.out.println("Calamity Active: " + wouEntity.isCalamityActiveEnabled());
            }
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        if (power != null && power.getStandManifestation() instanceof WonderOfYouEntity) {
            return ((WonderOfYouEntity) power.getStandManifestation()).isCalamityActiveEnabled();
        }
        return false;
    }
}
