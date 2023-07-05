package com.thanatos.magicalgems.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.lang.Math;

public class ScuteAdditionModifier extends LootModifier {
    private final Item addition;

    protected ScuteAdditionModifier(ILootCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    private static float f(int scuteNumber, int looting){ // Called f because it's a mathematical function (f(x,y)) with x the scute number to loot and y the looting level
        return new BigDecimal((float) (5 * Math.abs(-(looting + 1) * Math.tan(Math.sin(scuteNumber)))/100)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace anything!

        float randomValue = context.getRandom().nextFloat();

        float loot1 = f(1,context.getLootingModifier());
        float loot2 = loot1 + f(2,context.getLootingModifier());
        float loot3 = loot2 + f(3,context.getLootingModifier());
        float loot4 = loot3 + f(4,context.getLootingModifier());

        if (randomValue <= loot1 ) generatedLoot.add(new ItemStack(addition,1));
        if (randomValue > loot1 && randomValue <= loot2) generatedLoot.add(new ItemStack(addition,2));
        if (randomValue > loot2 && randomValue <= loot3) generatedLoot.add(new ItemStack(addition,3));
        if (randomValue > loot3 && randomValue <= loot4) generatedLoot.add(new ItemStack(addition,4));

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ScuteAdditionModifier> {

        @Override
        public ScuteAdditionModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(JSONUtils.getString(object, "addition")));
            return new ScuteAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(ScuteAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }


}
