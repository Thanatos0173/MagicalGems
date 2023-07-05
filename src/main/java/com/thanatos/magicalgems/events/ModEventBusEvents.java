package com.thanatos.magicalgems.events;


import com.thanatos.magicalgems.loot.ScuteAdditionModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.thanatos.magicalgems.main;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = main.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new ScuteAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(main.MODID,"dolphins_scute")));

    }

}
