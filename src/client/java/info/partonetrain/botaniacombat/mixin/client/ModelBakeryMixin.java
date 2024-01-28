package info.partonetrain.botaniacombat.mixin.client;


import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

	@Shadow
	protected abstract void loadTopLevel(ModelResourceLocation modelId);

	@Inject(method = "<init>", at = @At(target = "Lnet/minecraft/client/resources/model/ModelBakery;loadTopLevel(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", value = "INVOKE",  ordinal = 3, shift = At.Shift.AFTER))

	public void addBotaniaCombatModels(BlockColors blockColors, ProfilerFiller profiler, Map<ResourceLocation, BlockModel> jsonUnbakedModels, Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStates, CallbackInfo ci) {

		this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "soulstaff_held", "inventory"));
		this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "elementium_spear_held", "inventory"));
		this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "terrasteel_spear_held", "inventory"));
		this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "gaia_greatsword_held", "inventory"));

		//this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "skadi_bow_held", "inventory"));
		//this.loadTopLevel(new ModelResourceLocation(BotaniaCombat.MOD_ID, "skadi_bow_pulling_0_held", "inventory"));

	}

}