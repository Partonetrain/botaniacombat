package info.partonetrain.botaniacombat.mixin;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumSwordItem;

@Mixin(ElementiumSwordItem.class)
public class ElementiumSwordItemMixin {

    @ModifyVariable(
            method = "Lvazkii/botania/common/item/equipment/tool/elementium/ElementiumSwordItem;getDefaultAttributeModifiers(Lnet/minecraft/world/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private Multimap<Attribute, AttributeModifier> BotaniaCombat_makeElementiumSwordAttributesMutable(Multimap<Attribute, AttributeModifier> ret) {
        return HashMultimap.create(ret);
    }

    @Inject(
            method = "Lvazkii/botania/common/item/equipment/tool/elementium/ElementiumSwordItem;getDefaultAttributeModifiers(Lnet/minecraft/world/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void BotaniaCombat_addOffhandAttributeToElementiumSword(@NotNull EquipmentSlot slot, CallbackInfoReturnable<@NotNull Multimap<Attribute, AttributeModifier>> cir, Multimap<Attribute, AttributeModifier> ret) {
        if (slot == EquipmentSlot.OFFHAND && BotaniaCombat.BetterCombatInstalled) {
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Elementium Sword modifier", 0.05));
        }
    }

}
