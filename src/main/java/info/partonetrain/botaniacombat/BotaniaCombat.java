package info.partonetrain.botaniacombat;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import info.partonetrain.botaniacombat.item.shield.ElementiumBannerShieldItem;
import info.partonetrain.botaniacombat.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vazkii.botania.common.item.equipment.armor.terrasteel.TerrasteelArmorItem;

public class BotaniaCombat implements ModInitializer {
    public static final String MOD_ID = "botaniacombat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final boolean BETTER_COMBAT_INSTALLED = FabricLoader.getInstance().isModLoaded("bettercombat");
    public static final boolean FABRIC_SHIELD_LIB_INSTALLED = FabricLoader.getInstance().isModLoaded("fabricshieldlib");
    public static final boolean RANGED_WEAPON_API_INSTALLED = FabricLoader.getInstance().isModLoaded("ranged_weapon_api");
    public static final boolean FARMERS_DELIGHT_INSTALLED = FabricLoader.getInstance().isModLoaded("farmersdelight");
    public static final boolean ARCHERS_INSTALLED = FabricLoader.getInstance().isModLoaded("archers");
    public static final int MANA_PER_DAMAGE = 60;
    public static final int MANA_PER_DAMAGE_TERRA = 100;

    @Override
    public void onInitialize() {
        AutoConfig.register(BotaniaCombatConfig.class, JanksonConfigSerializer::new);
        BotaniaCombatItems.init();
        BotaniaNerfConfiguredValues.init(); //prevents loading issues; ensures autoconfig is registered before values are used

        if(!BETTER_COMBAT_INSTALLED){ //fabric events for vanilla combat
            AttackEntityCallback.EVENT.register(TerrasteelWeaponItem::attackEntity);
            AttackEntityCallback.EVENT.register(GaiaGreatswordItem::attackEntity);
        }

        if (FABRIC_SHIELD_LIB_INSTALLED) {
            BotaniaCombatShieldItems.init();
            ShieldBlockCallback.EVENT.register(ElementiumBannerShieldItem::BlockAttack); //register summon pixie event to FSL event
        }
        if (RANGED_WEAPON_API_INSTALLED) {
            BotaniaCombatRangedItems.init();
        }
        if (BETTER_COMBAT_INSTALLED){
            BotaniaCombatNetworking.initBetterCombat();
        }

        BotaniaCombatBlocks.init();
        BotaniaCombatBlockEntities.init();

        PsiContributorColors.get();

        LOGGER.info("BotaniaCombat initialized");
    }
}