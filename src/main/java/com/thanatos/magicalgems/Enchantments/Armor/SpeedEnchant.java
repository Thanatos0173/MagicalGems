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
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class SpeedEnchant extends Enchantment {

    public SpeedEnchant(Enchantment.Rarity pRarity, EnchantmentType pCategory, EquipmentSlotType... pApplicableSlots) {
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
            Enchantment speedEnchant = EnchantementInit.SPEED_ENCHANT.get();
            ItemStack pArmor = playerIn.getItemStackFromSlot(EquipmentSlotType.LEGS);
            int pLevel = EnchantmentHelper.getEnchantmentLevel(speedEnchant,pArmor);
            if (playerIn.hasItemInSlot(EquipmentSlotType.LEGS) && EnchantmentHelper.getEnchantmentLevel(speedEnchant, pArmor) > 0) {
                playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 1999999999, pLevel - 1));
            }
            else{
                playerIn.removePotionEffect(Effects.SPEED);
            }
        }
    }
}


