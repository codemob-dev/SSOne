package com.codemob.ssone.entity.client;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.CobbledZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModModelLayers {
    public static final ModelLayerLocation COBBLED_ZOMBIE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "cobbled_zombie_layer"), "main");
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.COBBLED_ZOMBIE_LAYER, CobbledZombieModel::createBodyLayer);

    }
}
