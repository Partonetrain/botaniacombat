package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.registry.BotaniaCombatItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlaughtersawItem extends BotaniaCombatDiggerItem{

    public static final TagKey<EntityType<?>> ENTITY_TYPE_TAG = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(BotaniaCombat.MOD_ID, "meaty"));
    public static final TagKey<Block> MINEABLE_TAG = TagKey.create(Registries.BLOCK, new ResourceLocation(BotaniaCombat.MOD_ID, "mineable/slaughtersaw"));

    static List<Enchantment> allowedEnchantments = new ArrayList<Enchantment>();
            
    public SlaughtersawItem(float attackDamageFromWeaponType, float attackSpeed, Tier tier, Properties properties) {
        super(attackDamageFromWeaponType, attackSpeed, tier, MINEABLE_TAG, properties);
        initAllowedEnchantments();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if(BotaniaCombatItems.config.slaughtersawRestricted && !target.getType().is(ENTITY_TYPE_TAG)){
            return false;
        }

        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        float sharpnessBonus = (sharpness > 0 ? (0.5f * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack) + 0.5f) : 0);
        float meatyDamageAmount = 10.5f + sharpnessBonus; //see issue #2

        if (!target.level().isClientSide()
                && target.getType().is(ENTITY_TYPE_TAG)
                && attacker instanceof Player player) {
            target.setSilent(true); //this is supposed to prevent the hurt and death sound, but only prevents the death sound?
            target.hurt(player.damageSources().playerAttack(player), meatyDamageAmount);
        }

        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        target.setSilent(false); //in case it had higher health than 10
        return true;
    }

    public static void initAllowedEnchantments() {
        for(Enchantment e : BuiltInRegistries.ENCHANTMENT){
            if(e.category == EnchantmentCategory.WEAPON && !(e.getDescriptionId().contains("sweeping"))){
                allowedEnchantments.add(e);
            }
        }

        if(BotaniaCombat.FARMERS_DELIGHT_INSTALLED){
            allowedEnchantments.add(BuiltInRegistries.ENCHANTMENT.get(
                    new ResourceLocation("farmersdelight", "backstabbing")));
        }
    }

    public static boolean isEnchantmentAllowed(Enchantment enchantment){
        return allowedEnchantments.contains(enchantment);
    }

}
