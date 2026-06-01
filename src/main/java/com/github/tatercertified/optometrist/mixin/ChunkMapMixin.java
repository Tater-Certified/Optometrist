/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist.mixin;

import com.github.tatercertified.optometrist.VariablePlayerChunkCache;
import com.github.tatercertified.optometrist.VariableViewDistance;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkMap.class)
public abstract class ChunkMapMixin implements VariablePlayerChunkCache {
    @Shadow
    protected abstract void updateChunkTracking(ServerPlayer player);

    @Redirect(
            method = "getPlayerViewDistance",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/level/ServerPlayer;requestedViewDistance()I"))
    private int optometrist$overridePlayerViewDistance(ServerPlayer instance) {
        return ((VariableViewDistance) instance).getViewDistance();
    }

    @Override
    public void updateViewDistanceCache(ServerPlayer player) {
        this.updateChunkTracking(player);
    }
}
