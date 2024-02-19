package info.partonetrain.botaniacombat.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;
import vazkii.botania.common.item.equipment.CustomDamageItem;

public final class BotaniaCombatItemProperties {
    // If/when this gets ported to forge:
    // Forge does custom damage by just implementing a method on Item,
    // Fabric does it by an extra lambda to the Properties object
    public static Item.Properties defaultItemBuilderWithCustomDamageOnFabric() {
        return new FabricItemSettings().customDamage((stack, amount, entity, breakCallback) -> {
            var item = stack.getItem();
            if (item instanceof CustomDamageItem cd) {
                return cd.damageItem(stack, amount, entity, breakCallback);
            }
            return amount;
        });
    }
}
