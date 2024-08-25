package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.entity.BabylonWeaponEntity;

@Mixin(BabylonWeaponEntity.class)
public class BabylonWeaponEntityMixin {
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public float botanaicombat$modifyBabylonWeaponDamage(float value){
        return BotaniaNerfConfiguredValues.babylonDamage;
    }
}
