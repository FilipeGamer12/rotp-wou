package com.filipegamer12br.rotp_wou.client.render;

import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.IModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import net.minecraft.client.renderer.model.ModelRenderer;


public class WonderOfYouModel extends HumanoidStandModel<WonderOfYouEntity> {

    ModelRenderer stick;
    ModelRenderer cube_r17;
    ModelRenderer cube_r18;
    ModelRenderer bone;
    ModelRenderer bone2;
    ModelRenderer bone3;
    ModelRenderer bone4;
    ModelRenderer scarfEdge2;
    ModelRenderer skarf;

    public WonderOfYouModel() {
        super();
    }

    @Override
    protected RotationAngle[][] initSummonPoseRotations() {
        return new RotationAngle[][] {
                new RotationAngle[] {

                        // Cabeça (head)
                        RotationAngle.fromDegrees(head, 20, 0, 0),

                        // Perna Direita (rightLeg)
                        RotationAngle.fromDegrees(rightLeg, -9.04899, 27.75176, 3.58967),

                        // Perna Inferior Direita (rightLowerLeg)
                        RotationAngle.fromDegrees(rightLowerLeg, 10.05018, -2.28783, -1.41995),

                        // Perna Esquerda (leftLeg)
                        RotationAngle.fromDegrees(leftLeg, 4.83663, -30.52598, -5.52472),

                        // Perna Inferior Esquerda (leftLowerLeg)
                        RotationAngle.fromDegrees(leftLowerLeg, 2.5, 0.0, 0.0),

                        // Braço Direito (rightArm)
                        RotationAngle.fromDegrees(rightArm, -0.37572, -44.96077, 12.26971),

                        // Antebraço Direito (rightForeArm)
                        RotationAngle.fromDegrees(rightForeArm, 0.0, 80.0, 25.0),

                        // Braço Esquerdo (leftArm)
                        RotationAngle.fromDegrees(leftArm, -110.00473, 0.21782, -2.4905),

                        // Antebraço Esquerdo (leftForeArm)
                        RotationAngle.fromDegrees(leftForeArm, -52.4645, 1.37156, 4.70861),

                        // Torso
                        RotationAngle.fromDegrees(torso, 2.5, 0.0, 0.0),

                        // Stick (stick) - Atualização da rotação e posição
                        RotationAngle.fromDegrees(stick, 4.80848, 44.06251, 69.96147),

                        // Escudo (bone)
                        RotationAngle.fromDegrees(bone, 7.5, 0.0, 0.0),

                        // Bone2 (bone2)
                        RotationAngle.fromDegrees(bone2, -0.00097, -1.30363, -4.91487),

                        // Scarf Edge 2 (scarfEdge2)
                        RotationAngle.fromDegrees(scarfEdge2, 2.0, 0.0, 0.0),

                        // Bone3 (bone3)
                        RotationAngle.fromDegrees(bone3, 0.0, 0.0, 1.0),

                        // Bone4 (bone4)
                        RotationAngle.fromDegrees(bone4, -5.0, 0.0, 0.0),

                        // Skarf (skarf)
                        RotationAngle.fromDegrees(skarf, 0.0, 0.0, -0.5)

                }
        };
    }

    @Override
    protected ModelPose<WonderOfYouEntity> initIdlePose() {
        return new ModelPose<>(new RotationAngle[] {

                // Perna Direita (rightLeg)
                RotationAngle.fromDegrees(rightLeg, -9.04899, 27.75176, 3.58967),

                // Perna Inferior Direita (rightLowerLeg)
                RotationAngle.fromDegrees(rightLowerLeg, 10.05018, -2.28783, -1.41995),

                // Perna Esquerda (leftLeg)
                RotationAngle.fromDegrees(leftLeg, 4.83663, -30.52598, -5.52472),

                // Perna Inferior Esquerda (leftLowerLeg)
                RotationAngle.fromDegrees(leftLowerLeg, 2.5, 0.0, 0.0),

                // Braço Direito (rightArm)
                RotationAngle.fromDegrees(rightArm, -0.37572, -44.96077, 12.26971),

                // Antebraço Direito (rightForeArm)
                RotationAngle.fromDegrees(rightForeArm, 0.0, 80.0, 25.0),

                // Braço Esquerdo (leftArm)
                RotationAngle.fromDegrees(leftArm, 44.99527, 0.21782, -2.4905),

                // Antebraço Esquerdo (leftForeArm)
                RotationAngle.fromDegrees(leftForeArm, -1.50701, 4.76804, 72.43726),

                // Torso
                RotationAngle.fromDegrees(torso, 2.5, 0.0, 0.0),

                // Stick (stick) - Atualização da rotação e posição
                RotationAngle.fromDegrees(stick, 4.80848, 44.06251, 69.96147),

                // Escudo (bone)
                RotationAngle.fromDegrees(bone, 7.5, 0.0, 0.0),

                // Bone2 (bone2)
                RotationAngle.fromDegrees(bone2, -0.00097, -1.30363, -4.91487),

                // Scarf Edge 2 (scarfEdge2)
                RotationAngle.fromDegrees(scarfEdge2, 2.0, 0.0, 0.0),

                // Bone3 (bone3)
                RotationAngle.fromDegrees(bone3, 0.0, 0.0, 1.0),

                // Bone4 (bone4)
                RotationAngle.fromDegrees(bone4, -5.0, 0.0, 0.0),

                // Skarf (skarf)
                RotationAngle.fromDegrees(skarf, 0.0, 0.0, -0.5)
        });
    }

    @Override
    protected IModelPose<WonderOfYouEntity> initIdlePose2Loop() {
        return super.initIdlePose2Loop();
    }
}