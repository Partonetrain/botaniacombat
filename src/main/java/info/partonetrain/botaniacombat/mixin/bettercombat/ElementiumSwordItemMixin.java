package info.partonetrain.botaniacombat.mixin.bettercombat;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumSwordItem;

@Mixin(ElementiumSwordItem.class)
public class ElementiumSwordItemMixin {
    @Inject(
            method = "getDefaultAttributeModifiers(Lnet/minecraft/world/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;",
            at = @At("TAIL")
    )
    private void botaniacombat$addOffhandAttributeToElementiumSword(@NotNull EquipmentSlot slot, CallbackInfoReturnable<@NotNull Multimap<Attribute, AttributeModifier>> cir, @Local Multimap<Attribute, AttributeModifier> ret) {
        if (slot == EquipmentSlot.OFFHAND) {
            ret = HashMultimap.create(ret);
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Sword modifier", 0.05));
        }
    }
}
