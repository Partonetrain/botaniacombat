package info.partonetrain.botaniacombat;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class BotaniaCombatModelLoadingPlugin implements ModelLoadingPlugin {
    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(ModelConstants.GREATSWORD_MODEL, ModelConstants.GREATSWORD_HELD_MODEL,
                ModelConstants.SKADI_MODEL, ModelConstants.SKADI_HELD_MODEL);
    }
}
