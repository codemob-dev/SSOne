package com.codemob.ssone.event;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.entity.ModdedEntities;
import com.codemob.ssone.entity.custom.CobbledZombie;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SSOne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModdedEntities.COBBLED_ZOMBIE.get(), CobbledZombie.createAttributes().build());
    }
}