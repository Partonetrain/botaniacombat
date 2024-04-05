package info.partonetrain.botaniacombat.mixin.slaughtersaw;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.item.SlaughtersawItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow @Final public EnchantmentCategory category;
    @Shadow public abstract String getDescriptionId();

    @Inject(method = "canEnchant", at = @At("RETURN"), cancellable = true)
    private void botaniaCombat$canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof SlaughtersawItem){
            //This doesn't work... see Issue #4
            //BotaniaCombat.LOGGER.info("Slaughtersaw EnchantmentMixin: " + this.getDescriptionId());
            cir.setReturnValue(this.getDescriptionId().contains("backstabbing"));
        }
    }
}
