package info.partonetrain.botaniacombat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import info.partonetrain.botaniacombat.mixin.client.access.ModelManagerAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class DifferentPerspectiveItemRender implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private final ResourceLocation guiModel;
    private final ResourceLocation heldModel;

    public DifferentPerspectiveItemRender(ResourceLocation guiModel, ResourceLocation heldModel){
        this.guiModel = guiModel;
        this.heldModel = heldModel;
    }

    @Override
    public void render(ItemStack stack, ItemDisplayContext transform, PoseStack matrixStack, MultiBufferSource buffer, int light, int overlay) {
        if (!stack.isEmpty()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

            matrixStack.pushPose();
            boolean gui = transform == ItemDisplayContext.GUI || transform == ItemDisplayContext.GROUND || transform == ItemDisplayContext.FIXED;

            BakedModel model;
            if (gui) {
                model = getModel(itemRenderer.getItemModelShaper().getModelManager(), guiModel);
            } else {
                model = getModel(itemRenderer.getItemModelShaper().getModelManager(), heldModel);
            }
            RenderType rendertype = ItemBlockRenderTypes.getRenderType(stack, true);
            VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, rendertype, true, stack.hasFoil());
            itemRenderer.renderModelLists(model, stack, light, overlay, matrixStack, vertexconsumer);
            matrixStack.popPose();
        }
    }

    public static BakedModel getModel(ModelManager modelManager, ResourceLocation modelLocation)
    {
        Map<ResourceLocation, BakedModel> bakedReg = ((ModelManagerAccessor)modelManager).getBakedRegistry();
        BakedModel model = bakedReg.get(modelLocation);
        BotaniaCombat.LOGGER.info("my model: "+String.valueOf(bakedReg.containsKey(modelLocation)));
        return model;
    }

}