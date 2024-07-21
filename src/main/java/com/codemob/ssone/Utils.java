package com.codemob.ssone;

import net.minecraft.core.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.jetbrains.annotations.Nullable;

import javax.annotation.concurrent.Immutable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Utils {
    public static boolean isNormalSetup(MinecraftServer server) {
        return server.getDefaultGameType().isSurvival() && server.isDedicatedServer();
    }
    public static void genStructure(ServerLevel serverlevel, Structure structure, BlockPos pos) {
        ChunkGenerator chunkgenerator = serverlevel.getChunkSource().getGenerator();
        StructureStart structurestart = structure.generate(
                serverlevel.registryAccess(),
                chunkgenerator,
                chunkgenerator.getBiomeSource(),
                serverlevel.getChunkSource().randomState(),
                serverlevel.getStructureManager(),
                serverlevel.getSeed(),
                new ChunkPos(pos),
                0,
                serverlevel,
                biome -> true
        );
        if (!structurestart.isValid()) {
            throw new RuntimeException("No valid structurestart found");
        } else {
            BoundingBox boundingbox = structurestart.getBoundingBox();
            ChunkPos minChunk = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.minX()), SectionPos.blockToSectionCoord(boundingbox.minZ()));
            ChunkPos maxChunk = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.maxX()), SectionPos.blockToSectionCoord(boundingbox.maxZ()));

            checkLoaded(serverlevel, minChunk, maxChunk);

            ChunkPos.rangeClosed(minChunk, maxChunk)
                    .forEach(
                            chunk -> structurestart.placeInChunk(
                                    serverlevel,
                                    serverlevel.structureManager(),
                                    chunkgenerator,
                                    serverlevel.getRandom(),
                                    new BoundingBox(
                                            chunk.getMinBlockX(),
                                            serverlevel.getMinBuildHeight(),
                                            chunk.getMinBlockZ(),
                                            chunk.getMaxBlockX(),
                                            serverlevel.getMaxBuildHeight(),
                                            chunk.getMaxBlockZ()
                                    ),
                                    chunk
                            )
                    );
        }
    }
    public static <T> Holder.Reference<T> loadFromRegistry(RegistryAccess registryAccess, ResourceLocation resource, ResourceKey<Registry<T>> registry) {
        return registryAccess
                .registryOrThrow(registry)
                .getHolder(ResourceKey.create(registry, resource))
                .orElseThrow();
    }

    public static void checkLoaded(ServerLevel level, ChunkPos start, ChunkPos end) {
        if (ChunkPos.rangeClosed(start, end).anyMatch(chunk -> !level.isLoaded(chunk.getWorldPosition()))) {
            throw new RuntimeException("Chunks not loaded!");
        }
    }

    @Nullable
    public static BlockPos findNearestBlock(BlockPos pos, LevelReader level, Predicate<BlockState> blockCheck, int maxDistance) {
        return findNearbyBlock(pos, level, blockCheck, maxDistance, true);
    }

    @Nullable
    public static BlockPos findNearbyBlock(BlockPos pos, LevelReader level, Predicate<BlockState> blockCheck, int maxDistance, boolean sort) {
        double closestDist = Double.MAX_VALUE;
        BlockPos result = null;
        for (int x = pos.getX() - maxDistance / 2; x <= pos.getX() + maxDistance / 2; x++) {
            for (int y = pos.getY() - maxDistance / 2; y <= pos.getY() + maxDistance / 2; y++) {
                for (int z = pos.getZ() - maxDistance / 2; z <= pos.getZ() + maxDistance / 2; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockState block = level.getBlockState(blockPos);
                    if (blockCheck.test(block)) {
                        if (sort) {
                            double newDist = blockPos.distSqr(pos);
                            if (newDist < closestDist) {
                                result = blockPos;
                                closestDist = newDist;
                            }
                        } else {
                            return blockPos;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static BlockPos blockPosFromIntArray(int[] pos) {
        assert pos.length == 3;
        return new BlockPos(pos[0], pos[1], pos[2]);
    }
    public static int[] intArrayFromBlockPos(BlockPos pos) {
        return new int[] {pos.getX(), pos.getY(), pos.getZ()};
    }
    @Immutable
    public static class ClassWithSupplier<T> {
        private final Class<T> klass;
        private final Supplier<T> supplier;

        public ClassWithSupplier(Class<T> klass, Supplier<T> supplier) {
            this.klass = klass;
            this.supplier = supplier;
        }

        public Supplier<T> supplier() {
            return supplier;
        }

        public Class<T> get() {
            return klass;
        }
    }
}
