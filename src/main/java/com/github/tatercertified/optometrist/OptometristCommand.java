/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetChunkCacheRadiusPacket;
import net.minecraft.server.level.ServerPlayer;

public class OptometristCommand {
    public static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        int max;
        if (Optometrist.GLOBAL_MC_SERVER == null) {
            // Probably on a client
            max = 32;
        } else {
            max = Optometrist.GLOBAL_MC_SERVER.getPlayerList().getViewDistance();
        }
        dispatcher.register(Commands.literal("vd").requires(Commands.hasPermission(Commands.LEVEL_ADMINS)).then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("distance", IntegerArgumentType.integer(2, max)).executes((c) -> {
            Collection<ServerPlayer> players = EntityArgument.getPlayers(c, "targets");
            if (!players.isEmpty()) {
                int distance = IntegerArgumentType.getInteger(c, "distance");
                players.forEach(player -> setViewDistance(distance, player));
                c.getSource().sendSuccess(() -> Component.literal("Set view distance to " + distance + " for target(s)"), true);
            }

            return players.size();
        }))));
    }

    private static void setViewDistance(final int viewDistance, ServerPlayer player) {
        player.connection.send(new ClientboundSetChunkCacheRadiusPacket(viewDistance));
        ((VariableViewDistance)player).setViewDistance(viewDistance);
        ((VariablePlayerChunkCache) player.level().getChunkSource()).updateViewDistanceCache(player);
    }
}
