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

    private static final long COOLDOWN_TICKS = 20 * 6; // 6 segundos
    private long lastActivationTick = -COOLDOWN_TICKS;

    public CalamityAttack(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide() && standEntity instanceof WonderOfYouEntity) {
            WonderOfYouEntity wouEntity = (WonderOfYouEntity) standEntity;
            long currentTick = world.getGameTime();

            // Impede reativação durante o cooldown
            if ((currentTick - lastActivationTick) < COOLDOWN_TICKS) {
                return;
            }

            // Alterna o estado da habilidade
            boolean currentlyEnabled = wouEntity.isCalamityCarAttackEnabled();
            wouEntity.setIsCalamityCarAttackEnabled(!currentlyEnabled);

            // Se ativando, atualiza cooldown e toca som
            if (!currentlyEnabled) {
                standEntity.playSound(InitSounds.CALAMITY_CAR_ATTACK.get(), 1F, 1);
                lastActivationTick = currentTick;
            }

            System.out.println("Calamity Attack: " + wouEntity.isCalamityCarAttackEnabled());
        }
    }

    public static void drainStamina2(IStandPower userPower) {
        if (userPower != null && userPower.getMaxStamina() > 0) {
            // Drena 4 de stamina por tick
            float staminaDrain = 4.0F;
            if (userPower.getStamina() >= staminaDrain) {
                userPower.consumeStamina(staminaDrain);
            } else {
                // Caso o usuário não tenha stamina suficiente, talvez definir a stamina como 0
                userPower.consumeStamina(userPower.getStamina());  // Drena toda a stamina restante
            }
            if (userPower.getStamina() <= 0) {
                if (userPower.getStandManifestation() instanceof WonderOfYouEntity) {
                    WonderOfYouEntity wouEntity = (WonderOfYouEntity) userPower.getStandManifestation();
                    wouEntity.setIsCalamityCarAttackEnabled(false); // Desativa CalamityAttack
                }
            }
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        if (power != null && power.getStandManifestation() instanceof WonderOfYouEntity) {
            return ((WonderOfYouEntity) power.getStandManifestation()).isCalamityCarAttackEnabled();
        }
        return false;
    }
}