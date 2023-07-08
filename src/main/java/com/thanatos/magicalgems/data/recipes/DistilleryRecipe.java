package com.thanatos.magicalgems.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thanatos.magicalgems.init.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.*;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class DistilleryRecipe implements IDistilleryRecipe{

    private final ResourceLocation id;
    private final ItemStack output;
    private final Potion potion;


    private final NonNullList<Ingredient> recipeItems;


    public DistilleryRecipe(ResourceLocation id, ItemStack output,
                            NonNullList<Ingredient> recipeItems, Potion potion) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.potion = potion;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {

        return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), getPotion()).getTranslationKey().equalsIgnoreCase(inv.getStackInSlot(2).getTranslationKey()) &&
               recipeItems.get(0).test(inv.getStackInSlot(0)) &&
               recipeItems.get(1).test(inv.getStackInSlot(1)) &&
               recipeItems.get(2).test(inv.getStackInSlot(3));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    public Potion getPotion() {
        return potion;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }


    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.DISTILLERY.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.DISTILLERY_SERIALIZER.get();
    }

    public static class DistilleryType implements IRecipeType<DistilleryRecipe>{
        @Override
        public String toString() {
            return DistilleryRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DistilleryRecipe>{


        @Override
        public DistilleryRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");

            String potionName = JSONUtils.getString(json,"potion");


            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new DistilleryRecipe(recipeId, output,
                    inputs,Potion.getPotionTypeForName(potionName));
        }

        @Nullable
        @Override
        public DistilleryRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);



            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();

            int id = buffer.readInt();
            Effect effect = Effect.get(id);

            return new DistilleryRecipe(recipeId, output,
                    inputs,null);
        }

        @Override
        public void write(PacketBuffer buffer, DistilleryRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }

    }
}
