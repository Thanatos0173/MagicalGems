package com.thanatos.magicalgems.integrations.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thanatos.magicalgems.data.recipes.alchemy_table.AlchemyTableRecipe;
import com.thanatos.magicalgems.data.recipes.distillery.DistilleryRecipe;
import com.thanatos.magicalgems.init.ModBlocks;
import com.thanatos.magicalgems.main;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;

public class AlchemyTableRecipeCategory implements IRecipeCategory<AlchemyTableRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(main.MODID, "alchemy_table");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(main.MODID, "textures/gui/alchemy_table_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public AlchemyTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 4, 4, 168, 77);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ALCHEMY_TABLE.get()));
        this.arrow = helper.drawableBuilder(TEXTURE,177,1,7,19).
                buildAnimated(100, IDrawableAnimated.StartDirection.TOP,false);

    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AlchemyTableRecipe> getRecipeClass() {
        return AlchemyTableRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.ALCHEMY_TABLE.get().getTranslatedName().getString();
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
    public void setIngredients(AlchemyTableRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AlchemyTableRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 12, 12);
        recipeLayout.getItemStacks().init(1, true, 116, 11);
        recipeLayout.getItemStacks().init(2, true, 74, 12);
        recipeLayout.getItemStacks().init(3, false, 74, 53);
        ItemStack potionSlot = recipe.getPotion() == null ? recipe.getPotionSlot() : PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), recipe.getPotion());
        recipeLayout.getItemStacks().set(ingredients);
        recipeLayout.getItemStacks().set(2,potionSlot);
        recipeLayout.getItemStacks().set(3,recipe.getRecipeOutput());

    }

    @Override
    public void draw(AlchemyTableRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        this.arrow.draw(matrixStack,94,33);
    }



}