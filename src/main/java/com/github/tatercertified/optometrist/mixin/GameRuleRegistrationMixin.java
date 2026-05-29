/**
 * Copyright (c) 2026 QPCrummer
 * This project is Licensed under <a href="https://github.com/Tater-Certified/Optometrist/blob/main/LICENSE">MIT</a>
 */
package com.github.tatercertified.optometrist.mixin;

import com.github.tatercertified.optometrist.Optometrist;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRules.class)
public abstract class GameRuleRegistrationMixin {

    @Shadow
    private static GameRule<Integer> registerInteger(String id, GameRuleCategory category, int defaultValue, int min, int max) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(
            method = "<clinit>",
            at =
            @At(
                    value = "FIELD",
                    target =
                            "Lnet/minecraft/world/level/gamerules/GameRules;FIRE_SPREAD_RADIUS_AROUND_PLAYER:Lnet/minecraft/world/level/gamerules/GameRule;",
                    opcode = Opcodes.PUTSTATIC))
    private static void optometrist$registerGameRules(CallbackInfo ci) {
        int vd;
        if (Optometrist.GLOBAL_MC_SERVER == null) {
            // Probably launching a client
            vd = 32;
        } else {
            vd = Optometrist.GLOBAL_MC_SERVER.getPlayerList().getViewDistance();
        }

        Optometrist.DEFAULT_VIEW_DISTANCE = registerInteger("default_view_distance", GameRuleCategory.PLAYER, vd, 2, vd);
    }
}
