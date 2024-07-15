package com.codemob.ssone.network;

import com.codemob.ssone.SSOne;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketHandler {
    private static final SimpleChannel CHANNEL = ChannelBuilder.named(
            ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "main"))
            .serverAcceptedVersions(((status, version) -> true))
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register() {
        CHANNEL.messageBuilder(CPlayStartAnimationPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CPlayStartAnimationPacket::encode)
                .decoder(CPlayStartAnimationPacket::new)
                .consumerMainThread(CPlayStartAnimationPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg) {
        CHANNEL.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        CHANNEL.send(msg, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToAllPlayers(Object msg) {
        CHANNEL.send(msg, PacketDistributor.ALL.noArg());
    }
}
