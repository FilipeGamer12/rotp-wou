package com.filipegamer12br.rotp_wou.init;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;
import com.filipegamer12br.rotp_wou.WonderOfYouAddon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, WonderOfYouAddon.MOD_ID);
    
    public static final RegistryObject<SoundEvent> SUMMON_VOICELINE = SOUNDS.register("summon_voiceline",
            () -> new SoundEvent(new ResourceLocation(WonderOfYouAddon.MOD_ID, "summon_voiceline")));

    public static final Supplier<SoundEvent> SUMMON_SOUND = ModSounds.STAND_SUMMON_DEFAULT;
    
    public static final Supplier<SoundEvent> UNSUMMON_SOUND = ModSounds.STAND_UNSUMMON_DEFAULT;
    
    public static final Supplier<SoundEvent> PUNCH_LIGHT = ModSounds.STAND_PUNCH_LIGHT;
    
    public static final Supplier<SoundEvent> PUNCH_HEAVY = ModSounds.STAND_PUNCH_HEAVY;
    
    public static final Supplier<SoundEvent> BARRAGE = ModSounds.STAND_PUNCH_LIGHT;

    public static final RegistryObject<SoundEvent> CALAMITY1 = SOUNDS.register("calamity1",
            () -> new SoundEvent(new ResourceLocation(WonderOfYouAddon.MOD_ID, "calamity1"))); // summon

    public static final RegistryObject<SoundEvent> CALAMITY2 = SOUNDS.register("calamity2",
            () -> new SoundEvent(new ResourceLocation(WonderOfYouAddon.MOD_ID, "calamity2"))); // calamity passive

    public static final RegistryObject<SoundEvent> CALAMITY3 = SOUNDS.register("calamity3",
            () -> new SoundEvent(new ResourceLocation(WonderOfYouAddon.MOD_ID, "calamity3"))); // calamity active

    public static final RegistryObject<SoundEvent> CALAMITY4 = SOUNDS.register("calamity4",
            () -> new SoundEvent(new ResourceLocation(WonderOfYouAddon.MOD_ID, "calamity4"))); // lightning and explosion

    public static final OstSoundList WOU_OST = new OstSoundList(
            new ResourceLocation(WonderOfYouAddon.MOD_ID, "wou_ost"), SOUNDS);
}
