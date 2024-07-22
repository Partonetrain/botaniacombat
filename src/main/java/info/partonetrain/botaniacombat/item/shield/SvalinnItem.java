package info.partonetrain.botaniacombat.item.shield;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.mana.ManaItemHandler;

public class SvalinnItem extends TerrasteelShieldItem {
    public SvalinnItem(Properties properties, int cooldownTicks, Tier tier) {
        super(properties, cooldownTicks, tier);
    }

    @Override
    public void inventoryTickExtra(ItemStack stack, Player player, boolean extraFlag) {
        if(player.getOffhandItem() == stack
                && (extraFlag || player.isOnFire())
                && !player.hasEffect(MobEffects.FIRE_RESISTANCE)
                && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true)){
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 150));
        }
    }

    @Override
    public void useExtra(LivingEntity living, Level world) {
        living.setSecondsOnFire(this.getSvalinnSecondsOnFire(world.getDayTime()));
    }

    @Override
    public void doFXExtra(ServerLevel ws, AABB aabb) {
        ws.sendParticles(ParticleTypes.FLAME, aabb.getCenter().x(), aabb.getCenter().y(), aabb.getCenter().z(), this.getSvalinnSecondsOnFire(ws.getDayTime()), 0, 0, 0, 0.25D);
    }

    private int getSvalinnSecondsOnFire(long dayTime) {
        dayTime %= 24000; //convert total ticks in world to ticks out of day

        if (dayTime <= 2000) {
            return 2;    //6:00 - 8:00
        }
        else if (dayTime <= 4000) {
            return 3;    //8:00 - 10:00
        }
        else if (dayTime <= 8000) {
            return 8;   //10:00 - 14:00
        }
        else if (dayTime <= 12000) {
            return 4;   //18:00 - 22:00
        }
        else if (dayTime <= 16000) {
            return 1;   //20:00 - 24:00
        }
        else if (dayTime <= 20000) {
            return 0;   //24:00 - 4:00
        }
        else if (dayTime <= 24000) {
            return 1;   //4:00 - 6:00
        }

        return 0;
    }
}
