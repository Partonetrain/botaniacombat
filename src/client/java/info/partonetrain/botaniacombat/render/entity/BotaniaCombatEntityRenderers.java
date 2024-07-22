package info.partonetrain.botaniacombat.render.entity;

import info.partonetrain.botaniacombat.registry.BotaniaCombatBlockEntities;
import info.partonetrain.botaniacombat.registry.BotaniaCombatBlocks;
import info.partonetrain.botaniacombat.render.block_entity.AngryBozuBlockEntityRenderer;
import net.minecraft.world.level.block.Block;
import vazkii.botania.client.render.block_entity.PylonBlockEntityRenderer;
import vazkii.botania.client.render.block_entity.TEISR;
import vazkii.botania.client.render.entity.EntityRenderers;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.Map;
import java.util.function.Function;

public final class BotaniaCombatEntityRenderers {

    public static final Map<Block, Function<Block, TEISR>> BLOCKENTITY_ITEM_RENDERER_FACTORIES = Map.of(
            BotaniaCombatBlocks.angryBozu, TEISR::new //"tile entity item special renderer"
    );

    public static void init(EntityRenderers.BERConsumer berConsumer) {
        berConsumer.register(BotaniaCombatBlockEntities.ANGRY_BOZU, AngryBozuBlockEntityRenderer::new);
    };
}
