package info.partonetrain.botaniacombat;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import info.partonetrain.botaniacombat.item.shield.ElementiumBannerShieldItem;
import info.partonetrain.botaniacombat.registry.BotaniaCombatItems;
import info.partonetrain.botaniacombat.registry.BotaniaCombatRangedItems;
import info.partonetrain.botaniacombat.registry.BotaniaCombatShieldItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotaniaCombat implements ModInitializer {
    public static final String MOD_ID = "botaniacombat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final boolean BETTER_COMBAT_INSTALLED = FabricLoader.getInstance().isModLoaded("bettercombat");
    public static final boolean FABRIC_SHIELD_LIB_INSTALLED = FabricLoader.getInstance().isModLoaded("fabricshieldlib");
    public static final boolean RANGED_WEAPON_API_INSTALLED = FabricLoader.getInstance().isModLoaded("ranged_weapon_api");

    public static final int MANA_PER_DAMAGE = 60;
    public static final int MANA_PER_DAMAGE_TERRA = 100;

    @Override
    public void onInitialize() {
        BotaniaCombatItems.init();

        AttackEntityCallback.EVENT.register(TerrasteelWeaponItem::attackEntity); //fabric events for if BetterCombat is not installed
        AttackEntityCallback.EVENT.register(GaiaGreatswordItem::attackEntity);

        if (FABRIC_SHIELD_LIB_INSTALLED) {
            BotaniaCombatShieldItems.init();
            ShieldBlockCallback.EVENT.register(ElementiumBannerShieldItem::BlockAttack); //register summon pixie event to FSL event
        }

        if (RANGED_WEAPON_API_INSTALLED) {
            BotaniaCombatRangedItems.init();
        }

        LOGGER.info("BotaniaCombat initialized");
        PsiContributorColors.get();
    }
}