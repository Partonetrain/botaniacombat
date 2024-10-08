package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin {
    @ModifyVariable(method = "updateBurst", at = @At(value = "STORE", target = "Lnet/minecraft/world/item/Tier;getAttackDamageBonus()F"), ordinal = 0)
    public float botaniacombat$modifyTerraBladeDamage(float original){
        return BotaniaNerfConfiguredValues.terraBladeDamage;
    }
}
