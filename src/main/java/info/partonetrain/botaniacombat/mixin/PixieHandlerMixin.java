package info.partonetrain.botaniacombat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.handler.PixieHandler;

@Mixin(PixieHandler.class)
public class PixieHandlerMixin {

    @ModifyArg(
            method = "onDamageTaken",
            at = @At(value = "INVOKE", target = "Lvazkii/botania/common/entity/PixieEntity;setProps(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;IF)V")
    )
    private static float BotaniaCombat_BuffPixieDamage(float dmg){
        return 6;
    }
}
