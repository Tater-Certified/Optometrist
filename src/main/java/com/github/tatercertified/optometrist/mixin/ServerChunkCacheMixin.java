/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist.mixin;

import com.github.tatercertified.optometrist.VariablePlayerChunkCache;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerChunkCache.class)
public class ServerChunkCacheMixin implements VariablePlayerChunkCache {
    @Shadow @Final public ChunkMap chunkMap;

    @Override
    public void updateViewDistanceCache(ServerPlayer player) {
        ((VariablePlayerChunkCache) this.chunkMap).updateViewDistanceCache(player);
    }
}
