package info.partonetrain.botaniacombat.mixin.client;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.ItemModelShaper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class)
public interface ItemRendererAccessor {
    @Accessor("itemModelShaper")
    public ItemModelShaper BotaniaCombat$getModels();
}