package info.partonetrain.botaniacombat.mixin.ranged;

import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.fabric_extras.ranged_weapon.api.EntityAttributes_RangedWeapon;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.ManaBlasterItem;

import java.util.UUID;

@Mixin(ManaBlasterItem.class)
public abstract class ManaBlasterMixin extends Item {
    @Unique
    float damage;
    @Unique
    int modifiedCooldown = 30;
    @Unique
    AttributeModifier mod1, mod2;

    public ManaBlasterMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void botaniacombat$setDamageLensNBT(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci){
        if(ManaBlasterItem.getLens(stack).is(BotaniaItems.lensDamage)){
            damage = BotaniaNerfConfiguredValues.dmgLensDamage;
            mod1 = new AttributeModifier(UUID.fromString("60dfc4ff-de55-4f4f-8b4b-a7748c26ec4d"), "Blaster mainhand damage", damage, AttributeModifier.Operation.ADDITION);
            mod2 = new AttributeModifier(UUID.fromString("2c9b7180-d04f-4b1f-9574-174969759856"), "Blaster offhand damage", damage, AttributeModifier.Operation.ADDITION);
            if(!stack.getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(EntityAttributes_RangedWeapon.DAMAGE.attribute)){
                stack.addAttributeModifier(EntityAttributes_RangedWeapon.DAMAGE.attribute, mod1, EquipmentSlot.MAINHAND);
                stack.addAttributeModifier(EntityAttributes_RangedWeapon.DAMAGE.attribute, mod2, EquipmentSlot.OFFHAND);
            }
        }else{
            if(stack.getTag() != null){
                stack.getTag().remove("AttributeModifiers");
            }
        }
    }

    //haste disabler - use player ranged speed attribute instead
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSecondaryUseActive()Z"))
    public void botaniacombat$useRangedWeaponHaste(Level world, Player player, @NotNull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        ItemStack stack = player.getItemInHand(hand);
        if(ManaBlasterItem.getLens(stack).is(BotaniaItems.lensDamage) && !player.level().isClientSide()) { //only relevant to damaging lens
            modifiedCooldown = 30; //default cooldown
            double attributeValue = player.getAttributeValue(EntityAttributes_RangedWeapon.HASTE.attribute); //defaults to 100, increases with bonuses.
            double cooldownMultiplier = (100 - attributeValue) / 100;
            modifiedCooldown = (int) (modifiedCooldown + (modifiedCooldown * cooldownMultiplier)); //int is a primitive type, so we can't use effCd local var, because it wouldn't be propgated
        }
    }

    @ModifyArg(method = "use", at= @At(value = "INVOKE", target = "Lvazkii/botania/common/item/ManaBlasterItem;setCooldown(Lnet/minecraft/world/item/ItemStack;I)V"), index = 1)
    public int botaniacombat$useModifiedCooldown(int cooldown){
        BotaniaCombat.LOGGER.info(String.valueOf(modifiedCooldown));
        return modifiedCooldown;
    }

    @Inject(method = "getBarColor", at=@At(value = "HEAD"), cancellable = true)
    public void dontCrash(ItemStack stack, CallbackInfoReturnable<Integer> cir){
        int cd = stack.getOrCreateTag().getInt("cooldown");
        try{
            cir.setReturnValue(Mth.hsvToRgb((1 - cd / (float) 30) / 3.0F, 1.0F, 1.0F));
        }
        catch (RuntimeException e){
            BotaniaCombat.LOGGER.error(e.getMessage());
            cir.setReturnValue(Mth.hsvToRgb(1, 1, 1));
        }
    }

}
