package com.rotpaddon.exampleaddon.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.entity.damaging.projectile.ModdedProjectileEntity;
import com.github.standobyte.jojo.util.general.MathUtil;
import com.rotpaddon.exampleaddon.init.InitEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class ExamplePickaxeEntity extends ModdedProjectileEntity {

    public ExamplePickaxeEntity(EntityType<ExamplePickaxeEntity> type, World world) {
        super(type, world);
    }
    
    public ExamplePickaxeEntity(LivingEntity shooter, World world) {
        super(InitEntities.EXAMPLE_PICKAXE.get(), shooter, world);
    }

    
    
    @Override
    protected float getBaseDamage() {
        return 6;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 100;
    }

    @Override
    public boolean standDamage() {
        return true;
    }

    @Override
    public int ticksLifespan() {
        return Integer.MAX_VALUE;
    }
    
    
    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("Durability", durability);
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        durability = nbt.getFloat("Durability");
    }
    
    
    // Below are some extra method overridings I wrote to make the projectile a bit more spicy.
    // In some cases it's a copy-paste of Minecraft's code with little changes
    // (when the functionality is similar to something that already exists in the base game),
    
    
    
    
    // Instead of usual way of breaking a single block if it is not durable enough and destroying the projectile,
    // the pickaxe has a durability counter for breaking blocks.
    // Depending on the hardness of the block, and if it is a stone block or not, durability gets reduced.
    private float durability = 100;
    private boolean preventEntityBreaking;
    @Override
    protected boolean destroyBlock(BlockRayTraceResult blockRayTraceResult) {
        BlockPos blockPos = blockRayTraceResult.getBlockPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.getShape(level, blockPos).isEmpty()) {
            preventEntityBreaking = true;
            return false;
        }
        
        float hardness = blockState.getDestroySpeed(level, blockPos);
        boolean fittingBlock = !blockState.requiresCorrectToolForDrops()
                || blockState.getHarvestTool() == ToolType.PICKAXE && blockState.getHarvestLevel() <= 3;
        if (fittingBlock) {
            hardness *= 0.3F;
        }
        
        if (hardness < durability && super.destroyBlock(blockRayTraceResult)) {
            durability -= hardness;
            preventEntityBreaking = true;
            return true;
        }
        
        return false;
    }
    
    // If the pickaxe still has durability left, do not break it yet
    // When breaking the projectile, also summon particles and play the sound of a tool breaking.
    @Override
    protected void breakProjectile(TargetType targetType, RayTraceResult hitTarget) {
        if (!level.isClientSide()) {
            if (preventEntityBreaking) {
                preventEntityBreaking = false;
                return;
            }
            
            if (isAlive()) {
                playSound(SoundEvents.ITEM_BREAK, 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F);
                
                ServerWorld serverWorld = ((ServerWorld) level);
                for (int i = 0; i < 20; ++i) {
                    ItemStack particleItem = new ItemStack(Items.DIAMOND_PICKAXE);
                    
                    Vector3d velocity = new Vector3d(
                            (random.nextDouble() - 0.5) * 0.1, 
                            Math.random() * 0.1 + 0.1, 
                            0)
                            .xRot(-xRot * MathUtil.DEG_TO_RAD)
                            .yRot(-yRot * MathUtil.DEG_TO_RAD);
                    
                    Vector3d pos = position().add(new Vector3d(
                            random.nextDouble() - 0.5, 
                            random.nextDouble(), 
                            random.nextDouble() - 0.5)
                            );
                    serverWorld.sendParticles(new ItemParticleData(ParticleTypes.ITEM, particleItem), 
                            pos.x, pos.y, pos.z, 1, velocity.x, velocity.y + 0.05, velocity.z, 0.0);
                }
            }
            
        }
        super.breakProjectile(targetType, hitTarget);
    }
    
    // Return all the blocks in a small area around the pickaxe to make the tunnels it digs wider.
    @Override
    protected RayTraceResult[] rayTrace() {
        RayTraceResult[] firstResult = super.rayTrace();
        if (level.isClientSide() || firstResult.length > 0 && firstResult[0].getType() == RayTraceResult.Type.ENTITY) {
            return firstResult;
        }
        
        AxisAlignedBB blocksHitbox = getBoundingBox().expandTowards(getDeltaMovement());
        List<BlockPos> blocksToBreak = new ArrayList<>();
        int xMin = MathHelper.floor(blocksHitbox.minX);
        int yMin = MathHelper.floor(blocksHitbox.minY);
        int zMin = MathHelper.floor(blocksHitbox.minZ);
        int xMax = MathHelper.ceil(blocksHitbox.maxX);
        int yMax = MathHelper.ceil(blocksHitbox.maxY);
        int zMax = MathHelper.ceil(blocksHitbox.maxZ);
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockState blockState = level.getBlockState(blockPos);
                    if (!blockState.isAir(level, blockPos)
                            && (FluidMode.NONE.canPick(blockState.getFluidState())
                                    || !blockState.getShape(level, blockPos).isEmpty())) {
                        blocksToBreak.add(blockPos);
                    }
                }
            }
        }
        
        double posX = getX();
        double posY = getY(0.5);
        double posZ = getZ();
        BlockRayTraceResult[] blocks = blocksToBreak.stream()
                .sorted(Comparator.comparingDouble(blockPos -> blockPos.distSqr(posX, posY, posZ, true)))
                .map(blockPos -> new BlockRayTraceResult(
                        Vector3d.atCenterOf(blockPos), 
                        Direction.getNearest(
                                blockPos.getX() + 0.5 - posX, 
                                blockPos.getY() + 0.5 - posY, 
                                blockPos.getZ() + 0.5 - posZ), blockPos, false))
                .toArray(BlockRayTraceResult[]::new);
        return blocks;
    }
}
