package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class LightningStrike extends StandEntityAction {

    public LightningStrike(Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            RayTraceResult target = standEntity.aimWithStandOrUser(100, task.getTarget()); // Alcança até 100 blocos
            Vector3d pos = target.getLocation();
            if (pos != null) {
                // Invoca o raio no local
                standEntity.playSound(InitSounds.CALAMITY4.get(), 1F, 1);
                LightningBoltEntity bolt = EntityType.LIGHTNING_BOLT.create(world);
                bolt.moveTo(pos);
                if (userPower.getUser() instanceof ServerPlayerEntity) {
                    bolt.setCause((ServerPlayerEntity) userPower.getUser());
                }
                world.addFreshEntity(bolt);
            }
        }
    }

    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.ENTITY; // Mantém como alvo apenas entidades
    }
}
