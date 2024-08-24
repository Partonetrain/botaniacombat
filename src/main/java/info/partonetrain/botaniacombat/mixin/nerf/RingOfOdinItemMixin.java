package info.partonetrain.botaniacombat.mixin.nerf;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.relic.RingOfOdinItem;

import static vazkii.botania.common.item.equipment.bauble.BaubleItem.getBaubleUUID;

@Mixin(RingOfOdinItem.class)
public class RingOfOdinItemMixin {
    @Inject(method = "getEquippedAttributeModifiers", at = @At("HEAD"), cancellable = true)
    public void botaniacombat_nerfAttributeModifiers(ItemStack stack, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir){
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.MAX_HEALTH,
                new AttributeModifier(getBaubleUUID(stack), "Odin Ring", BotaniaNerfConfiguredValues.odinHealthMod, BotaniaNerfConfiguredValues.odinHealthOperation));
        cir.setReturnValue(attributes);
        cir.cancel();
    }
}
