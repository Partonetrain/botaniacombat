package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.item.*;
import net.minecraft.world.item.Item;

import java.util.*;

import static info.partonetrain.botaniacombat.item.BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric;

public final class BotaniaCombatItems { //why can't this be static lol

    //these stats are based on BasicWeapons by June. attack_range is determined by weapon_attributes in data (also based on same mod)
    public static final int daggerDamageModifier = -3; //ingame tier - 2
    public static final float daggerSpeed = -2.0f; //ingame 2.25
    public static final int spearDamageModifier = -2; //ingame tier - 1
    public static final float spearSpeed = -2.8f; //ingame 1.2

    //These stats are for the original weapons
    public static final int soulstaffDamage = -1;
    public static final float soulstaffSpeed = -2.3f;
    public static final int greatswordDamageModifier = 3;
    public static final float greatswordSpeed = -3.4f;


    public static HashMap<String, Item> items = new LinkedHashMap<>() {};

    public static final Item.Properties itemProperties = defaultItemBuilderWithCustomDamageOnFabric();
    public static final Item.Properties itemPropertiesFireResist = itemProperties.fireResistant();

    public static void Init(){
        //manasteel dagger = botania soulscribe
        items.put("elementium_dagger", new SingleHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, daggerDamageModifier, daggerSpeed, itemProperties));
        items.put("terrasteel_dagger", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, daggerDamageModifier, daggerSpeed, itemPropertiesFireResist));

        items.put("soulstaff", new SoulstaffItem(BotaniaCombatTiers.MANASTEEL_TIER, soulstaffDamage, soulstaffSpeed, itemProperties));
        items.put("elementium_spear", new TwoHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, spearDamageModifier, spearSpeed, itemProperties));
        items.put("terrasteel_spear", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, spearDamageModifier, spearSpeed, itemPropertiesFireResist));

        //items.put("livingwood_crossbow", new LivingwoodCrossbowItem(itemProperties.defaultDurability(500)));
        //items.put("crystal_crossbow", new CrystalCrossbowItem(itemProperties.defaultDurability(500)));

        items.put("gaia_greatsword", new GaiaGreatswordItem(BotaniaCombatTiers.TERRASTEEL_TIER, greatswordDamageModifier, greatswordSpeed, itemPropertiesFireResist));
    }




}
