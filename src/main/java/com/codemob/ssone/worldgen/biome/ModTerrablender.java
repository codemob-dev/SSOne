package com.codemob.ssone.worldgen.biome;

import com.codemob.ssone.SSOne;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.RegionType;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "overworld"),
                RegionType.OVERWORLD, 8));
    }
}
