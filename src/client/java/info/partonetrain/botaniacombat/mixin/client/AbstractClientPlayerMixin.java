package info.partonetrain.botaniacombat.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import vazkii.botania.common.item.BotaniaItems;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {
    /**
     * Mixin to fix Botania #4543
     * Courtesy of Wormbo, https://github.com/TheRealWormbo/Botania/commit/08af20658bb607a30033e03262341dc9e4f4255e#diff-fe600823b0e029bf5e4c4dbf7dafd426b17456d1a865db752307c0ce47beb6f0
     * used with permission
     */
    @ModifyExpressionValue(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean isBowItem(boolean originalResult, @Local ItemStack useStack) {
        return originalResult || useStack.is(BotaniaItems.livingwoodBow) || useStack.is(BotaniaItems.crystalBow);
    }
}