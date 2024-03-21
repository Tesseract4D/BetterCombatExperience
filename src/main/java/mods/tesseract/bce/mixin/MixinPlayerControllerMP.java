package mods.tesseract.bce.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {

    @Inject(method = "resetBlockRemoving", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;resetCooldown()V"), cancellable = true)
    private void inject(CallbackInfo ci) {
        ci.cancel();
    }
}
