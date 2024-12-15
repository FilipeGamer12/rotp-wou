package com.filipegamer12br.rotp_wou.entity;

import javax.annotation.Nullable;

import com.filipegamer12br.rotp_wou.init.InitEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

import static net.minecraft.world.Explosion.Mode.BREAK;

public class CarProjectileEntity extends Entity {

    public int ticksBeforeExplosion = 100; // Tempo até a explosão (100 ticks).
    @Nullable
    public Entity owner;
    @Nullable
    public UUID ownerId;

    public CarProjectileEntity(World world) {
        this(InitEntities.CAR_PROJECTILE.get(), world);
    }

    public CarProjectileEntity(EntityType<CarProjectileEntity> type, World world) {
        super(type, world);
    }

    public void setOwner(Entity entity) {
        this.owner = entity;
        this.ownerId = entity != null ? entity.getUUID() : null;
    }

    @Override
    public void tick() {
        super.tick();

        // Movimento básico da entidade
        move(MoverType.SELF, getDeltaMovement());

        // Explosão baseada em tempo
        if (ticksBeforeExplosion > 0) {
            ticksBeforeExplosion--;
        } else {
            explode(); // Explodir quando o tempo de contagem chega a zero
            remove();  // Remover a entidade após a explosão
        }

        // Verificar se o dono se afastou muito e explodir
        if (owner != null && distanceToSqr(owner) > 100) {
            explode();
            remove();
        }

        // Aplicar dano a entidades próximas quando o tempo da explosão estiver baixo
        if (ticksBeforeExplosion <= 20) {
            applyDamageToNearbyEntities();
        }
    }

    // Método para aplicar dano a entidades próximas
    private void applyDamageToNearbyEntities() {
        DamageSource damageSource = DamageSource.MAGIC; // A fonte de dano pode ser personalizada
        float damage = 5.0F; // O dano pode ser ajustado

        // Obtendo as entidades próximas
        AxisAlignedBB aabb = getBoundingBox().inflate(6.0D); // O alcance de efeito pode ser ajustado
        level.getEntitiesOfClass(LivingEntity.class, aabb, entity -> entity != owner).forEach(entity -> {
            if (!entity.isInvulnerableTo(damageSource)) {
                entity.hurt(damageSource, damage); // Aplicar o dano
            }
        });
    }

    // Método para realizar a explosão
    private void explode() {
        level.explode(this, getX(), getY(), getZ(), 4.0F, false, BREAK); // Realizar a explosão
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return entity != owner; // Impede colisão com o dono
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void defineSynchedData() {
        // Não é mais necessário gerenciar EXPLOSION_TIME via DataManager
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        ticksBeforeExplosion = nbt.getInt("ExplosionTime");
        if (nbt.hasUUID("Owner")) {
            ownerId = nbt.getUUID("Owner");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putInt("ExplosionTime", ticksBeforeExplosion);
        if (ownerId != null) {
            nbt.putUUID("Owner", ownerId);
        }
    }
}