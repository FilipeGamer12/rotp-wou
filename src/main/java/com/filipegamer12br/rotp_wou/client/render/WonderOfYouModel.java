package com.filipegamer12br.rotp_wou.client.render;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.IModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;

//import net.minecraft.client.renderer.model.ModelRenderer;

public class WonderOfYouModel extends HumanoidStandModel<WonderOfYouEntity> {

    public WonderOfYouModel() {
        super();
    }

    @Override // TODO summon poses
    protected RotationAngle[][] initSummonPoseRotations() {
        return new RotationAngle[][] {
                new RotationAngle[] {
                },
                new RotationAngle[] {
                }
        };
    }

    @Override // TODO idle pose
    protected ModelPose<WonderOfYouEntity> initIdlePose() {
        return super.initIdlePose();
    }

    @Override
    protected IModelPose<WonderOfYouEntity> initIdlePose2Loop() {
        return super.initIdlePose2Loop();
    }
}
