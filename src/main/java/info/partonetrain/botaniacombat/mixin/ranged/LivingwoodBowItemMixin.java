package info.partonetrain.botaniacombat.mixin.ranged;

import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.equipment.tool.bow.CrystalBowItem;
import vazkii.botania.common.item.equipment.tool.bow.LivingwoodBowItem;

@Mixin(LivingwoodBowItem.class)
public abstract class LivingwoodBowItemMixin {
    //This is not injected if RangedWeaponAPI is not installed. See BotaniaCombatMixinPlugin
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/item/Item$Properties;)V")
    private void botaniacombat$addRangedConfigToBotania(CallbackInfo info) {
        if (((Object) this) instanceof CrystalBowItem) {
            ((CustomRangedWeapon) this).configure(new RangedConfig(12, 6, null));
        } else {
            ((CustomRangedWeapon) this).configure(new RangedConfig(20, 6, null));
        }
    }
}