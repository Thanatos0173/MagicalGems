package com.thanatos.magicalgems;

import com.thanatos.magicalgems.Enchantments.EnchantementInit;
import com.thanatos.magicalgems.TileEntities.ModTileEntities;
import com.thanatos.magicalgems.container.ModContainer;
import com.thanatos.magicalgems.init.ModBlocks;
import com.thanatos.magicalgems.init.ModItems;
import com.thanatos.magicalgems.init.ModOreGeneration;
import com.thanatos.magicalgems.screen.AlchemyTableScreen;
import com.thanatos.magicalgems.screen.DistilleryScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(main.MODID)
public class main {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "magicalgems";
    public main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EnchantementInit.register(bus);

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModTileEntities.register(bus);
        ModContainer.register(bus);


    }

    private void setup(final FMLCommonSetupEvent event) {
        ModOreGeneration features = new ModOreGeneration();
        features.init();
        MinecraftForge.EVENT_BUS.register(features);


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainer.ALCHEMY_TABLE_CONTAINER.get(),
                AlchemyTableScreen::new);
        ScreenManager.registerFactory(ModContainer.DISTILLERY_CONTAINER.get(),
                DistilleryScreen::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.DISTILLERY.get(), RenderType.getCutout());

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("magicalgems", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";

        });
    }
}
