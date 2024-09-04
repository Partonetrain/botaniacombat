package info.partonetrain.botaniacombat.mixin.nerf;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.item.lens.DamagingLens;

@Mixin(DamagingLens.class)
public class DamagingLensMixin {
    @ModifyArg(method = "updateBurst", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    public float botaniacombat$modifyDamageLensDamage(float value, @Local ThrowableProjectile entity){
        return BotaniaNerfConfiguredValues.getDamageLensDamage(entity.getOwner());
    }
}
