package info.partonetrain.botaniacombat;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldSetModelCallback;
import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import info.partonetrain.botaniacombat.registry.BotaniaCombatItems;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

import static com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient.renderBanner;

public class BotaniaCombatClient implements ClientModInitializer {

	public static final ModelLayerLocation ELEMENTIUM_SHIELD_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(BotaniaCombat.MOD_ID, "elementium_banner_shield"),"main");

	public static ShieldModel modelElementiumShield;

	public static final Material ELEMENTIUM_BANNER_SHIELD_BASE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base"));
	public static final Material ELEMENTIUM_BANNER_SHIELD_BASE_NO_PATTERN = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base_nopattern"));


	@Override
	public void onInitializeClient() {
		ColorHandler.submitBotaniaCombatItems(ColorProviderRegistry.ITEM::register);

		if (BotaniaCombat.BetterCombatInstalled){
			BetterCombatClientEvents.ATTACK_START.register(this::CheckTerrasteelSwing);
			BotaniaCombat.LOGGER.info("BotaniaCombat Terrasteel ATTACK_START registered for BetterCombat");
		}else{
			BotaniaCombat.LOGGER.info("BetterCombat not found, ignoring ATTACK_START code.");
		}

		if(BotaniaCombat.FabricShieldLibInstalled){
			EntityModelLayerRegistry.registerModelLayer(ELEMENTIUM_SHIELD_MODEL_LAYER, ShieldModel::createLayer);

			ShieldSetModelCallback.EVENT.register((loader) -> {
				modelElementiumShield = new ShieldModel(loader.bakeLayer(ELEMENTIUM_SHIELD_MODEL_LAYER));
				return InteractionResult.PASS;
			});

			BuiltinItemRendererRegistry.INSTANCE.register(BotaniaCombatItems.items.get("elementium_banner_shield"), (stack, mode, matrices, vertexConsumers, light, overlay) -> {
				renderBanner(stack, matrices, vertexConsumers, light, overlay, modelElementiumShield, ELEMENTIUM_BANNER_SHIELD_BASE, ELEMENTIUM_BANNER_SHIELD_BASE_NO_PATTERN);
				//The first five parameters are taken from the method, while the last 3 you provide yourself. You will provide the model, and then your 2 sprite identifiers in the order of SHIELD_NAME_BASE and then SHIELD_NAME_BASE_NOPATTERN.
			});
		}
	}

	public void CheckTerrasteelSwing(LocalPlayer clientPlayerEntity, AttackHand attackHand){
		if(attackHand.itemStack().getItem() instanceof TerrasteelWeaponItem){
			TerrasteelWeaponItem.leftClick(attackHand.itemStack());
		} else if(attackHand.itemStack().getItem() instanceof TerraBladeItem){
			TerraBladeItem.leftClick(attackHand.itemStack()); //the botania weapon
		} else if(attackHand.itemStack().getItem() instanceof GaiaGreatswordItem){
			GaiaGreatswordItem.leftClick(attackHand.itemStack());
		}
	}

}