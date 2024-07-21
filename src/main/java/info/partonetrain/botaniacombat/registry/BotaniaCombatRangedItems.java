package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombatConfig;
import info.partonetrain.botaniacombat.item.BotaniaCombatItemProperties;
import info.partonetrain.botaniacombat.item.ranged.CrystalCrossbowItem;
import info.partonetrain.botaniacombat.item.ranged.LivingwoodCrossbowItem;
import info.partonetrain.botaniacombat.item.ranged.SkadiBowItem;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

import static info.partonetrain.botaniacombat.registry.BotaniaCombatItems.*;

public class BotaniaCombatRangedItems {
    public static final Item.Properties ITEM_PROPERTIES_LIVINGWOOD =  BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().defaultDurability(300);
    public static final Item.Properties ITEM_PROPERTIES_CRYSTAL =  BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().defaultDurability(500);
    public static final Item.Properties ITEM_PROPERTIES_EPIC =  BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().defaultDurability(3200).fireResistant().rarity(Rarity.EPIC);

    public static BotaniaCombatConfig.RangedItemConfig config = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().rangedItemConfig;
    private static final RangedConfig LIVINGWOOD_CROSSBOW_CONFIG = new RangedConfig(config.livingwoodPullTime, config.crossbowDamage, 0.0f);
    private static final RangedConfig CRYSTAL_CROSSBOW_CONFIG = new RangedConfig(config.crystalPullTime, config.crossbowDamage, 0.0f);
    private static final RangedConfig SKADI_BOW_CONFIG = new RangedConfig(config.skadiPullTime, config.skadiDamage, 5f);

    public static final LivingwoodCrossbowItem LIVINGWOOD_CROSSBOW = registerItem("livingwood_crossbow", new LivingwoodCrossbowItem(ITEM_PROPERTIES_LIVINGWOOD, () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "livingwood_twig"))), LIVINGWOOD_CROSSBOW_CONFIG));
    public static final CrystalCrossbowItem CRYSTAL_CROSSBOW = registerItem("crystal_crossbow", new CrystalCrossbowItem(ITEM_PROPERTIES_CRYSTAL, () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "livingwood_twig"))), CRYSTAL_CROSSBOW_CONFIG));
    public static final SkadiBowItem SKADI_BOW = registerItem("skadi_bow", new SkadiBowItem(ITEM_PROPERTIES_EPIC, () -> Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("botania", "terrasteel_ingot"))), SKADI_BOW_CONFIG));

    public static void init() {}
}
