package info.partonetrain.botaniacombat.mixin.shield;

import info.partonetrain.botaniacombat.item.shield.SvalinnItem;
import info.partonetrain.botaniacombat.registry.BotaniaCombatShieldItems;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "Lnet/minecraft/world/entity/player/Player;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("TAIL"))
    private void botaniacombat$svallinCatchFireDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        Player player = (Player) (Object) this;
        ItemStack offhand = player.getItemBySlot(EquipmentSlot.OFFHAND);
        if(source.is(DamageTypeTags.IS_FIRE) && offhand.is(BotaniaCombatShieldItems.SVALINN)){
            SvalinnItem svalinnItem = (SvalinnItem) player.getItemBySlot(EquipmentSlot.OFFHAND).getItem();
            svalinnItem.inventoryTickExtra(offhand, player, true); //activates fire resistance
        }
        //does not actually cancel damage! (intentional)
    }

}
