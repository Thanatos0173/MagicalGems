package com.thanatos.magicalgems.data.recipes;

import com.thanatos.magicalgems.data.recipes.alchemy_table.AlchemyTableRecipe;
import com.thanatos.magicalgems.data.recipes.distillery.DistilleryRecipe;
import com.thanatos.magicalgems.main;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;



public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, main.MODID);

    public static final RegistryObject<DistilleryRecipe.Serializer> DISTILLERY_SERIALIZER
            = RECIPE_SERIALIZER.register("distillery", DistilleryRecipe.Serializer::new);

    public static IRecipeType<DistilleryRecipe> DISTILLERY_RECIPE
            = new DistilleryRecipe.DistilleryType();

    public static final RegistryObject<AlchemyTableRecipe.Serializer> ALCHEMY_TABLE_SERIALIZER
            = RECIPE_SERIALIZER.register("alchemy_table", AlchemyTableRecipe.Serializer::new);

    public static IRecipeType<AlchemyTableRecipe> ALCHEMY_TABLE_RECIPE
            = new AlchemyTableRecipe.AlchemyTableType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, DistilleryRecipe.TYPE_ID, DISTILLERY_RECIPE);
        Registry.register(Registry.RECIPE_TYPE, AlchemyTableRecipe.TYPE_ID, ALCHEMY_TABLE_RECIPE);

    }

}
