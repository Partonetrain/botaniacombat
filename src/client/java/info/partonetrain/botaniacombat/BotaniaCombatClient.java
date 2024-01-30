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
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

import static com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient.renderBanner;

public class BotaniaCombatClient implements ClientModInitializer {

	public static final ModelLayerLocation ELEMENTIUM_SHIELD_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(BotaniaCombat.MOD_ID, "elementium_banner_shield"),"main");

	public static ShieldModel modelElementiumShield;

	public static final Material ELEMENTIUM_BANNER_SHIELD_BASE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base"));
	public static final Material ELEMENTIUM_BANNER_SHIELD_BASE_NO_PATTERN = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base_nopattern"));

	public static final ResourceLocation SKADI_MODEL = new ResourceLocation(BotaniaCombat.MOD_ID, "item/skadi_bow");
	public static final ResourceLocation SKADI_HELD_MODEL = new ResourceLocation(BotaniaCombat.MOD_ID, "item/skadi_bow_held");
	public static final ResourceLocation GREATSWORD_MODEL = new ResourceLocation(BotaniaCombat.MOD_ID, "item/gaia_greatsword");
	public static final ResourceLocation GREATSWORD_HELD_MODEL = new ResourceLocation(BotaniaCombat.MOD_ID, "item/gaia_greatsword_held");
	public static final ResourceLocation TERRASPEAR_MODEL = new ResourceLocation(BotaniaCombat.MOD_ID, "item/terrasteel_spear");
	public static final ResourceLocation TERRASPEAR_MODEL_HELD = new ResourceLocation(BotaniaCombat.MOD_ID, "item/terrasteel_spear_held");

	@Override
	public void onInitializeClient() {
		ColorHandler.submitBotaniaCombatItems(ColorProviderRegistry.ITEM::register);
		registerOptionalResourcePack();

		BuiltinItemRendererRegistry.INSTANCE.register(BotaniaCombatItems.items.get("gaia_greatsword"),
				new DifferentPerspectiveItemRender(GREATSWORD_MODEL, GREATSWORD_HELD_MODEL));
		BuiltinItemRendererRegistry.INSTANCE.register(BotaniaCombatItems.items.get("terrasteel_spear"),
				new DifferentPerspectiveItemRender(TERRASPEAR_MODEL, TERRASPEAR_MODEL_HELD));

		if (BotaniaCombat.BetterCombatInstalled){
			BotaniaCombat.LOGGER.info("BetterCombat found, running client code");
			BetterCombatClientEvents.ATTACK_START.register(this::CheckTerrasteelSwing);
		}

		if(BotaniaCombat.FabricShieldLibInstalled){
			BotaniaCombat.LOGGER.info("FabricShieldLib found, running client code");
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

		if(BotaniaCombat.RangedWeaponAPIInstalled){
			BotaniaCombat.LOGGER.info("RangedWeaponAPI found, running client code");

			BuiltinItemRendererRegistry.INSTANCE.register(BotaniaCombatItems.items.get("skadi_bow"),
					new DifferentPerspectiveItemRender(SKADI_MODEL, SKADI_HELD_MODEL));
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

	public void registerOptionalResourcePack(){
		ResourceLocation folderName = new ResourceLocation(BotaniaCombat.MOD_ID, "botaniacombatlang");
		Component displayName = Component.literal("BotaniaCombat Lang Fixes");
		FabricLoader.getInstance().getModContainer(folderName.getNamespace()).ifPresent(c -> ResourceManagerHelper.registerBuiltinResourcePack(folderName, c, displayName, ResourcePackActivationType.DEFAULT_ENABLED));
	}


}