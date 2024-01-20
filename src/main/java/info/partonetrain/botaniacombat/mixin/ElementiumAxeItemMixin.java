package info.partonetrain.botaniacombat.mixin;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.access.ElementiumAxeAccess;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.handler.PixieHandler;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumAxeItem;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumSwordItem;

@Mixin(ElementiumAxeItem.class)
public class ElementiumAxeItemMixin extends Item implements ElementiumAxeAccess {

    public ElementiumAxeItemMixin(Properties properties) {
        super(properties);
    }

    //Extremely cursed mixin to add an overridden method
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Local @NotNull EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> ret = HashMultimap.create(super.getDefaultAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Elementium Axe modifier", 0.05));
        }
        if (slot == EquipmentSlot.OFFHAND && BotaniaCombat.BetterCombatInstalled) {
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Elementium Axe modifier", 0.05));
        }
        return ret;
    }
}

