package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitSounds;
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
                // Desativa CalamityPassive quando CalamityActive é ativado
                if (wouEntity.isCalamityPassiveEnabled()) {
                    wouEntity.setIsCalamityPassiveEnabled(false);
                }

                // Alterna o estado de CalamityActive
                wouEntity.setIsCalamityActiveEnabled(!wouEntity.isCalamityActiveEnabled());

                if (wouEntity.isCalamityActiveEnabled()) {
                    standEntity.playSound(InitSounds.CALAMITY3.get(), 1F, 1);
                }
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

    // Método para drenagem de stamina
    public static void drainStamina(IStandPower userPower) {
        if (userPower != null && userPower.getMaxStamina() > 0) {
            // Drena 2 de stamina por tick
            float staminaDrain = 2.0F;
            if (userPower.getStamina() >= staminaDrain) {
                userPower.consumeStamina(staminaDrain);
            } else {
                // Caso o usuário não tenha stamina suficiente, talvez definir a stamina como 0
                userPower.consumeStamina(userPower.getStamina());  // Drena toda a stamina restante
            }
            if (userPower.getStamina() <= 0) {
                if (userPower.getStandManifestation() instanceof WonderOfYouEntity) {
                    WonderOfYouEntity wouEntity = (WonderOfYouEntity) userPower.getStandManifestation();
                    wouEntity.setIsCalamityActiveEnabled(false); // Desativa CalamityActive
                }
            }
        }
    }
}
