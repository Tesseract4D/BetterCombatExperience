package mods.tesseract.bce.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    public EntityPlayerSP player;

    @Inject(method = "processKeyBinds", at = @At(value = "TAIL"))
    private void inject(CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP h = mc.player;
        if (mc.gameSettings.keyBindAttack.isKeyDown() && h.getCooledAttackStrength(0) >= 1 && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
            Entity t = mc.objectMouseOver.entityHit;
            mc.playerController.attackEntity(h, t);
        }
    }

    @Inject(method = "clickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;resetCooldown()V"), cancellable = true)
    private void inject2(CallbackInfo ci) {
        ForgeHooks.onEmptyLeftClick(player);
        player.swingArm(EnumHand.MAIN_HAND);
        ci.cancel();
    }
}
