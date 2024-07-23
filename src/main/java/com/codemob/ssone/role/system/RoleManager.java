package com.codemob.ssone.role.system;

import com.codemob.ssone.SSOne;
import com.google.common.collect.HashBiMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class RoleManager extends SavedData {
    private static final HashBiMap<ResourceLocation, Class<? extends DefaultRole>> registeredRoles = HashBiMap.create();
    private static final HashMap<Class<? extends DefaultRole>, Function<RoleManager, ? extends DefaultRole>> roleConstructors = new HashMap<>();
    private final HashMap<UUID, DefaultRole> playerRoles = new HashMap<>();

    public static <T extends DefaultRole> void registerRole(@NotNull Class<T> role, @NotNull Function<RoleManager, T> constructor, @NotNull ResourceLocation id) {
        if (registeredRoles.containsKey(id)) {
            if (registeredRoles.get(id) == role) {
                return;
            }
            throw new RuntimeException("'%s' is already registered!".formatted(id));
        }
        if (Modifier.isAbstract(role.getModifiers())) {
            throw new RuntimeException("The class cannot be abstract!");
        }
        if (Modifier.isInterface(role.getModifiers())) {
            throw new RuntimeException("Cannot register an interface!");
        }
        if (registeredRoles.containsValue(role)) {
            throw new RuntimeException("'%s' is already registered!".formatted(role.toString()));
        }
        registeredRoles.put(id, role);
        roleConstructors.put(role, constructor);
    }

    public DefaultRole getRole(UUID player) {
        if (!playerRoles.containsKey(player)) player = null;
        return playerRoles.get(player);
    }
    public DefaultRole getRole(Player player) {
        return getRole(player.getUUID());
    }
    public void setRole(@NotNull UUID player, @NotNull Class<? extends DefaultRole> role) {
        if (getRole(player).getClass() != role) {
            playerRoles.put(player, roleConstructors.get(role).apply(this));
        }
    }
    public void setRole(@NotNull Player player, @NotNull Class<? extends DefaultRole> role) {
        setRole(player.getUUID(), role);
    }
    public void setRole(@NotNull UUID player, @NotNull ResourceLocation id) {
        if (!roleExists(id)) {
            throw new RuntimeException("Could not find role '%s'".formatted(id));
        }
        setRole(player, Objects.requireNonNull(registeredRoles.get(id)));
    }
    public boolean roleExists(ResourceLocation role) {
        return registeredRoles.containsKey(role);
    }
    public void setRole(@NotNull Player player, @NotNull ResourceLocation id) {
        setRole(player.getUUID(), id);
    }

    public ResourceLocation getRoleId(@NotNull DefaultRole role) {
        return registeredRoles.inverse().get(role.getClass());
    }

    public static RoleManager get(MinecraftServer server) {
        DimensionDataStorage storage = server.overworld().getDataStorage();
        return storage.computeIfAbsent(new Factory<>(RoleManager::create, RoleManager::load,
                DataFixTypes.SAVED_DATA_MAP_DATA), "roleManager");
    }

    private RoleManager() {

    }

    private static RoleManager load(CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        RoleManager roleManager = create();
        CompoundTag playerRoles = tag.getCompound("playerRoles");
        for (String key : playerRoles.getAllKeys()) {
            roleManager.setRole(UUID.fromString(key),
                    Objects.requireNonNull(
                            registeredRoles.get(
                                    ResourceLocation.parse(
                                            playerRoles.getString(key))))
            );
        }
        return roleManager;
    }

    private static RoleManager create() {
        RoleManager.registerRole(
                DefaultRole.class,
                DefaultRole::new,
                ResourceLocation.fromNamespaceAndPath(SSOne.MODID, "default"));

        RoleManager roleManager = new RoleManager();
        registeredRoles.forEach((loc, roleClass) -> {
            DefaultRole role = roleConstructors.get(roleClass).apply(roleManager);
            UUID uuid = role.getDefaultUUID();
            roleManager.playerRoles.put(role.getDefaultUUID(), role);
        });
        return roleManager;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        CompoundTag playerRolesTag = new CompoundTag();
        playerRoles.forEach((player, role) ->
                playerRolesTag.putString(
                        player.toString(),
                        registeredRoles.inverse().get(role.getClass()).toString()));
        tag.put("playerRoles", new CompoundTag());
        return tag;
    }
}
