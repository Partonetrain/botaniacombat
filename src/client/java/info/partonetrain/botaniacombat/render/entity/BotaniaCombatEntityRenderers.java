package info.partonetrain.botaniacombat.render.entity;

import info.partonetrain.botaniacombat.registry.BotaniaCombatBlockEntities;
import info.partonetrain.botaniacombat.render.block_entity.AngryBozuBlockEntityRenderer;
import vazkii.botania.client.render.entity.EntityRenderers;

public class BotaniaCombatEntityRenderers {
    public static void registerBlockEntityRenderers(EntityRenderers.BERConsumer consumer) {
        consumer.register(BotaniaCombatBlockEntities.ANGRY_BOZU, AngryBozuBlockEntityRenderer::new);
    }

}
