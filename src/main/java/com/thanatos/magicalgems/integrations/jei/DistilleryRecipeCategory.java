package com.thanatos.magicalgems.integrations.jei;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.thanatos.magicalgems.data.recipes.DistilleryRecipe;
import com.thanatos.magicalgems.init.ModBlocks;
import com.thanatos.magicalgems.main;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

public class DistilleryRecipeCategory implements IRecipeCategory<DistilleryRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(main.MODID, "distillery");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(main.MODID, "textures/gui/distillery_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public DistilleryRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 4, 4, 168, 77);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.DISTILLERY.get()));
        this.arrow = helper.drawableBuilder(TEXTURE,176,1,5,21).
                buildAnimated(100, IDrawableAnimated.StartDirection.TOP,false);

    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends DistilleryRecipe> getRecipeClass() {
        return DistilleryRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.DISTILLERY.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(DistilleryRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DistilleryRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 12, 12);
        recipeLayout.getItemStacks().init(1, true, 74, 12);
        recipeLayout.getItemStacks().init(2, true, 74, 53);
        recipeLayout.getItemStacks().init(3,true,104,12);
        ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), recipe.getPotion());
        recipeLayout.getItemStacks().set(3,potion);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(DistilleryRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        this.arrow.draw(matrixStack,86,31);
    }



}