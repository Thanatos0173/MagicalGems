package com.thanatos.magicalgems.Enchantments;




import com.thanatos.magicalgems.Enchantments.Armor.*;
import com.thanatos.magicalgems.Enchantments.Sword.PoisonEnchant;
import com.thanatos.magicalgems.Enchantments.Sword.SlownessEnchant;
import com.thanatos.magicalgems.main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class EnchantementInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, main.MODID);

    public static RegistryObject<Enchantment> POISON_ENCHANT =
            ENCHANTMENTS.register("poison_enchant", () -> new PoisonEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));
    public static RegistryObject<Enchantment> SLOWNESS_ENCHANT =
            ENCHANTMENTS.register("slowness_enchant", () -> new SlownessEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));
    public static RegistryObject<Enchantment> STRENGHT_ENCHANT =
            ENCHANTMENTS.register("strength_enchant", () -> new StrengthEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> NIGHT_VISION_ENCHANT =
            ENCHANTMENTS.register("night_vision_enchant", () -> new NightVisionEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD));
    public static RegistryObject<Enchantment> HASTE_ENCHANT =
            ENCHANTMENTS.register("haste_enchant", () -> new HasteEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> RESISTANCE_ENCHANT =
            ENCHANTMENTS.register("resistance_enchant", () -> new ResistanceEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> HEALTH_BOOST_ENCHANT =
            ENCHANTMENTS.register("health_boost_enchant", () -> new HealthBoostEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> FIRE_RESISTANCE_ENCHANT =
            ENCHANTMENTS.register("fire_resistance_enchant", () -> new FireResistanceEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> JUMP_BOOST_ENCHANT =
            ENCHANTMENTS.register("jump_boost_enchant", () -> new JumpBoostEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_LEGS, EquipmentSlotType.LEGS));
    public static RegistryObject<Enchantment> SLOW_FALLING_ENCHANT =
            ENCHANTMENTS.register("slow_falling_enchant", () -> new SlowFallingEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET));
    public static RegistryObject<Enchantment> SPEED_ENCHANT =
            ENCHANTMENTS.register("speed_enchant", () -> new SpeedEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_LEGS, EquipmentSlotType.LEGS));
    public static RegistryObject<Enchantment> WATER_BREATHING_ENCHANT =
            ENCHANTMENTS.register("water_breathing_enchant", () -> new WaterBreathingEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD));
    public static RegistryObject<Enchantment> REGENERATION_ENCHANT =
            ENCHANTMENTS.register("regeneration_enchant", () -> new RegenerationEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST));
    public static RegistryObject<Enchantment> DOLPHINS_GRACE_ENCHANT =
            ENCHANTMENTS.register("dolphins_grace_enchant", () -> new DolphinsGraceEnchant(Enchantment.Rarity.UNCOMMON,
                    EnchantmentType.ARMOR_LEGS, EquipmentSlotType.LEGS));
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
    public static boolean hasEffect(LivingEntity entity, Effect potionEffect){
        return !Objects.equals(entity.getActivePotionEffect(Effects.HEALTH_BOOST), null);
    }
}
