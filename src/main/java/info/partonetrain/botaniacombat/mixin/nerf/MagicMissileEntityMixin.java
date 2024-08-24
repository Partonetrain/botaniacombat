package info.partonetrain.botaniacombat.mixin.nerf;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.entity.MagicMissileEntity;

@Mixin(MagicMissileEntity.class)
public class MagicMissileEntityMixin {
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    public float botanaicombat_modifyMagicMissleDamage(float amount, @Local boolean evil){
        if(evil){
            return 12;
        }
        else{
            return BotaniaNerfConfiguredValues.missileDamage;
        }
    }
}
