package com.thanatos.magicalgems.init;


import com.thanatos.magicalgems.init.custom.AlchemyTableBlock;
import com.thanatos.magicalgems.init.custom.DistilleryBlock;
import com.thanatos.magicalgems.main;
import com.thanatos.magicalgems.utils.ModItemGroups;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);




    public static final RegistryObject<Block> GEM_ORE = createBlock("gem_ore",
            () -> new Block(
                    AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3f, 15f).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

    public static final RegistryObject<Block> ALCHEMY_TABLE = createBlock("alchemy_table",
            () -> new AlchemyTableBlock(AbstractBlock.Properties.create(Material.IRON)
                    .hardnessAndResistance(3f, 15f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(0).notSolid()));


    public static final RegistryObject<Block> DISTILLERY = createBlock("distillery",
            () -> new DistilleryBlock(AbstractBlock.Properties.create(Material.IRON)
                    .hardnessAndResistance(3f, 15f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(0).notSolid()));

    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier)
    {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ModItemGroups.MAGICALGEMS_TAB)));
        return block;
    }
}
