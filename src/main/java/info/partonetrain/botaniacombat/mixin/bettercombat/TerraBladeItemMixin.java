package info.partonetrain.botaniacombat.mixin.bettercombat;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.PsiContributorColors;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin {
    @Inject(
            method = "trySpawnBurst(Lnet/minecraft/world/entity/player/Player;F)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void botaniacombat$addOffhandBurst(Player player, float attackStrength, CallbackInfo ci) {
        if (!player.isSpectator()
                && !player.getOffhandItem().isEmpty()
                && player.getOffhandItem().is(BotaniaItems.terraSword)
                && attackStrength == 1) {
            ManaBurstEntity burst = TerraBladeItem.getBurst(player, player.getOffhandItem());
            player.level().addFreshEntity(burst);
            player.getOffhandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.OFF_HAND));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            ci.cancel(); //cancel the rest of the method so it doesn't check the main hand if there was an offhand burst.
        }
    }

    @Inject(method = "getBurst", at = @At("TAIL"))
    private static void botaniacombat$makeManaBeamPatronColors(Player player, ItemStack stack, CallbackInfoReturnable<ManaBurstEntity> cir, @Local ManaBurstEntity burst) {
        if (PsiContributorColors.isContributor(player.getName().getString().toLowerCase())) {
            int[] colors = PsiContributorColors.getColors(player.getName().getString().toLowerCase());
            int random = player.level().getRandom().nextInt(colors.length);
            burst.setColor(colors[random]);
        }
    }
}
