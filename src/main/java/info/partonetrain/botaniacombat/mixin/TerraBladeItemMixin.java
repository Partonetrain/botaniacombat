package info.partonetrain.botaniacombat.mixin;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.PsiContributorColors;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin {


    @Inject(
            method = "Lvazkii/botania/common/item/equipment/tool/terrasteel/TerraBladeItem;trySpawnBurst(Lnet/minecraft/world/entity/player/Player;F)V",
            at = @At("HEAD"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private static void BotaniaCombat_AddOffhandBurst(Player player, float attackStrength, CallbackInfo ci) {
        if(BotaniaCombat.BetterCombatInstalled){
            if (!player.getOffhandItem().isEmpty()
                    && player.getOffhandItem().getItem() instanceof TerraBladeItem
                    && attackStrength == 1) {
                ManaBurstEntity burst = TerraBladeItem.getBurst(player, player.getOffhandItem());
                player.level().addFreshEntity(burst);
                player.getOffhandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.OFF_HAND));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
                ci.cancel(); //cancel the rest of the method so it doesn't check the main hand if there was an offhand burst.
            }
        }

    }

    @Inject(method = "getBurst", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void BotaniaCombat_makeManaBeamPatronColors(Player player, ItemStack stack, CallbackInfoReturnable<ManaBurstEntity> cir, ManaBurstEntity burst){
        if(PsiContributorColors.isContributor(player.getName().getString().toLowerCase())){
            int[] colors = PsiContributorColors.getColors(player.getName().getString().toLowerCase());
            int random = Mth.randomBetweenInclusive(player.level().random, 0, colors.length - 1);
            burst.setColor(colors[random]);
        }
    }

}
