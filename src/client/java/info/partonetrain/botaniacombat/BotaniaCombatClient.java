package info.partonetrain.botaniacombat;

import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.player.LocalPlayer;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

public class BotaniaCombatClient implements ClientModInitializer {



	@Override
	public void onInitializeClient() {
		if (BotaniaCombat.BetterCombatInstalled){
			BetterCombatClientEvents.ATTACK_START.register(this::CheckTerrasteelSwing);
			BotaniaCombat.LOGGER.info("BotaniaCombat Terrasteel ATTACK_START registered");
		}else{
			BotaniaCombat.LOGGER.info("BetterCombat not found, ignoring ATTACK_START code.");
		}

		ColorHandler.submitBotaniaCombatItems(ColorProviderRegistry.ITEM::register);

	}

	public void CheckTerrasteelSwing(LocalPlayer clientPlayerEntity, AttackHand attackHand){
		if(attackHand.itemStack().getItem() instanceof TerrasteelWeaponItem){
			TerrasteelWeaponItem.leftClick(attackHand.itemStack());
		} else if(attackHand.itemStack().getItem() instanceof TerraBladeItem){
			TerraBladeItem.leftClick(attackHand.itemStack()); //the botania weapon
		}else if(attackHand.itemStack().getItem() instanceof GaiaGreatswordItem){
			GaiaGreatswordItem.leftClick(attackHand.itemStack());
		}
	}

}