package com.codemob.ssone.worldgen.biome;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.ModdedEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.common.world.BiomeSpecialEffectsBuilder;

public class ModBiomes {
    public static final ResourceKey<Biome> DEEPSLATE_MIDLANDS = ResourceKey.create(Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "deepslate_midlands"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(DEEPSLATE_MIDLANDS, deepslateMidlands(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome deepslateMidlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModdedEntities.COBBLED_ZOMBIE.get(), 125, 3, 5));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 80, 1, 3));

        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

        return new Biome.BiomeBuilder()
                       .hasPrecipitation(true)
                       .downfall(0.4f)
                       .temperature(0.7f)
                       .generationSettings(biomeBuilder.build())
                       .mobSpawnSettings(spawnBuilder.build())
                       .specialEffects(BiomeSpecialEffectsBuilder.create(
                               0x4f587d, 0x364da8, 0x2e3f82, 0x546bc4)
                                               .grassColorOverride(0x2c6b34)
                                               .foliageColorOverride(0x26572c)
                                               .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                       .build();
    }
}
