package com.thanatos.magicalgems.TileEntities;

import com.thanatos.magicalgems.main;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.thanatos.magicalgems.init.ModBlocks;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, main.MODID);

    public static RegistryObject<TileEntityType<AlchemyTableTile>> ALCHEMY_TABLE_TILE =
            TILE_ENTITIES.register("alchemy_table_tile", () -> TileEntityType.Builder.create(
                    AlchemyTableTile::new, ModBlocks.ALCHEMY_TABLE.get()).build(null));
    public static RegistryObject<TileEntityType<DistilleryTile>> DISTILLERY_TILE =
            TILE_ENTITIES.register("distillery_tile", () -> TileEntityType.Builder.create(
                    DistilleryTile::new, ModBlocks.DISTILLERY.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}