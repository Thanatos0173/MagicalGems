package com.thanatos.magicalgems.Enchantments.Sword;

import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
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

public class PoisonEnchant extends Enchantment {

    public PoisonEnchant(Enchantment.Rarity pRarity, EnchantmentType pCategory, EquipmentSlotType... pApplicableSlots) {
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

    @Override
    public void onEntityDamaged(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if(pTarget instanceof LivingEntity){
            ((LivingEntity) pTarget).addPotionEffect(new EffectInstance(Effects.POISON, 20*pLevel, pLevel - 1));
        }
        super.onEntityDamaged(pAttacker, pTarget, pLevel);
    }
}


