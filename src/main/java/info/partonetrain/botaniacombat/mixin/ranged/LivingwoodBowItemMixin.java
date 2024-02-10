package info.partonetrain.botaniacombat.mixin.ranged;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.equipment.tool.bow.CrystalBowItem;
import vazkii.botania.common.item.equipment.tool.bow.LivingwoodBowItem;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;

@Mixin(LivingwoodBowItem.class)
public abstract class LivingwoodBowItemMixin {
    //This is not injected if RangedWeaponAPI is not installed. See BotaniaCombatMixinPlugin
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/item/Item$Properties;)V")
    private void botaniacombat_addRangedConfigToBotania(CallbackInfo info) {
        RangedConfig rangedConfig = new RangedConfig(20, 6, null);
        if ((Object) this instanceof CrystalBowItem) {
            rangedConfig = new RangedConfig(12, 6, null);
        }
        ((CustomRangedWeapon) this).configure(rangedConfig);
    }
}