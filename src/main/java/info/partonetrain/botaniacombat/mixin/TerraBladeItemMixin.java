package info.partonetrain.botaniacombat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.PsiContributorColors;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin {
    @Inject(method = "getBurst", at = @At("TAIL"))
    private static void botaniacombat$makeManaBeamPatronColors(Player player, ItemStack stack, CallbackInfoReturnable<ManaBurstEntity> cir, @Local ManaBurstEntity burst) {
        if (PsiContributorColors.isContributor(player.getName().getString().toLowerCase())) {
            int[] colors = PsiContributorColors.getColors(player.getName().getString().toLowerCase());
            int random = player.level().getRandom().nextInt(colors.length);
            burst.setColor(colors[random]);
        }
    }
}
