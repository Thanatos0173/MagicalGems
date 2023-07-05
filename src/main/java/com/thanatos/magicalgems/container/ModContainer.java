package com.thanatos.magicalgems.container;


import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.thanatos.magicalgems.main;

public class ModContainer {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, main.MODID);

    public static final RegistryObject<ContainerType<AlchemyTableContainer>> ALCHEMY_TABLE_CONTAINER
            = CONTAINERS.register("alchemy_table_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new AlchemyTableContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<DistilleryContainer>> DISTILLERY_CONTAINER
            = CONTAINERS.register("distillery_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new DistilleryContainer(windowId, world, pos, inv, inv.player);
            })));



    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}