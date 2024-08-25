package info.partonetrain.botaniacombat.mixin.nerf;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.item.equipment.tool.SoulscribeItem;

@Mixin(SoulscribeItem.class)
public class SoulscribeItemMixin {
    @ModifyArgs(method= "<init>(Lnet/minecraft/world/item/Item$Properties;)V", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/item/equipment/tool/manasteel/ManasteelSwordItem;<init>(Lnet/minecraft/world/item/Tier;IFLnet/minecraft/world/item/Item$Properties;)V"))
    private static void botaniacombat$conformSoulscribe(Args args){
        BotaniaNerfConfiguredValues.readFromFileDirectly();
        if(BotaniaNerfConfiguredValues.conformSoulscribe){
            args.set(1, (int)args.get(1) + BotaniaNerfConfiguredValues.daggerDamageModifier);
            args.set(2, BotaniaNerfConfiguredValues.daggerSpeed);
            BotaniaCombat.LOGGER.info("BotaniaCombat conformed Soulscribe to dagger stats");
        }
    }
}
