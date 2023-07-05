package com.thanatos.magicalgems.Enchantments.Armor;

import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ResistanceEnchant extends Enchantment {

    public ResistanceEnchant(Enchantment.Rarity pRarity, EnchantmentType pCategory, EquipmentSlotType... pApplicableSlots) {
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
            Enchantment resistanceEnchant = EnchantementInit.RESISTANCE_ENCHANT.get();
            ItemStack pArmor = playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST);
            int pLevel = EnchantmentHelper.getEnchantmentLevel(resistanceEnchant,pArmor);
            if (playerIn.hasItemInSlot(EquipmentSlotType.CHEST) && EnchantmentHelper.getEnchantmentLevel(resistanceEnchant, pArmor) > 0) {
                playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1999999999, pLevel - 1));
            }
            else{
                playerIn.removePotionEffect(Effects.RESISTANCE);
            }
        }
    }
}

