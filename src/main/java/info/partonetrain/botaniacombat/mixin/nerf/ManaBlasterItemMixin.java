package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.item.ManaBlasterItem;

@Mixin(ManaBlasterItem.class)
public class ManaBlasterItemMixin {
    @ModifyArg(method = "getBurstProps", at = @At(target = "Lvazkii/botania/api/mana/BurstProperties;<init>(IIFFFI)V", value = "INVOKE"), index = 0)
    public int botaniacombat$modifyBlasterBurstCost(int maxMana){
        return BotaniaNerfConfiguredValues.manaBlasterCost;
    }
}
