package com.codemob.ssone.role.system;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DefaultRole {
    public void onDeath(LivingDeathEvent event, ServerPlayer player) {

    }

    @Nullable
    public UUID getDefaultUUID() {
        return null;
    }

    public @NotNull MagicAbilities getMagicAbilities() {
        return new MagicAbilities();
    }
}
