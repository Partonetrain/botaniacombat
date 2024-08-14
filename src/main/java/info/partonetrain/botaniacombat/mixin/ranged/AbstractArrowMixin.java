package info.partonetrain.botaniacombat.mixin.ranged;

import info.partonetrain.botaniacombat.ITickFreezeArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin implements ITickFreezeArrow {
    @Unique
    public int freezeTicks = 0;

    @Unique
    @Override
    public void botaniacombat$setFreezeTicks(int freezeTicks){
        this.freezeTicks = freezeTicks;
    }

    @Inject(method = "doPostHurtEffects", at = @At("HEAD"))
    public void botaniacombat_addFrozenTicks(LivingEntity target, CallbackInfo ci){
        target.setTicksFrozen(target.getTicksFrozen() + freezeTicks);
    }
}
