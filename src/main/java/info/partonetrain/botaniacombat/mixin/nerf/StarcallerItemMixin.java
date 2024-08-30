package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.StarcallerItem;

@Mixin(StarcallerItem.class)
public class StarcallerItemMixin {
    @Inject(method = "summonFallingStar(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;)V", at=@At("HEAD"), cancellable = true)
    public void botaniaCombat$noFreeStars(ItemStack stack, Level world, Player player, CallbackInfo ci){
        if(BotaniaNerfConfiguredValues.starcallerProjectileCost != 0 &&
                !ManaItemHandler.INSTANCE.requestManaExactForTool(stack, player, BotaniaNerfConfiguredValues.starcallerProjectileCost, true)){
            ci.cancel();
        }
    }
}
