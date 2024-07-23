package com.codemob.ssone.role.system;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DefaultRole {
    private final RoleManager roleManager;

    public RoleManager getRoleManager() {
        return roleManager;
    }

    protected DefaultRole(RoleManager manager) {
        roleManager = manager;
    }
    public void onDeath(LivingDeathEvent event, ServerPlayer player) {

    }

    public ResourceLocation getId() {
        return roleManager.getRoleId(this);
    }

    @Nullable
    public UUID getDefaultUUID() {
        return null;
    }

    public @NotNull MagicAbilities getMagicAbilities() {
        return new MagicAbilities();
    }
}
