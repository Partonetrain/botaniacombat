package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombatConfig;
import info.partonetrain.botaniacombat.item.ranged.CrystalCrossbowItem;
import info.partonetrain.botaniacombat.item.ranged.LivingwoodCrossbowItem;
import info.partonetrain.botaniacombat.item.ranged.SkadiBowItem;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import static info.partonetrain.botaniacombat.registry.BotaniaCombatItems.*;

public class BotaniaCombatRangedItems {
    public static BotaniaCombatConfig.RangedItemConfig config = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().rangedItemConfig;
    private static final RangedConfig LIVINGWOOD_CROSSBOW_CONFIG = new RangedConfig(config.livingwoodPullTime, config.crossbowDamage, null);
    private static final RangedConfig CRYSTAL_CROSSBOW_CONFIG = new RangedConfig(config.crystalPullTime, config.crossbowDamage, null);
    private static final RangedConfig SKADI_BOW_CONFIG = new RangedConfig(config.skadiPullTime, config.skadiDamage, 5f);

    public static final LivingwoodCrossbowItem LIVINGWOOD_CROSSBOW = register("livingwood_crossbow", new LivingwoodCrossbowItem(ITEM_PROPERTIES.defaultDurability(300), () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "livingwood_twig"))), LIVINGWOOD_CROSSBOW_CONFIG));
    public static final CrystalCrossbowItem CRYSTAL_CROSSBOW = register("crystal_crossbow", new CrystalCrossbowItem(ITEM_PROPERTIES.defaultDurability(500), () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "livingwood_twig"))), CRYSTAL_CROSSBOW_CONFIG));
    public static final SkadiBowItem SKADI_BOW = register("skadi_bow", new SkadiBowItem(ITEM_PROPERTIES_EPIC.defaultDurability(3200), () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "terrasteel_ingot"))), SKADI_BOW_CONFIG));

    public static void init() {}
}
