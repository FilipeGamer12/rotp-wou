package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.world.World;

public class CalamityAttack extends StandEntityAction {
    public CalamityAttack(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            WonderOfYouEntity wouEntity = (WonderOfYouEntity) standEntity;
            if (wouEntity != null) {
                // Ativa o modo de ataque "CalamityAttack"
                if (!wouEntity.isCalamityCarAttackEnabled()) {
                    wouEntity.setIsCalamityCarAttackEnabled(true);
                    standEntity.playSound(InitSounds.CALAMITY_CAR_ATTACK.get(), 1F, 1);
                } else {
                    // Caso a habilidade já esteja ativa, pode desativá-la ou trocar o estado
                    wouEntity.setIsCalamityCarAttackEnabled(false);
                }

                System.out.println("Calamity Attack: " + wouEntity.isCalamityCarAttackEnabled());
            }
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        if (power != null && power.getStandManifestation() instanceof WonderOfYouEntity) {
            // Verifica se a habilidade está ativa
            return ((WonderOfYouEntity) power.getStandManifestation()).isCalamityCarAttackEnabled();
        }
        return false;
    }
}
