package com.thanatos.magicalgems.integrations.jei;

import com.thanatos.magicalgems.data.recipes.DistilleryRecipe;
import com.thanatos.magicalgems.data.recipes.ModRecipeTypes;
import com.thanatos.magicalgems.main;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class MagicalGemsJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(main.MODID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new DistilleryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.DISTILLERY_RECIPE).stream()
                        .filter(r -> r instanceof DistilleryRecipe).collect(Collectors.toList()),
                DistilleryRecipeCategory.UID);
    }
}
