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

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands extends StandEntityAction {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), AddonMain.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), AddonMain.MOD_ID);

    // ======================================== Example Stand ========================================

    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_PUNCH = ACTIONS.register("example_stand_punch",
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.EXAMPLE_STAND_PUNCH_LIGHT)));

    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_BARRAGE = ACTIONS.register("example_stand_barrage",
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.EXAMPLE_STAND_PUNCH_BARRAGE)));

    public static final RegistryObject<StandEntityHeavyAttack> EXAMPLE_STAND_FINISHER_PUNCH = ACTIONS.register("example_stand_finisher_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.EXAMPLE_STAND_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityHeavyAttack> EXAMPLE_STAND_HEAVY_PUNCH = ACTIONS.register("example_stand_heavy_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(EXAMPLE_STAND_PUNCH)
                    .shiftVariationOf(EXAMPLE_STAND_BARRAGE)
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

    // Calamidade Ability - Ação ativada/desativada ao segurar o botão
    public static final RegistryObject<StandEntityAction> EXAMPLE_STAND_CALAMITY = ACTIONS.register("example_stand_calamity",
            () -> new StandEntityAction(new StandEntityAction.Builder()
                    .holdToFire(25, false)  // Define que a habilidade será ativada/desativada ao pressionar o botão
                    .standSound(InitSounds.EXAMPLE_STAND_CALAMITY)
                    .staminaCost(50)
                    .partsRequired(StandPart.ARMS)) {

                // Flag de controle de ativação/desativação da calamidade
                private boolean calamityActive = false;  // Estado da habilidade


                public void onActionStart(LivingEntity standUser) {
                    // Alterna o estado da habilidade a cada pressionamento
                    calamityActive = !calamityActive;  // Alterna entre ativado/desativado

                    if (calamityActive) {
                        System.out.println("Calamidade Ativada!");

                        // Envia mensagem no chat para o jogador quando a habilidade é ativada
                        if (standUser instanceof PlayerEntity) {
                            ((PlayerEntity) standUser).sendMessage(new StringTextComponent("CALAMIDADE!"), standUser.getUUID());
                        }

                    } else {
                        System.out.println("Calamidade Desativada!");

                        // Envia mensagem no chat para o jogador quando a habilidade é desativada
                        if (standUser instanceof PlayerEntity) {
                            ((PlayerEntity) standUser).sendMessage(new StringTextComponent("CALAMIDADE DESATIVADA!"), standUser.getUUID());
                        }
                    }
                }


                public void onHit(LivingEntity attacker, LivingEntity target) {
                    if (calamityActive) {
                        System.out.println("Habilidade de Calamidade ativada no atacante!");
                        triggerCalamity(attacker);
                    }
                }

                // Função para disparar a calamidade (aplicar dano e efeitos negativos ao atacante)
                private void triggerCalamity(LivingEntity attacker) {
                    if (attacker == null) return;

                    // Calamidade: Causar dano aleatório ao atacante
                    float randomDamage = MathHelper.nextFloat(attacker.getRandom(), 1.0F, 10.0F);  // Dano aleatório entre 1 e 10
                    attacker.hurt(DamageSource.MAGIC, randomDamage);  // Aplica dano ao atacante (não ao alvo)

                    // Aplicar um efeito de veneno ao atacante (calamidade)
                    attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1));  // Aplica veneno por 5 segundos ao atacante

                    System.out.println("Dano e veneno aplicados ao atacante!");
                }
            });




    // ======================================== Stand Entity ========================================

    // Exemplo de Stand tipo ExampleStand
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
                                    EXAMPLE_STAND_THROW_PICKAXE.get(),
                                    EXAMPLE_STAND_CALAMITY.get()  // Adiciona a ação de calamidade no hotbar
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

    public InitStands(AbstractBuilder<?> builder) {
        super(builder);
    }

    // ======================================== Outros ========================================
    // Aqui você pode continuar com o resto da lógica para os outros Stands ou funcionalidades.

}
