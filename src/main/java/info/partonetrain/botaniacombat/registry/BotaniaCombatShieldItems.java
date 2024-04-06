package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.item.BotaniaCombatTiers;
import info.partonetrain.botaniacombat.item.shield.ElementiumBannerShieldItem;
import info.partonetrain.botaniacombat.item.shield.ManasteelShieldItem;
import info.partonetrain.botaniacombat.item.shield.SvalinnItem;
import info.partonetrain.botaniacombat.item.shield.TerrasteelShieldItem;

import static info.partonetrain.botaniacombat.registry.BotaniaCombatItems.*;

public class BotaniaCombatShieldItems {
    private static final int SHIELD_COOLDOWN_TICKS = 100; //equivalent to vanilla shield

    public static final ManasteelShieldItem MANASTEEL_SHIELD = register("manasteel_shield", new ManasteelShieldItem(ITEM_PROPERTIES_MANASTEEL, SHIELD_COOLDOWN_TICKS, BotaniaCombatTiers.MANASTEEL_TIER));
    public static final ElementiumBannerShieldItem ELEMENTIUM_BANNER_SHIELD = register("elementium_banner_shield", new ElementiumBannerShieldItem(ITEM_PROPERTIES_ELEMENTIUM, SHIELD_COOLDOWN_TICKS, BotaniaCombatTiers.ELEMENTIUM_TIER));
    public static final TerrasteelShieldItem TERRASTEEL_SHIELD = register("terrasteel_shield", new TerrasteelShieldItem(ITEM_PROPERTIES_TERRASTEEL, SHIELD_COOLDOWN_TICKS, BotaniaCombatTiers.TERRASTEEL_TIER));
    public static final SvalinnItem SVALINN = register("svalinn", new SvalinnItem(ITEM_PROPERTIES_EPIC, SHIELD_COOLDOWN_TICKS, BotaniaCombatTiers.TERRASTEEL_TIER));

    public static void init() {}
}
