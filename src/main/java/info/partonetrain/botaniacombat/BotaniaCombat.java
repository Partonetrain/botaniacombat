package info.partonetrain.botaniacombat;

import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import info.partonetrain.botaniacombat.registry.BotaniaCombatRegistryCallback;
import info.partonetrain.botaniacombat.registry.BotaniaCombatRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotaniaCombat implements ModInitializer {
	public static final String MOD_ID = "botaniacombat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean BetterCombatInstalled;
	public static boolean FabricShieldLibInstalled;
	public static final int MANA_PER_DAMAGE = 60;
	public static final int MANA_PER_DAMAGE_TERRA = 100;

	@Override
	public void onInitialize() {
		BetterCombatInstalled = FabricLoader.getInstance().isModLoaded("bettercombat");
		FabricShieldLibInstalled = FabricLoader.getInstance().isModLoaded("fabricshieldlib");
		BotaniaCombatRegistry.init();
		BotaniaCombatRegistryCallback.init();

		AttackEntityCallback.EVENT.register(TerrasteelWeaponItem::attackEntity); //fabric events for if BetterCombat is not installed
		AttackEntityCallback.EVENT.register(GaiaGreatswordItem::attackEntity);
		LOGGER.info("BotaniaCombat initialized");
		PsiContributorColors.get();

	}

}