package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import vazkii.botania.api.BotaniaRegistries;

public final class BotaniaCombatItems {
    private static final int DAGGER_DAMAGE_MODIFIER = -3; //ingame tier - 2
    private static final float DAGGER_SPEED = -2.0f; //ingame 2.25
    private static final int SLAUGHTERSAW_DAMAGE_MODIFIER = -5; //ingame tier - 4
    private static final float SLAUGHTERSAW_SPEED = -1.8f; //ingame 2.2
    private static final int SPEAR_DAMAGE_MODIFIER = -1; //ingame tier - 0
    private static final float SPEAR_SPEED = -2.8f; //ingame 1.2
    private static final int SOULSTAFF_DAMAGE_MODIFIER = -1; //ingame tier + 0 (6)
    private static final float SOULSTAFF_SPEED = -2.3f; //ingame 1.7
    private static final int GREATSWORD_DAMAGE_MODIFIER = 3;
    private static final float GREATSWORD_SPEED = -3.4f;
    private static final int MJOLNIR_DAMAGE_MODIFIER = 4; //ingame tier + 5 (12)
    private static final float MJOLNIR_SPEED = -2.8f; //ingame 1.2

    public static final Item.Properties ITEM_PROPERTIES = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric();
    public static final Item.Properties ITEM_PROPERTIES_RARE = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().fireResistant().rarity(Rarity.UNCOMMON);
    //we run defaultItemBuilderWithCustomDamageOnFabric again because using ITEM_PROPERTIES again here would modify it
    public static final Item.Properties ITEM_PROPERTIES_EPIC = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric().fireResistant().rarity(Rarity.EPIC);

    //manasteel dagger = botania soulscribe
    public static final SingleHandedElementiumWeaponItem ELEMENTIUM_DAGGER = register("elementium_dagger", new SingleHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, DAGGER_DAMAGE_MODIFIER, DAGGER_SPEED, ITEM_PROPERTIES));
    public static final TerrasteelWeaponItem TERRASTEEL_DAGGER = register("terrasteel_dagger", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, DAGGER_DAMAGE_MODIFIER, DAGGER_SPEED, ITEM_PROPERTIES_RARE));
    public static final SlaughtersawItem SLAUGHTERSAW = register("slaughtersaw", new SlaughtersawItem(BotaniaCombatTiers.MANASTEEL_TIER, SLAUGHTERSAW_DAMAGE_MODIFIER, SLAUGHTERSAW_SPEED, ITEM_PROPERTIES));

    public static final SoulstaffItem SOULSTAFF = register("soulstaff", new SoulstaffItem(BotaniaCombatTiers.MANASTEEL_TIER, SOULSTAFF_DAMAGE_MODIFIER, SOULSTAFF_SPEED, ITEM_PROPERTIES));
    public static final TwoHandedElementiumWeaponItem ELEMENTIUM_SPEAR = register("elementium_spear", new TwoHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, SPEAR_DAMAGE_MODIFIER, SPEAR_SPEED, ITEM_PROPERTIES));
    public static final TerrasteelWeaponItem TERRASTEEL_SPEAR = register("terrasteel_spear", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, SPEAR_DAMAGE_MODIFIER, SPEAR_SPEED, ITEM_PROPERTIES_RARE));
    public static final GaiaGreatswordItem GAIA_GREATSWORD = register("gaia_greatsword", new GaiaGreatswordItem(BotaniaCombatTiers.TERRASTEEL_TIER, GREATSWORD_DAMAGE_MODIFIER, GREATSWORD_SPEED, ITEM_PROPERTIES_RARE));
    public static final MjolnirItem MJOLNIR = register("mjolnir", new MjolnirItem(BotaniaCombatTiers.TERRASTEEL_TIER, MJOLNIR_DAMAGE_MODIFIER, MJOLNIR_SPEED, ITEM_PROPERTIES_EPIC));

    public static void init() {}

    public static <T extends Item> T register(String name, T item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, name), item);
        ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(item));

        return item;
    }
}
