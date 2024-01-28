package info.partonetrain.botaniacombat.mixin.client;

import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)

public abstract class MinecraftMixin {

    @Shadow
    @Nullable
    public LocalPlayer player;

    //this is not actually called when the player swings BetterCombat is installed, but it's here in case you want to use the mod without BetterCombat or for misc compat
    @Inject(method = "startAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;resetAttackStrengthTicker()V"))
    private void leftClickEmpty(CallbackInfoReturnable<Boolean> ci) {
        TerrasteelWeaponItem.leftClick(player.getMainHandItem());
        GaiaGreatswordItem.leftClick(player.getMainHandItem());
    }
}
