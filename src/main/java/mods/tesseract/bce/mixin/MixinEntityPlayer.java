package mods.tesseract.bce.mixin;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {
    @Inject(method = "resetCooldown", at = @At(value = "HEAD"), cancellable = true)
    private void inject1(CallbackInfo ci) {
        if (((EntityPlayer) (Object) this).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() != 1) {
            ci.cancel();
        }
    }
}
