package com.thanatos.magicalgems.init;

import com.thanatos.magicalgems.main;
import com.thanatos.magicalgems.utils.ModItemGroups;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems{



    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    public static final RegistryObject<Item> gem = ITEMS.register("gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));


    public static final RegistryObject<Item> upgraded_blaze_powder = ITEMS.register("upgraded_blaze_powder",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));

    public static final RegistryObject<Item> nether_star_shard = ITEMS.register("nether_star_shard",
            () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> dolphins_scute = ITEMS.register("dolphins_scute",
            () -> new Item(new Item.Properties().group(ItemGroup.MISC)));

    //On Sword
    public static final RegistryObject<Item> poison_gem = ITEMS.register("poison_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> slowness_gem = ITEMS.register("slowness_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    //On Armor
    public static final RegistryObject<Item> strength_gem = ITEMS.register("strength_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> night_vision_gem = ITEMS.register("night_vision_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> haste_gem = ITEMS.register("haste_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> fire_resistance_gem = ITEMS.register("fire_resistance_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> health_boost_gem = ITEMS.register("health_boost_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> regeneration_gem = ITEMS.register("regeneration_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> water_breathing_gem = ITEMS.register("water_breathing_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> speed_gem = ITEMS.register("speed_gem"
            ,() -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> jump_boost_gem = ITEMS.register("jump_boost_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> resistance_gem = ITEMS.register("resistance_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> slow_falling_gem = ITEMS.register("slow_falling_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> dolphins_grace_gem = ITEMS.register("dolphins_grace_gem",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
    public static final RegistryObject<Item> haste_essence = ITEMS.register("haste_essence",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB).maxStackSize(1)));
    public static final RegistryObject<Item> health_boost_essence = ITEMS.register("health_boost_essence",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB).maxStackSize(1)));
    public static final RegistryObject<Item> dolphins_grace_essence = ITEMS.register("dolphins_grace_essence",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB).maxStackSize(1)));

    public static final RegistryObject<Item> empty_vial  = ITEMS.register("empty_vial",
            () -> new Item(new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB).maxStackSize(1)));

/*
    public static final RegistryObject<Item> life_steal_gem = ITEMS.register("life_steal_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> decapitation_gem = ITEMS.register("decapitation_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> xp_plus_gem = ITEMS.register("xp_plus_gem"
            ,() -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> lumberjack_gem = ITEMS.register("lumberjack_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> fruit_gem = ITEMS.register("fruit_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> excavation_gem = ITEMS.register("excavation_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));
    public static final RegistryObject<Item> xp_bonus_gem = ITEMS.register("xp_bonus_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));








    public static final RegistryObject<Item> armored_elytra_gem = ITEMS.register("armored_elytra_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));

    public static final RegistryObject<Item> platform_nine_and_three_quarters_gem = ITEMS.register("platform_nine_and_three_quarters_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));

    public static final RegistryObject<Item> spider_gem = ITEMS.register("spider_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));



    public static final RegistryObject<Item> NoFall_gem = ITEMS.register("nofall_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));


    public static final RegistryObject<Item> lava_walker_gem = ITEMS.register("lava_walker_gem",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.MAGICGEM_TAB)));

*/
}


