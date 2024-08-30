package info.partonetrain.botaniacombat.mixin.bettercombat;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import info.partonetrain.botaniacombat.ITerrasteelWeapon;
import info.partonetrain.botaniacombat.PsiContributorColors;
import net.bettercombat.api.AttackHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

@Mixin(TerraBladeItem.class)
public class TerraBladeItemMixin implements ITerrasteelWeapon {
    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    private static void botaniacombat$cancelAttackEntity(Player player, Level world, InteractionHand hand, Entity target, EntityHitResult hit, CallbackInfoReturnable<InteractionResult> cir){
        cir.setReturnValue(InteractionResult.PASS);
        cir.cancel();
    }

    @Override
    public void botaniacombat$spawnBeamBetterCombat(ItemStack stack, Level level, Player player, InteractionHand interactionHand) {
        if(ManaItemHandler.INSTANCE.requestManaExactForTool(new ItemStack(BotaniaItems.terraSword), player, BotaniaNerfConfiguredValues.terrasteelBeamCost, true)) {
            float attackStrength = player.getAttackStrengthScale(0F);
            if (!player.isSpectator()
                    && stack.getItem() instanceof ITerrasteelWeapon
                    && attackStrength == 1) {
                ManaBurstEntity burst = TerraBladeItem.getBurst(player, stack);
                player.level().addFreshEntity(burst);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(interactionHand));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            }
        }
    }
}
