package info.partonetrain.botaniacombat;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class BotaniaCombatModelLoadingPlugin implements ModelLoadingPlugin {
    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(
                ModelConstants.SOULSTAFF_MODEL, ModelConstants.SOULSTAFF_MODEL_HELD,
                ModelConstants.ELEMENTIUM_SPEAR_MODEL, ModelConstants.ELEMENTIUM_SPEAR_MODEL_HELD,
                ModelConstants.TERRASPEAR_MODEL, ModelConstants.TERRASPEAR_MODEL_HELD,
                ModelConstants.GREATSWORD_MODEL, ModelConstants.GREATSWORD_HELD_MODEL,
                ModelConstants.SKADI_MODEL, ModelConstants.SKADI_HELD_MODEL);
    }

}
