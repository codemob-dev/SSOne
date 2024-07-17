package com.codemob.ssone.event;

import com.codemob.ssone.SSOne;
import com.codemob.ssone.Utils;
import com.codemob.ssone.network.CPlayStartAnimationPacket;
import com.codemob.ssone.network.PacketHandler;
import com.codemob.ssone.start.StartManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
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

        if (!previousPlayers.contains(player.getUUID())) {
            previousPlayers.add(player.getUUID());
            BlockPos pos = startManager.getBlimpSpawn();
            player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            PacketHandler.sendToPlayer(new CPlayStartAnimationPacket(), player);
        }
    }
    @SuppressWarnings("All")
    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event) {
        StartManager startManager = StartManager.get(event.getServer());
        if (!startManager.hasGeneratedBlimp()) {
            ServerLevel overworld = event.getServer().overworld();
            BlockPos pos = overworld.getSharedSpawnPos();

            Structure blimpStruct = Utils.loadFromRegistry(
                    event.getServer().registryAccess(),
                    ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "blimp_crash"),
                    Registries.STRUCTURE).value();

            Utils.genStructure(overworld, blimpStruct, pos);

            startManager.setGeneratedBlimp();
            BlockPos spawn = Utils.findNearbyBlock(pos, overworld, block -> block.getBlock() == Blocks.BLUE_CONCRETE, 48, false);
            overworld.setBlockAndUpdate(spawn, Blocks.AIR.defaultBlockState());
            startManager.setBlimpSpawn(spawn);
        }
    }
}
