package info.partonetrain.botaniacombat.item.ranged;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SkadiBowItem extends CustomBow implements CustomDamageItem {
    RangedConfig rangedConfig;

    public SkadiBowItem(Properties settings, Supplier<Ingredient> repairIngredientSupplier, RangedConfig rangedConfig) {
        super(settings, repairIngredientSupplier);
        ((CustomRangedWeapon) this).configure(rangedConfig);
        this.rangedConfig = rangedConfig;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide() && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, BotaniaCombat.MANA_PER_DAMAGE_TERRA * 2, true)) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, BotaniaCombat.MANA_PER_DAMAGE_TERRA);
    }

    //vanilla copy
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0) { //builtin "InfiniBows"
            player.startUsingItem(usedHand);
        }

        boolean bl = !player.getProjectile(stack).isEmpty();

        if (!player.isCreative() && !bl) {
            return InteractionResultHolder.fail(stack);
        } else {
            player.startUsingItem(usedHand);
            return InteractionResultHolder.consume(stack);
        }
    }

    //vanilla copy
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {
        if (livingEntity instanceof Player player) {
            ItemStack originalItemStack = player.getProjectile(stack);
            ItemStack itemStack = originalItemStack.copy();

            if (itemStack.isEmpty()) {
                itemStack = new ItemStack(Items.ARROW);
            }

            boolean arrowWasFree = player.isCreative() || (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0) && itemStack.is(Items.ARROW);

            if (itemStack.is(Items.ARROW)) {
                itemStack = new ItemStack(Items.TIPPED_ARROW, 1);
                PotionUtils.setPotion(itemStack, Potions.STRONG_SLOWNESS);
            }

            int i = this.getUseDuration(stack) - timeCharged;
            float f = getPowerForTime(i);

            if (!(f < 0.1)) {
                if (!level.isClientSide) {
                    ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                    AbstractArrow abstractArrow = arrowItem.createArrow(level, itemStack, player);

                    abstractArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 0);
                    abstractArrow.setDeltaMovement(abstractArrow.getDeltaMovement().scale((rangedConfig.velocity() / 3.0F))); //set velocity using rangedConfig

                    if (f == 1.0F) {
                        abstractArrow.setCritArrow(true);
                    }

                    int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                    if (j > 0) {
                        abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + (double) j * 0.5 + 0.5);
                    }

                    int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                    if (k > 0) {
                        abstractArrow.setKnockback(k);
                    }

                    if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                        abstractArrow.setSecondsOnFire(100);
                    }

                    stack.hurtAndBreak(1, player, (player2) -> {
                        player2.broadcastBreakEvent(player.getUsedItemHand());
                    });

                    if (arrowWasFree) {
                        abstractArrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(abstractArrow);
                }

                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (!player.isCreative()) {
                    if (!arrowWasFree) {
                        originalItemStack.shrink(1);
                    }

                    if (originalItemStack.isEmpty()) {
                        player.getInventory().removeItem(originalItemStack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }
}
