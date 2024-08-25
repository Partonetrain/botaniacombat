package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.equipment.bauble.FlugelTiaraItem;

@Mixin(FlugelTiaraItem.class)
public class FlugelTiaraItemMixin {
    @Inject(method = "getCost(Lnet/minecraft/world/item/ItemStack;I)I", at = @At("HEAD"), cancellable = true)
    private static void botaniacombat$modifyTiaraCost(ItemStack stack, int timeLeft, CallbackInfoReturnable<Integer> cir){
        if(timeLeft <= 0){
            cir.setReturnValue(BotaniaNerfConfiguredValues.tiaraOverkillCost);
        }
        else
        {
            cir.setReturnValue(BotaniaNerfConfiguredValues.tiaraCost);
        }
        cir.cancel();
    }

    @ModifyVariable(method = "onWornTick", at = @At(value = "INVOKE_ASSIGN", target = "Lvazkii/botania/common/helper/ItemNBTHelper;getBoolean(Lnet/minecraft/world/item/ItemStack;Ljava/lang/String;Z)Z", ordinal = 1), index = 8)
    private int botaniacombat$modifyTiaraTimeLeft(int original){
        BotaniaCombat.LOGGER.info(String.valueOf(original - BotaniaNerfConfiguredValues.tiaraExtraTime));
        return original - BotaniaNerfConfiguredValues.tiaraExtraTime;
    }

    //Botania has gui rendering code in the item class, so I'll put this here too
    @Mixin(FlugelTiaraItem.ClientLogic.class)
    public static class FlugelTiaraClientLogicMixin{
        @Inject(method = "estimateAdditionalNumRowsRendered(Lnet/minecraft/world/entity/player/Player;)I", at=@At("RETURN"), cancellable = true)
        private static void botaniacombat$moveTiaraFlightBar(Player player, CallbackInfoReturnable<Integer> cir){
            cir.setReturnValue(cir.getReturnValueI() + BotaniaNerfConfiguredValues.tiaraAdditionalRow);
        }
    }


}
