package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.entity.ThornChakramEntity;

@Mixin(ThornChakramEntity.class)
public class ThornChakramEntityMixin {
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    public float botaniacombat$modifyChakramDamage(float value){
        return BotaniaNerfConfiguredValues.chakramDamage;
    }
}
