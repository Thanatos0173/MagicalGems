package com.thanatos.magicalgems.utils;

import com.thanatos.magicalgems.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


public class ModItemGroups {

    public static final ItemGroup MAGICALGEMS_TAB = new ItemGroup("magicalgems") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.gem.get());
        }
    };

}


