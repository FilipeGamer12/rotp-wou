package com.rotpaddon.exampleaddon.action;

import com.rotpaddon.exampleaddon.entity.ExampleStandEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CalamityAbility {

    // Evento acionado quando uma entidade sofre dano
    @SubscribeEvent
    public static void onEntityAttacks(LivingAttackEvent event) {
        if (event.getEntity())
        // Obtém a entidade que está sendo atacada (target)
        LivingEntity target = event.getEntityLiving();
        // Obtém a entidade que está causando o dano (attacker)
        Entity attacker = event.getSource().getEntity();

        // Verifica se o alvo do ataque é o Stand ou o usuário do Stand (target)
        if (target instanceof ExampleStandEntity || target instanceof PlayerEntity || isHostileEntity(target)) {
            // Verifica se existe um atacante e aplica a "calamidade" a ele
            if (attacker instanceof LivingEntity) {
                triggerCalamity((LivingEntity) attacker);  // Aplica a calamidade no atacante
            }
        }
    }

    // Função para verificar se a entidade é hostil
    private static boolean isHostileEntity(Entity entity) {
        return entity instanceof MonsterEntity;  // Verifica se o mob é hostil
    }

    // Função para disparar a calamidade (aplicar dano e efeitos negativos ao atacante)
    private static void triggerCalamity(LivingEntity attacker) {
        if (attacker == null) return;

        // Calamidade: Causar dano aleatório ao atacante
        float randomDamage = MathHelper.nextFloat(attacker.getRandom(), 1.0F, 10.0F);  // Dano aleatório entre 1 e 10
        attacker.hurt(DamageSource.MAGIC, randomDamage);  // Aplica dano ao atacante (não ao alvo)

        // Aplicar um efeito de veneno ao atacante (calamidade)
        attacker.addEffect(new EffectInstance(Effects.POISON, 100, 1));  // Aplica veneno por 5 segundos ao atacante
    }
}