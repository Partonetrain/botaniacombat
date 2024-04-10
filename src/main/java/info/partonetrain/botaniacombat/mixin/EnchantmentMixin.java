package info.partonetrain.botaniacombat.mixin;

import info.partonetrain.botaniacombat.item.SlaughtersawItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void botaniaCombat$canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof SlaughtersawItem){
            Enchantment self = (Enchantment) (Object) this;
            cir.setReturnValue(SlaughtersawItem.isEnchantmentAllowed(self));
            //NOTE: FDRF overrides Backstabbing's canEnchant method.
            // As of writing, this code is ineffective for Backstabbing.
            //See issue: https://github.com/MehVahdJukaar/FarmersDelight/issues/22
        }
    }
}
