package info.partonetrain.botaniacombat.mixin.slaughtersaw;

import info.partonetrain.botaniacombat.item.SlaughtersawItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getAvailableEnchantmentResults", at = @At("RETURN"), cancellable = true)
    private static void botaniaCombat$getAvailableEnchantmentResults(int level, ItemStack stack, boolean allowTreasure, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {

        if(stack.getItem() instanceof SlaughtersawItem){
            ItemStack fakeKnife = BuiltInRegistries.ITEM.get(new ResourceLocation("farmersdelight", "golden_knife")).getDefaultInstance();
            cir.setReturnValue(EnchantmentHelper.getAvailableEnchantmentResults(level, fakeKnife, allowTreasure));
        }
    }
}
