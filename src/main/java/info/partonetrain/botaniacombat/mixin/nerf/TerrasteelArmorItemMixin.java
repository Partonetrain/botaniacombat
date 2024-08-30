package info.partonetrain.botaniacombat.mixin.nerf;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.llamalad7.mixinextras.sugar.Local;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.equipment.armor.terrasteel.TerrasteelArmorItem;

import java.util.UUID;

@Mixin(TerrasteelArmorItem.class)
public class TerrasteelArmorItemMixin {
    @Inject(
            method = "getDefaultAttributeModifiers(Lnet/minecraft/world/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;",
            at = @At("TAIL"),
            cancellable = true)
    private void botaniacombat$addSlowAttributeToArmor(@NotNull EquipmentSlot slot, CallbackInfoReturnable<@NotNull Multimap<Attribute, AttributeModifier>> cir, @Local Multimap<Attribute, AttributeModifier> ret) {
        if (BotaniaNerfConfiguredValues.slowTerraArmor){
            TerrasteelArmorItem thisArmor = (TerrasteelArmorItem) (Object) this;
            ArmorItem.Type type = thisArmor.getType();
            String modName = "Terrasteel nerf " + type;
            UUID uuid = UUID.nameUUIDFromBytes(modName.getBytes());
            if (slot == type.getSlot()) {
                ret = HashMultimap.create(ret);
                AttributeModifier slow = new AttributeModifier(uuid,"Terrasteel nerf " + type, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
                ret.put(Attributes.MOVEMENT_SPEED, slow);
            }
            cir.setReturnValue(ret);
        }
    }
}
