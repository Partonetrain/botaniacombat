package info.partonetrain.botaniacombat.render.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.block.block_entity.AngryBozuBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.BotaniaModelLayers;
import vazkii.botania.client.model.TeruTeruBozuModel;
import vazkii.botania.client.render.block_entity.TeruTeruBozuBlockEntityRenderer;
import vazkii.botania.common.helper.VecHelper;

import java.util.Random;

import static vazkii.botania.client.core.handler.ClientTickHandler.partialTicks;

public class AngryBozuBlockEntityRenderer implements BlockEntityRenderer<AngryBozuBlockEntity> {

    private static final ResourceLocation texture = new ResourceLocation(BotaniaCombat.MOD_ID, "textures/model/angry_bozu.png");
    private final TeruTeruBozuModel model;

    public AngryBozuBlockEntityRenderer(BlockEntityRendererProvider.Context ctx){
        model = new TeruTeruBozuModel(ctx.bakeLayer(BotaniaModelLayers.TERU_TERU_BOZU));
    }

    @Override
    public void render(AngryBozuBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.mulPose(VecHelper.rotateX(180));
        double time = ClientTickHandler.ticksInGame + partialTicks;
        boolean hasWorld = blockEntity != null && blockEntity.getLevel() != null;
        poseStack.translate(0.5F, -1.5F, -0.5F);
        if (hasWorld) {
            poseStack.mulPose(VecHelper.rotateY((float) (time * 0.3)));
            float rotZ = 4F * (float) Math.sin(time * 0.05F);
            //"rage"
            if(time % 10 == 0 || time % 10 == 2 || time % 10 == 4){
                rotZ -= 1;
            }else if(time % 10 == 1 || time % 10 == 3 || time % 10 == 5){
                rotZ += 1;
            }
            poseStack.mulPose(VecHelper.rotateZ(rotZ));
        }

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(texture));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1, 1, 1, 1);
        poseStack.popPose();
    }
}
