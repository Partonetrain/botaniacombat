package info.partonetrain.botaniacombat;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldSetModelCallback;
import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import info.partonetrain.botaniacombat.network.StarcallerAttackHitHandler;
import info.partonetrain.botaniacombat.network.TerrasteelWeaponAttackHitHandler;
import info.partonetrain.botaniacombat.registry.BotaniaCombatShieldItems;
import info.partonetrain.botaniacombat.render.entity.BotaniaCombatEntityRenderers;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import vazkii.botania.common.item.equipment.tool.StarcallerItem;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;

import static com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient.renderBanner;

public class BotaniaCombatClient implements ClientModInitializer {
    public static final Material ELEMENTIUM_BANNER_SHIELD_BASE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base"));
    public static final Material ELEMENTIUM_BANNER_SHIELD_BASE_NO_PATTERN = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(BotaniaCombat.MOD_ID, "entity/elementium_banner_shield_base_nopattern"));
    public static final ModelLayerLocation ELEMENTIUM_SHIELD_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(BotaniaCombat.MOD_ID, "elementium_banner_shield"), "main");

    private static ShieldModel modelElementiumShield;

    @Override
    public void onInitializeClient() {
        ColorHandler.submitBotaniaCombatItems(ColorProviderRegistry.ITEM::register); //for colorchanging items
        this.registerOptionalResourcePack(); //for botaniacombatlang respack

        if (BotaniaCombat.BETTER_COMBAT_INSTALLED) {
            BetterCombatClientEvents.ATTACK_HIT.register(new TerrasteelWeaponAttackHitHandler());
            BetterCombatClientEvents.ATTACK_HIT.register(new StarcallerAttackHitHandler());
        }

        if (BotaniaCombat.FABRIC_SHIELD_LIB_INSTALLED) {
            BotaniaCombat.LOGGER.info("FabricShieldLib found, running client code");
            EntityModelLayerRegistry.registerModelLayer(ELEMENTIUM_SHIELD_MODEL_LAYER, ShieldModel::createLayer);

            ShieldSetModelCallback.EVENT.register((loader) -> {
                modelElementiumShield = new ShieldModel(loader.bakeLayer(ELEMENTIUM_SHIELD_MODEL_LAYER));
                return InteractionResult.PASS;
            });

            BuiltinItemRendererRegistry.INSTANCE.register(BotaniaCombatShieldItems.ELEMENTIUM_BANNER_SHIELD, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                renderBanner(stack, matrices, vertexConsumers, light, overlay, modelElementiumShield, ELEMENTIUM_BANNER_SHIELD_BASE, ELEMENTIUM_BANNER_SHIELD_BASE_NO_PATTERN);
            });
        }

        BotaniaCombatEntityRenderers.init(BlockEntityRenderers::register);
        for (var pair : BotaniaCombatEntityRenderers.BLOCKENTITY_ITEM_RENDERER_FACTORIES.entrySet()) {
            var block = pair.getKey();
            var renderer = pair.getValue().apply(block);
            BuiltinItemRendererRegistry.INSTANCE.register(block, renderer::render);
        }
    }

    public void registerOptionalResourcePack() {
        ResourceLocation folderName = new ResourceLocation(BotaniaCombat.MOD_ID, "botaniacombatlang");
        Component displayName = Component.literal("BotaniaCombat Lang Fixes");

        FabricLoader.getInstance().getModContainer(BotaniaCombat.MOD_ID).ifPresent(container -> ResourceManagerHelper.registerBuiltinResourcePack(folderName, container, displayName, ResourcePackActivationType.DEFAULT_ENABLED));
    }
}