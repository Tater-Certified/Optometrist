/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist;

import net.minecraft.server.level.ServerPlayer;

public interface VariablePlayerChunkCache {
    void updateViewDistanceCache(ServerPlayer player);
}
