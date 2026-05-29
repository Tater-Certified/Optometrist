/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist.mixin;

import com.github.tatercertified.optometrist.Optometrist;
import com.github.tatercertified.optometrist.VariableViewDistance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin implements VariableViewDistance {
    @Shadow
    @Final
    private MinecraftServer server;
    private int viewDistance;

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void optometrist$loadViewDistance(ValueInput input, CallbackInfo ci) {
        this.viewDistance = input.getIntOr("view_distance", this.server.getGameRules().get(Optometrist.DEFAULT_VIEW_DISTANCE));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void optometrist$saveViewDistance(ValueOutput output, CallbackInfo ci) {
        output.putInt("view_distance", this.viewDistance);
    }

    @Override
    public int getViewDistance() {
        return this.viewDistance;
    }

    @Override
    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }
}
