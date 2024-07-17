package com.codemob.ssone.start;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class StartManager extends SavedData {

    private final ArrayList<UUID> previousPlayers = new ArrayList<>();
    private boolean hasGeneratedBlimp = false;
    private BlockPos blimpSpawn = BlockPos.ZERO;

    public void setBlimpSpawn(BlockPos blimpSpawn) {
        this.blimpSpawn = blimpSpawn;
        setDirty();
    }

    public BlockPos getBlimpSpawn() {
        return blimpSpawn;
    }

    public ArrayList<UUID> getPreviousPlayers() {
        return previousPlayers;
    }

    public boolean hasGeneratedBlimp() {
        return hasGeneratedBlimp;
    }

    public void setGeneratedBlimp(boolean hasGeneratedBlimp) {
        this.hasGeneratedBlimp = hasGeneratedBlimp;
        setDirty();
    }

    public void setGeneratedBlimp() {
        setGeneratedBlimp(true);
    }

    public static StartManager get(MinecraftServer server) {
        DimensionDataStorage storage = server.overworld().getDataStorage();
        return storage.computeIfAbsent(new Factory<>(StartManager::create, StartManager::load,
                DataFixTypes.SAVED_DATA_MAP_DATA), "startManager");
    }

    private StartManager() {

    }

    public static StartManager load(CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        StartManager startManager = create();
        for (Tag player : tag.getList("joinedPlayers", Tag.TAG_LONG_ARRAY)) {
            int[] uuidArray = ((IntArrayTag) player).getAsIntArray();
            startManager.previousPlayers.add(UUIDUtil.uuidFromIntArray(uuidArray));
        }
        startManager.hasGeneratedBlimp = tag.getBoolean("hasGeneratedBlimp");
        startManager.blimpSpawn = BlockPos.of(tag.getLong("blimpSpawn"));
        return startManager;
    }

    public static StartManager create() {
        return new StartManager();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        ListTag list = new ListTag();
        for (UUID player : previousPlayers) {
            list.add(new IntArrayTag(UUIDUtil.uuidToIntArray(player)));
        }
        tag.put("joinedPlayers", list);
        tag.putBoolean("hasGeneratedBlimp", hasGeneratedBlimp);
        tag.putLong("blimpSpawn", blimpSpawn.asLong());
        return tag;
    }
}
