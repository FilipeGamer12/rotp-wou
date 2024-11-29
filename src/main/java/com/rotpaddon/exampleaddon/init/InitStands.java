package com.rotpaddon.exampleaddon.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.rotpaddon.exampleaddon.AddonMain;
import com.rotpaddon.exampleaddon.action.ExampleStandThrowPickaxe;
import com.rotpaddon.exampleaddon.entity.ExampleStandEntity;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), AddonMain.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), AddonMain.MOD_ID);
    
 // ======================================== Example Stand ========================================
    
    
    // Create all the abilities here...
    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_PUNCH = ACTIONS.register("example_stand_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.EXAMPLE_STAND_PUNCH_LIGHT)));
    
    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_BARRAGE = ACTIONS.register("example_stand_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.EXAMPLE_STAND_PUNCH_BARRAGE)));

    public static final RegistryObject<StandEntityHeavyAttack> EXAMPLE_STAND_FINISHER_PUNCH = ACTIONS.register("example_stand_finisher_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder() // TODO finisher ability
                    .punchSound(InitSounds.EXAMPLE_STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityHeavyAttack> EXAMPLE_STAND_HEAVY_PUNCH = ACTIONS.register("example_stand_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(EXAMPLE_STAND_PUNCH).shiftVariationOf(EXAMPLE_STAND_BARRAGE)
                    .setFinisherVariation(EXAMPLE_STAND_FINISHER_PUNCH)
                    .punchSound(InitSounds.EXAMPLE_STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_BLOCK = ACTIONS.register("example_stand_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_THROW_PICKAXE = ACTIONS.register("example_stand_throw_pickaxe", 
            () -> new ExampleStandThrowPickaxe(new StandEntityAction.Builder()
                    .holdToFire(20, true)
                    .standSound(InitSounds.EXAMPLE_STAND_THROW_PICKAXE)
                    .staminaCost(75)
                    .partsRequired(StandPart.ARMS)));
    
    

    // ...then create the Stand type instance. Moves, stats, entity sizes, and a few other things are determined here.
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<ExampleStandEntity>> STAND_EXAMPLE_STAND = 
            new EntityStandRegistryObject<>("example_stand", 
                    STANDS, 
                    () -> new EntityStandType.Builder<StandStats>()
                    .color(0x00AFAF)
                    .storyPartName(ModStandsInit.PART_8_NAME)
                    .leftClickHotbar(
                            EXAMPLE_STAND_PUNCH.get(),
                            EXAMPLE_STAND_BARRAGE.get()
                            )
                    .rightClickHotbar(
                            EXAMPLE_STAND_BLOCK.get(),
                            EXAMPLE_STAND_THROW_PICKAXE.get()
                            )
                    .defaultStats(StandStats.class, new StandStats.Builder()
                            .tier(6)
                            .power(20)
                            .speed(20)
                            .range(50, 100)
                            .durability(20)
                            .precision(20)
                            .build())
                    .addSummonShout(InitSounds.EXAMPLE_STAND_SUMMON_VOICELINE)
                    .addOst(InitSounds.EXAMPLE_STAND_OST)
                    .build(),
                    
                    InitEntities.ENTITIES,
                    () -> new StandEntityType<ExampleStandEntity>(ExampleStandEntity::new, 0.7F, 2.1F)
                    .summonSound(InitSounds.EXAMPLE_STAND_SUMMON_SOUND)
                    .unsummonSound(InitSounds.EXAMPLE_STAND_UNSUMMON_SOUND))
            .withDefaultStandAttributes();
    

    
    // ======================================== ??? ========================================
    
    
    
}
