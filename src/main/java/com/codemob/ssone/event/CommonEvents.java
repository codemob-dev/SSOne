package com.codemob.ssone.event;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.network.CPlayStartAnimationPacket;
import com.codemob.ssone.network.PacketHandler;
import com.codemob.ssone.start.StartManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = SSOne.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        StartManager startManager = StartManager.get(
                Objects.requireNonNull(player.getServer()));
        ArrayList<UUID> previousPlayers = startManager.getPreviousPlayers();
        startManager.setDirty();
        SSOne.LOGGER.info("previous players: " + String.join(", ", previousPlayers.stream().map(UUID::toString).toList()));

        if (!previousPlayers.contains(player.getUUID())) {
            previousPlayers.add(player.getUUID());
            PacketHandler.sendToPlayer(new CPlayStartAnimationPacket(), player);
        }
    }
}
