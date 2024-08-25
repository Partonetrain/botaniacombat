package info.partonetrain.botaniacombat.mixin.nerf;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.fabric_extras.ranged_weapon.api.EntityAttributes_RangedWeapon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.item.lens.DamagingLens;

@Mixin(DamagingLens.class)
public class DamagingLensMixin {
    @ModifyArg(method = "updateBurst", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
    public float botanaicombat$modifyDamageLensDamage(float value, @Local ThrowableProjectile entity){
        float damage;
        if(BotaniaCombat.RANGED_WEAPON_API_INSTALLED && entity.getOwner() instanceof Player player){
            damage = (float) player.getAttributeValue(EntityAttributes_RangedWeapon.DAMAGE.attribute); //will get value from held blaster
        }
        else{
            damage = BotaniaNerfConfiguredValues.dmgLensDamage;
        }

        return damage;
    }
}
