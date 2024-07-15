package com.codemob.ssone.event;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.start.StartAnimationScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SSOne.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void screenOpening(ScreenEvent.Opening event) {
        if (event.getCurrentScreen() instanceof StartAnimationScreen screen) {
            event.setCanceled(true);
            screen.setScreenToOpen(event.getNewScreen());
        }
    }
}
