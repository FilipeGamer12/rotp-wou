package com.filipegamer12br.rotp_wou.action;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class ExplodeVariations extends StandEntityAction {
    private String nameOfTarget;

    public ExplodeVariations(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public ActionConditionResult checkTarget(ActionTarget target, LivingEntity user, IStandPower power) {
        if (target.getType() == ActionTarget.TargetType.ENTITY) {
            Entity targetEntity = target.getEntity();
            if (targetEntity.is(power.getUser())) {
                return conditionMessage("cant_attack_self");
            } else if (targetEntity instanceof StandEntity) {
                return conditionMessage("cant_attack_stand");
            }
            nameOfTarget = targetEntity.getName().getString();
        } else if (target.getType() == ActionTarget.TargetType.BLOCK) {
            BlockPos blockPos = target.getBlockPos();
            BlockState blockState = user.level.getBlockState(blockPos);
            nameOfTarget = blockState.getBlock().getName().getString();
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ANY;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask target) {
        if (!world.isClientSide()) {
            Vector3d pos;
            if (target.getTarget().getType() == ActionTarget.TargetType.ENTITY && target.getTarget().getEntity() instanceof LivingEntity) {
                pos = target.getTarget().getEntity().position();
            } else if (target.getTarget().getType() == ActionTarget.TargetType.BLOCK) {
                BlockPos blockPos = target.getTarget().getBlockPos();
                pos = new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            } else {
                pos = null;
            }

            if (pos != null) {
                Random random = new Random();
                switch (random.nextInt(3)) {
                    case 0:
                        BlockPos targetBlockPos = new BlockPos(pos.x, pos.y, pos.z);

                        TNTEntity tnt = new TNTEntity(world, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), null);
                        world.addFreshEntity(tnt);

                        tnt.setFuse(10);

                        if (world.getServer() == null)
                            return; // Because have some problem with Mobs and with LAN servers

                        world.getServer().execute(() -> {
                            Explosion explosion = new Explosion(world, tnt, null, null, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), 0.0F, false, Explosion.Mode.NONE);
                            explosion.explode();
                        });
                        break;
                    case 2:
                        CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
                        creeper.setPos(pos.x, pos.y, pos.z);
                        world.addFreshEntity(creeper);
                        creeper.ignite();
                        break;
                    case 1:
                    default:
                        world.explode(standEntity, pos.x, pos.y + 0.5f, pos.z, 1.0f, false, Explosion.Mode.NONE);
                        break;
                }
            }
        }
    }

    @Override
    public IFormattableTextComponent getTranslatedName(IStandPower power, String key) {
        LivingEntity user = power.getUser();
        if (nameOfTarget != null) {
            return new TranslationTextComponent(key, nameOfTarget);
        } else {
            return new TranslationTextComponent(key, new TranslationTextComponent("rotp_wou.explosion.nothing"));
        }
    }
}
