package com.filipegamer12br.rotp_wou.action;

import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.filipegamer12br.rotp_wou.init.InitSounds;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ExplodeVariations extends StandEntityAction {
    private String nameOfTarget;

    public ExplodeVariations(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {

            if (world.getServer() == null) // thx weever
                return;

            // Realiza o ray tracing (linha de visão) para encontrar o alvo
            RayTraceResult target = standEntity.aimWithStandOrUser(100, task.getTarget()); // Alcança até 100 blocos

            // Verifica se o ray tracing acertou algo
            if (target != null && target.getType() == RayTraceResult.Type.ENTITY) {
                // Obtém a posição do alvo (onde o Stand está mirando)
                Vector3d pos = target.getLocation();
                if (pos != null) {
                    standEntity.playSound(InitSounds.CALAMITY4.get(), 1F, 1);
                    // Verifica o tipo de explosão a ser gerada
                    Random random = new Random();
                    switch (random.nextInt(3)) {
                        case 0:
                            // TNT normal
                            BlockPos targetBlockPos = new BlockPos(pos.x, pos.y, pos.z);
                            TNTEntity tnt = new TNTEntity(world, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), null);
                            world.addFreshEntity(tnt); // Adiciona o TNT no mundo
                            tnt.setFuse(10); // Define o fuse para 10 ticks (0.5 segundos)

                            // Agendar a explosão após 0.5 segundos
                            world.getServer().execute(() -> {
                                // Cria a explosão manualmente, sem afetar o terreno
                                Explosion explosion = new Explosion(world, tnt, null, null, targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ(), 0.0F, false, Explosion.Mode.NONE);
                                explosion.explode(); // Detona a explosão
                            });
                            break;

                        case 1:
                            // Explosão padrão do mundo
                            world.explode(standEntity, pos.x, pos.y, pos.z, 2.0f, false, Explosion.Mode.BREAK);
                            break;

                        case 2:
                            // Criar um Creeper e explodir
                            CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
                            creeper.setPos(pos.x, pos.y, pos.z);
                            world.addFreshEntity(creeper);
                            creeper.ignite(); // Aciona a explosão do Creeper
                            break;
                    }
                }
            }
        }
    }

    @Override
    public boolean greenSelection(IStandPower power, ActionConditionResult conditionCheck) {
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