package info.partonetrain.botaniacombat.mixin.nerf;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.impl.mana.ManaItemHandlerImpl;

@Mixin(ManaItemHandlerImpl.class)
public class ManaItemHandlerImplMixin {
    @Inject(method = "requestManaExact", at=@At("HEAD"), cancellable = true)
    void botaniacombat$checkAllowedFreeRequests(ItemStack stack, Player player, int manaToGet, boolean remove, CallbackInfoReturnable<Boolean> cir){
        if(manaToGet == 0){ //necessary for terrasteelBeamCost and starcallerProjectileCost config options to work.
            cir.setReturnValue(true);
        }
    }
}
