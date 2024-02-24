package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class SlaughtersawItem extends BotaniaCombatWeaponItem{

    public static final TagKey<EntityType<?>> ENTITY_TYPE_TAG = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(BotaniaCombat.MOD_ID, "meaty"));
    public SlaughtersawItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Properties properties) {
        super(mat, attackDamageFromWeaponType, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        float sharpnessBonus = (sharpness > 0 ? (0.5f * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack) + 0.5f) : 0);
        float damageAmount = 10.0f + sharpnessBonus;

        if (!target.level().isClientSide()
                && target.getType().is(ENTITY_TYPE_TAG)
                && attacker instanceof Player player) {
            target.setSilent(true);
            target.hurt(player.damageSources().playerAttack(player), damageAmount);
        }


        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        target.setSilent(false);
        return true;
    }

}
