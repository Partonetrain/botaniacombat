package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaCombatConfig;
import info.partonetrain.botaniacombat.item.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import vazkii.botania.api.BotaniaRegistries;

public final class BotaniaCombatItems {
    public static BotaniaCombatConfig config = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig();

    public static final Item.Properties ITEM_PROPERTIES_MANASTEEL = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().defaultDurability(300);
    public static final Item.Properties ITEM_PROPERTIES_ELEMENTIUM = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric();
    //we run defaultItemBuilderWithCustomDamageOnFabric again because using ITEM_PROPERTIES_MANASTEEL again here would modify it
    public static final Item.Properties ITEM_PROPERTIES_TERRASTEEL = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().fireResistant().rarity(Rarity.UNCOMMON);
    public static final Item.Properties ITEM_PROPERTIES_EPIC = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().fireResistant().rarity(Rarity.EPIC);

    //manasteel dagger = botania soulscribe
    public static final SingleHandedElementiumWeaponItem ELEMENTIUM_DAGGER = registerItem("elementium_dagger", new SingleHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, config.daggerDamageModifier, config.daggerSpeed, ITEM_PROPERTIES_ELEMENTIUM));
    public static final TerrasteelWeaponItem TERRASTEEL_DAGGER = registerItem("terrasteel_dagger", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, config.daggerDamageModifier, config.daggerSpeed, ITEM_PROPERTIES_TERRASTEEL));
    public static final SlaughtersawItem SLAUGHTERSAW = registerItem("slaughtersaw", new SlaughtersawItem(config.slaughtersawDamageModifier, config.slaughtersawSpeed, BotaniaCombatTiers.MANASTEEL_TIER, ITEM_PROPERTIES_MANASTEEL));
    public static final SoulstaffItem SOULSTAFF = registerItem("soulstaff", new SoulstaffItem(BotaniaCombatTiers.MANASTEEL_TIER, config.soulstaffDamageModifier, config.soulstaffSpeed, ITEM_PROPERTIES_MANASTEEL));
    public static final TwoHandedElementiumWeaponItem ELEMENTIUM_SPEAR = registerItem("elementium_spear", new TwoHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, config.spearDamageModifier, config.spearSpeed, ITEM_PROPERTIES_ELEMENTIUM));
    public static final TerrasteelWeaponItem TERRASTEEL_SPEAR = registerItem("terrasteel_spear", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, config.spearDamageModifier, config.spearSpeed, ITEM_PROPERTIES_TERRASTEEL));
    public static final GaiaGreatswordItem GAIA_GREATSWORD = registerItem("gaia_greatsword", new GaiaGreatswordItem(BotaniaCombatTiers.TERRASTEEL_TIER, config.greatswordDamageModifier, config.greatswordSpeed, ITEM_PROPERTIES_TERRASTEEL));
    public static final MjolnirItem MJOLNIR = registerItem("mjolnir", new MjolnirItem(BotaniaCombatTiers.TERRASTEEL_TIER, config.mjolinirDamageModifier, config.mjolnirSpeed, ITEM_PROPERTIES_EPIC));

    public static void init() {}

    public static <T extends Item> T registerItem(String name, T item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, name), item);
        ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(item));

        return item;
    }
}
