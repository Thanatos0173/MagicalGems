package com.thanatos.magicalgems.init;


import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModOreGeneration {
    public ConfiguredFeature<?, ?> GEM_ORE_FEATURE;

    public void init()
    {
        GEM_ORE_FEATURE = register("gem_ore",
                Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig
                                .FillerBlockType.BASE_STONE_OVERWORLD,
                                ModBlocks.GEM_ORE.get().getDefaultState(), 6))

                .square()
                .range(12)
                .count(3));
    }

    public <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(
            String name, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
    }

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent e)
    {
        BiomeGenerationSettingsBuilder generation = e.getGeneration();
        if(e.getCategory() != Biome.Category.NETHER)
        {
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GEM_ORE_FEATURE);
        }
    }

}