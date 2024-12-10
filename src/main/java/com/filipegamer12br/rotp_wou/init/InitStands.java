package com.filipegamer12br.rotp_wou.init;

import com.filipegamer12br.rotp_wou.WonderOfYouAddon;
import com.filipegamer12br.rotp_wou.action.CalamityActive;
import com.filipegamer12br.rotp_wou.action.CalamityPassive;
import com.filipegamer12br.rotp_wou.action.ExplodeVariations;
import com.filipegamer12br.rotp_wou.action.LightningStrike;
import com.filipegamer12br.rotp_wou.entity.WonderOfYouEntity;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.github.standobyte.jojo.util.mod.StoryPart;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;


public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), WonderOfYouAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), WonderOfYouAddon.MOD_ID);

    // ======================================== Wonder of You ========================================

    public static final RegistryObject<StandEntityAction> PUNCH = ACTIONS.register("punch",
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.PUNCH_LIGHT)));

    public static final RegistryObject<StandEntityAction> BARRAGE = ACTIONS.register("barrage",
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.BARRAGE)));

    public static final RegistryObject<StandEntityHeavyAttack> FINISHER_PUNCH = ACTIONS.register("finisher_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityHeavyAttack> HEAVY_PUNCH = ACTIONS.register("heavy_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(PUNCH).shiftVariationOf(BARRAGE)
                    .setFinisherVariation(FINISHER_PUNCH)
                    .punchSound(InitSounds.PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityAction> BLOCK = ACTIONS.register("block",
            StandEntityBlock::new);

    public static final RegistryObject<CalamityPassive> CALAMITY_PASSIVE = ACTIONS.register("calamity_passive",
            () -> new CalamityPassive(new CalamityPassive.Builder()
                    .holdToFire(20, false)
                    .staminaCost(75)
                    //.standSound(StandEntityAction.Phase.WINDUP , InitSounds.CALAMITY)
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<CalamityActive> CALAMITY_ACTIVE = ACTIONS.register("calamity_active",
            () -> new CalamityActive(new CalamityActive.Builder()
                    .holdToFire(20, false)
                    .staminaCost(200)
                    //.standSound(StandEntityAction.Phase.WINDUP , InitSounds.CALAMITY)
                    .partsRequired(StandPart.MAIN_BODY)));

    public static final RegistryObject<LightningStrike> LIGHTNING_STRIKE = ACTIONS.register("lightning_strike",
            () -> new LightningStrike(new LightningStrike.Builder()
                    .holdToFire(30, false) // Segurar por 5 ticks
                    .staminaCost(50) // Consome 50 de estamina
                    //.standSound(InitSounds.CALAMITY)
                    .partsRequired(StandPart.ARMS))); // Parte necessária para executar a habilidade

    public static final RegistryObject<ExplodeVariations> TNT_DROP = ACTIONS.register("explosion",
            () -> new ExplodeVariations(new ExplodeVariations.Builder()
                    .shiftVariationOf(LIGHTNING_STRIKE)
                    .holdToFire(30, false)
                    .staminaCost(50)
                    .partsRequired(StandPart.MAIN_BODY)));


    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<WonderOfYouEntity>> WONDER_OF_YOU =
            new EntityStandRegistryObject<>("wonder_of_you",
                    STANDS,
                    () -> new EntityStandType.Builder<>()
                            .color(0x614208)
                            .storyPartName(StoryPart.JOJOLION.getName())
                            .leftClickHotbar(
                                    PUNCH.get(),
                                    BARRAGE.get(),
                                    LIGHTNING_STRIKE.get()
                            )
                            .rightClickHotbar(
                                    BLOCK.get(),
                                    CALAMITY_PASSIVE.get(),
                                    CALAMITY_ACTIVE.get()
                            )
                            .defaultStats(StandStats.class, new StandStats.Builder()
                                    .power(12)
                                    .speed(9)
                                    .range(10000, 10000)
                                    .durability(9)
                                    .precision(14)
                                    .build())
                            .addSummonShout(InitSounds.CALAMITY)
                            .addOst(InitSounds.WOU_OST)
                            .build(),

                    InitEntities.ENTITIES,
                    () -> new StandEntityType<>(WonderOfYouEntity::new, 0.7F, 2.1F)
                            .summonSound(InitSounds.SUMMON_SOUND)
                            .unsummonSound(InitSounds.UNSUMMON_SOUND))
                    .withDefaultStandAttributes();
}
