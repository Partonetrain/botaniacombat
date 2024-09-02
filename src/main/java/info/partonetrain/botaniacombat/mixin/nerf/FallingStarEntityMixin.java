package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.entity.FallingStarEntity;

@Mixin(FallingStarEntity.class)
public class FallingStarEntityMixin {
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    public float botaniacombat$modifyFallingStarDamage(float amount){
            return Math.random() < 0.25 ? BotaniaNerfConfiguredValues.fallingStarHighDamage : BotaniaNerfConfiguredValues.fallingStarNormalDamage;
    }
}
