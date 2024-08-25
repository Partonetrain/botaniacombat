package info.partonetrain.botaniacombat.mixin.ranged;

import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import net.fabric_extras.ranged_weapon.api.EntityAttributes_RangedWeapon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.ManaBlasterItem;

import java.util.UUID;

@Mixin(ManaBlasterItem.class)
public abstract class ManaBlasterMixin extends Item {
    @Unique
    float damage;
    @Unique
    AttributeModifier mod1, mod2;

    public ManaBlasterMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void botaniacombat$setDamageLensNBT(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci){
        if(ManaBlasterItem.getLens(stack).is(BotaniaItems.lensDamage)){
            damage = BotaniaNerfConfiguredValues.dmgLensDamage;
            mod1 = new AttributeModifier(UUID.fromString("60dfc4ff-de55-4f4f-8b4b-a7748c26ec4d"), "Blaster mainhand modifier", damage, AttributeModifier.Operation.ADDITION);
            mod2 = new AttributeModifier(UUID.fromString("2c9b7180-d04f-4b1f-9574-174969759856"), "Blaster offhand modifier", damage, AttributeModifier.Operation.ADDITION);
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

}
