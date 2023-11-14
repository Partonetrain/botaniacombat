package info.partonetrain.botaniacombat.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.registry.BotaniaCombatItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "render", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useHeldModels(BakedModel value, ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay){
        if (renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.FIXED) return value;
        boolean flag = false;
        String resourceLocation = "";
        Item item = stack.getItem();

        if(BotaniaCombatItems.items.containsValue(item)){
            flag = switch (item.getDescriptionId()){
                case "item.botaniacombat.soulstaff" -> {
                    resourceLocation = "soulstaff_held";
                    yield true;
                }
                case "item.botaniacombat.elementium_spear" -> {
                    resourceLocation = "elementium_spear_held";
                    yield true;
                }
                case "item.botaniacombat.terrasteel_spear" -> {
                    resourceLocation = "terrasteel_spear_held";
                    yield true;
                }
                case "item.botaniacombat.gaia_greatsword" -> {
                    resourceLocation = "gaia_greatsword_held";
                    yield true;
                }
                default -> false;
            };
        }

        if(flag) {
            return ((ItemRendererAccessor) this).BotaniaCombat$getModels() .getModelManager().getModel(new ModelResourceLocation(BotaniaCombat.MOD_ID, resourceLocation, "inventory"));
        }
        else {
            return value;
        }

    }


}


