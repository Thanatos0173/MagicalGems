package com.thanatos.magicalgems.data.recipes.alchemy_table;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.thanatos.magicalgems.data.recipes.ModRecipeTypes;
import com.thanatos.magicalgems.init.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class AlchemyTableRecipe implements IAlchemyTableRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Potion potion;
    private final ItemStack potionSlot; //For the crafts that don't require a potion but a vial.


    private final NonNullList<Ingredient> recipeItems;


    public AlchemyTableRecipe(ResourceLocation id, ItemStack output,
                              NonNullList<Ingredient> recipeItems, Potion potion, ItemStack potionSlot) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.potion = potion;
        this.potionSlot = potionSlot;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return

                (getPotion() == null ?
                        getPotionSlot().getItem() == inv.getStackInSlot(1).getItem()

                        :
                        PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), getPotion()).getTranslationKey()
                       .equalsIgnoreCase(inv.getStackInSlot(1).getTranslationKey()))


               &&
               recipeItems.get(0).test(inv.getStackInSlot(0)) &&
               recipeItems.get(1).test(inv.getStackInSlot(2));
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

    public ItemStack getPotionSlot(){return potionSlot;}

    @Override
    public ResourceLocation getId() {
        return id;
    }


    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.ALCHEMY_TABLE.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.ALCHEMY_TABLE_SERIALIZER.get();
    }

    public static class AlchemyTableType implements IRecipeType<AlchemyTableRecipe>{
        @Override
        public String toString() {
            return AlchemyTableRecipe.TYPE_ID.toString();
        }
    }


    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlchemyTableRecipe>{

        private static JsonObject stringToJson(String str){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item",str);
            return jsonObject;
        }


        @Override
        public AlchemyTableRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new AlchemyTableRecipe(recipeId, output,
                    inputs,
                    PotionUtils
                            .addPotionToItemStack(new ItemStack(Items.POTION)
                                    ,Potion.getPotionTypeForName(JSONUtils.getString(json,"potion")))
                            .getTranslationKey().equalsIgnoreCase("item.minecraft.potion.effect.empty")
                            ? null : Potion.getPotionTypeForName(JSONUtils.getString(json,"potion"))
                    ,
                    PotionUtils
                            .addPotionToItemStack(new ItemStack(Items.POTION)
                                    ,Potion.getPotionTypeForName(JSONUtils.getString(json,"potion")))
                            .getTranslationKey().equalsIgnoreCase("item.minecraft.potion.effect.empty")
                            ? ShapedRecipe.deserializeItem(stringToJson(JSONUtils.getString(json,"potion"))) : null
                    );
        }

        @Nullable
        @Override
        public AlchemyTableRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);



            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();

            return new AlchemyTableRecipe(recipeId, output,
                    inputs,null,null);
        }

        @Override
        public void write(PacketBuffer buffer, AlchemyTableRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }

    }
}
