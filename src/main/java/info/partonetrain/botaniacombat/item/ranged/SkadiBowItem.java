package info.partonetrain.botaniacombat.item.ranged;

import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class SkadiBowItem extends BowItem {
    public SkadiBowItem(Properties properties) {
        super(properties);
    }

    /*
    public SkadiBowItem(Properties settings, Supplier<Ingredient> repairIngredientSupplier, RangedConfig rangedConfig) {

        super(settings, repairIngredientSupplier);
        ((CustomRangedWeapon)this).configure(rangedConfig);
    }
    */


}
