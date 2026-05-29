package com.github.tatercertified.optometrist.mixin;

import com.github.tatercertified.optometrist.Optometrist;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "spin", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/atomic/AtomicReference;set(Ljava/lang/Object;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static <S extends MinecraftServer> void optometrist$getServerReference(Function<Thread, S> factory, CallbackInfoReturnable<S> cir, AtomicReference serverReference, Thread thread, MinecraftServer server) {
        Optometrist.GLOBAL_MC_SERVER = server;
    }
}
