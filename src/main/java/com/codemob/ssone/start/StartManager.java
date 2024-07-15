package com.codemob.ssone.start;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class StartManager extends SavedData {
    public ArrayList<UUID> getPreviousPlayers() {
        return previousPlayers;
    }

    private final ArrayList<UUID> previousPlayers = new ArrayList<>();

    public static StartManager get(MinecraftServer server) {
        DimensionDataStorage storage = server.overworld().getDataStorage();
        return storage.computeIfAbsent(new Factory<>(StartManager::create, StartManager::load,
                DataFixTypes.SAVED_DATA_MAP_DATA), "startManager");
    }

    private StartManager() {

    }

    public static StartManager load(CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        StartManager startManager = create();
        // each uuid is an array of two longs
        for (Tag player : tag.getList("joinedPlayers", Tag.TAG_LONG_ARRAY)) {
            long[] uuidArray = ((LongArrayTag) player).getAsLongArray();
            startManager.previousPlayers.add(new UUID(
                    uuidArray[0],
                    uuidArray[1]));
        }
        return startManager;
    }

    public static StartManager create() {
        return new StartManager();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        ListTag list = new ListTag();
        for (UUID player : previousPlayers) {
            long[] uuid = new long[] {
                    player.getMostSignificantBits(),
                    player.getLeastSignificantBits()
            };
            list.add(new LongArrayTag(uuid));
        }
        tag.put("joinedPlayers", list);
        return tag;
    }
}
