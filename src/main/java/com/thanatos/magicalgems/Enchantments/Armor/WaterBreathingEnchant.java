package com.thanatos.magicalgems.Enchantments.Armor;

import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class WaterBreathingEnchant extends Enchantment {

    public WaterBreathingEnchant(Enchantment.Rarity pRarity, EnchantmentType pCategory, EquipmentSlotType... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }
    @Mod.EventBusSubscriber(modid = main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ArmorEquipped {
        @SubscribeEvent
        public static void addEffect(LivingEquipmentChangeEvent event) {
            LivingEntity playerIn = event.getEntityLiving();
            Enchantment waterBreathingEnchant = EnchantementInit.WATER_BREATHING_ENCHANT.get();
            ItemStack pArmor = playerIn.getItemStackFromSlot(EquipmentSlotType.HEAD);
            int pLevel = EnchantmentHelper.getEnchantmentLevel(waterBreathingEnchant,pArmor);
            if (playerIn.hasItemInSlot(EquipmentSlotType.HEAD) && EnchantmentHelper.getEnchantmentLevel(waterBreathingEnchant, pArmor) > 0) {
                playerIn.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 1999999999, pLevel - 1));
            }
            else{
                playerIn.removePotionEffect(Effects.WATER_BREATHING);
            }
        }
    }
}


