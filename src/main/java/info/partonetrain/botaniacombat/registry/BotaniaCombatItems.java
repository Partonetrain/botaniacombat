package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.*;

public final class BotaniaCombatItems {

    //these stats are based on BasicWeapons by June. attack_range is determined by weapon_attributes in data (also based on same mod)
    public static final int daggerDamageModifier = -3; //ingame tier - 2
    public static final float daggerSpeed = -2.0f; //ingame 2.25
    public static final int spearDamageModifier = -1; //ingame tier - 0
    public static final float spearSpeed = -2.8f; //ingame 1.2

    //These stats are for the original weapons
    public static final int soulstaffDamageModifier = -1; //ingame tier + 0 (6)
    public static final float soulstaffSpeed = -2.3f; //ingame 1.7
    public static final int greatswordDamageModifier = 3;
    public static final float greatswordSpeed = -3.4f;
    public static final int mjolnirDamageModifier = 4; //ingame tier + 5 (12)
    public static final float mjolnirSpeed = -2.8f; //ingame 1.2

    public static final int shieldCooldownTicks = 100; //equivalent to vanilla shield

    public static HashMap<String, Item> items = new LinkedHashMap<>() {};
    public static HashMap<String, Item> callbackItems = new LinkedHashMap<>() {}; //items that will be registered with the callback event instead of during init

    public static final Item.Properties itemProperties = BotaniaCombatItemProperties.defaultItemBuilderWithCustomDamageOnFabric();
    public static final Item.Properties itemPropertiesFireResist = itemProperties.fireResistant().rarity(Rarity.UNCOMMON);

    public static void Init(){

        //manasteel dagger = botania soulscribe
        items.put("elementium_dagger", new SingleHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, daggerDamageModifier, daggerSpeed, itemProperties));
        items.put("terrasteel_dagger", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, daggerDamageModifier, daggerSpeed, itemPropertiesFireResist));

        items.put("soulstaff", new SoulstaffItem(BotaniaCombatTiers.MANASTEEL_TIER, soulstaffDamageModifier, soulstaffSpeed, itemProperties));
        items.put("elementium_spear", new TwoHandedElementiumWeaponItem(BotaniaCombatTiers.ELEMENTIUM_TIER, spearDamageModifier, spearSpeed, itemProperties));
        items.put("terrasteel_spear", new TerrasteelWeaponItem(BotaniaCombatTiers.TERRASTEEL_TIER, spearDamageModifier, spearSpeed, itemPropertiesFireResist));

        items.put("gaia_greatsword", new GaiaGreatswordItem(BotaniaCombatTiers.TERRASTEEL_TIER, greatswordDamageModifier, greatswordSpeed, itemPropertiesFireResist));
        items.put("mjolnir", new MjolnirItem(BotaniaCombatTiers.TERRASTEEL_TIER, mjolnirDamageModifier, mjolnirSpeed, itemPropertiesFireResist.rarity(Rarity.EPIC)));

        if(BotaniaCombat.FabricShieldLibInstalled){
            items.put("manasteel_shield", new ManasteelShieldItem(itemProperties, shieldCooldownTicks, BotaniaCombatTiers.MANASTEEL_TIER));
            //items.put("elementium_banner_shield", new ManasteelShieldItem(itemProperties, shieldCooldownTicks, BotaniaCombatTiers.ELEMENTIUM_TIER));
        }

    }




}
