package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class TNTDrop extends StandEntityAction {

    public TNTDrop(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            // Realiza o ray tracing (linha de visão) para encontrar o alvo
            RayTraceResult target = standEntity.aimWithStandOrUser(100, task.getTarget()); // Alcança até 100 blocos

            // Verifica se o ray tracing acertou algo
            if (target != null && target.getType() == RayTraceResult.Type.ENTITY) {
                // Obtém a posição do alvo (onde o Stand está mirando)
                Vector3d pos = target.getLocation();
                if (pos != null) {
                    // Converte a posição para BlockPos (necessário para o TNT)
                    BlockPos targetBlockPos = new BlockPos(pos.x, pos.y, pos.z);

                    // Criação do TNT normal
                    TNTEntity tnt = new TNTEntity(world, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), null);
                    world.addFreshEntity(tnt); // Adiciona o TNT no mundo

                    // Define o fuse para 10 ticks (0.5 segundos)
                    tnt.setFuse(10); // 10 ticks = 0.5 segundos

                    // Agendar a explosão após 0.5 segundos
                    world.getServer().execute(() -> {
                        // Cria a explosão manualmente, sem afetar o terreno
                        Explosion explosion = new Explosion(world, tnt, null, null, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), 0.0F, false, Explosion.Mode.NONE);
                        explosion.explode(); // Detona a explosão

                        System.out.println("TNT lançado no local da mira do Stand com 0.5 segundos de atraso!");
                    });
                }
            }
        }
    }



    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
        // Retorna false para não permitir alternar a habilidade manualmente
        return false;
    }

    // Método auxiliar para encontrar o alvo mais próximo
    private LivingEntity getTargetEntity(WonderOfYouEntity standEntity) {
        // Obtém o vetor de direção para onde o Stand está olhando
        Vector3d lookDirection = standEntity.getLookAngle();

        // Obtém a posição atual do Stand
        Vector3d standPosition = standEntity.position();

        // Define o alcance máximo de detecção (por exemplo, 10 blocos)
        double maxDistance = 10.0;

        // Calcula a posição final da linha de visão
        Vector3d lookEndPosition = standPosition.add(lookDirection.x * maxDistance, lookDirection.y * maxDistance, lookDirection.z * maxDistance);

        // Encontra as entidades dentro de um "raio" ao longo da linha de visão
        List<LivingEntity> entitiesInSight = standEntity.level.getEntitiesOfClass(LivingEntity.class,
                new AxisAlignedBB(standPosition, lookEndPosition).inflate(1.0D)); // Um pequeno "raio" de 1 bloco para garantir que as entidades próximas sejam detectadas

        for (LivingEntity entity : entitiesInSight) {
            if (entity != standEntity) { // Garante que o Stand não seja o alvo
                return entity; // Retorna a primeira entidade encontrada
            }
        }

        return null; // Retorna null caso não haja entidades na linha de visão
    }
}
