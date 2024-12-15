package com.filipegamer12br.rotp_wou.entity;

import javax.annotation.Nullable;
import com.filipegamer12br.rotp_wou.init.InitEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

public class CarProjectileEntity extends Entity {

    @Nullable
    public Entity owner;
    @Nullable
    public UUID ownerId;
    @Nullable
    private LivingEntity target; // O alvo da colisão do carro

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

        if (owner != null) {
            if (owner instanceof LivingEntity) {
                target = ((LivingEntity) owner).getLastHurtMob(); // Definir o alvo como o último mob atacado
            }

            // Se o alvo estiver vivo, mover o carro em direção a ele
            if (target != null && target.isAlive()) {
                Vector3d directionToTarget = target.position().subtract(this.position()).normalize().scale(0.5);
                this.setDeltaMovement(directionToTarget); // Movimento em direção ao alvo
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        }
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
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.hasUUID("Owner")) {
            ownerId = nbt.getUUID("Owner");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        if (ownerId != null) {
            nbt.putUUID("Owner", ownerId);
        }
    }

    // Definir a posição do carro para evitar explosão imediata
    @Override
    public void setPos(double x, double y, double z) {
        super.setPos(x, y, z); // Ajusta a posição do carro
    }
}