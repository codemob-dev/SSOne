package com.codemob.ssone.command;

import com.codemob.ssone.role.system.DefaultRole;
import com.codemob.ssone.role.system.RoleManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Objects;

public class RoleCommand {
    private static final DynamicCommandExceptionType ERROR_INVALID_ROLE =
            new DynamicCommandExceptionType((role) ->
                    Component.translatable("commands.ssone.role.invalid",
                            Component.literal(role.toString())));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
                Commands.literal("role")
                        .requires(stack -> stack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(
                                Commands.literal("get")
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .executes(
                                                                stack -> getRole(
                                                                        stack.getSource(),
                                                                        EntityArgument.getPlayer(stack, "player")
                                                                )
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("set")
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(
                                                                Commands.argument("role", ResourceLocationArgument.id())
                                                                        .executes(
                                                                                stack -> setRole(
                                                                                        stack.getSource(),
                                                                                        EntityArgument.getPlayer(stack, "player"),
                                                                                        stack.getArgument("role", ResourceLocation.class)
                                                                                )
                                                                        )
                                                        )
                                        )
                        )
        );
    }

    private static int setRole(CommandSourceStack source, ServerPlayer player, ResourceLocation role) throws CommandSyntaxException {
        RoleManager roleManager = RoleManager.get(Objects.requireNonNull(player.getServer()));
        if (!roleManager.roleExists(role)) {
            throw ERROR_INVALID_ROLE.create(role);
        }
        roleManager.setRole(player, role);

        source.sendSuccess(
                () -> Component.translatable("commands.ssone.role.success",
                        player.getName(),
                        Component.literal(role.toString())), false);

        return 1;
    }

    private static int getRole(CommandSourceStack source, ServerPlayer player) {
        RoleManager roleManager = RoleManager.get(Objects.requireNonNull(player.getServer()));
        DefaultRole role = roleManager.getRole(player);
        source.sendSuccess(
                () -> Component.translatable("commands.ssone.role.query",
                player.getName(),
                Component.literal(role.getId().toString())), false);
        return 1;
    }
}
