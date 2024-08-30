package info.partonetrain.botaniacombat.mixin.vanillacombat;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin {
    @Inject(method = "trySpawnBurst(Lnet/minecraft/world/entity/player/Player;)V", at=@At("HEAD"), cancellable = true)
    private static void botaniacombat$checkTerrasteelCostConfig(Player player, CallbackInfo ci){
        if(!ManaItemHandler.INSTANCE.requestManaExactForTool(new ItemStack(BotaniaItems.terraSword), player, BotaniaNerfConfiguredValues.terrasteelBeamCost, true)) {
            ci.cancel();
        }
    }

}