package com.codemob.ssone.network;

import com.codemob.ssone.start.StartAnimationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class CPlayStartAnimationPacket {
    public CPlayStartAnimationPacket() {

    }
    public CPlayStartAnimationPacket(RegistryFriendlyByteBuf byteBuffer) {
        this();
    }

    public void encode(RegistryFriendlyByteBuf byteBuffer) {

    }

    public void handle(CustomPayloadEvent.Context payloadEventContext) {
        Minecraft.getInstance().setScreen(new StartAnimationScreen());
    }
}
