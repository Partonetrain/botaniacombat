package info.partonetrain.botaniacombat.item.ranged;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabric_extras.ranged_weapon.api.CustomCrossbow;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CrystalCrossbowItem extends CustomCrossbow implements CustomDamageItem {
    private static final int ARROW_COST = 200;
    public CrystalCrossbowItem(Properties properties, Supplier<Ingredient> repairIngredientSupplier, RangedConfig rangedConfig) {
        super(properties, repairIngredientSupplier);
        ((CustomRangedWeapon)this).configure(rangedConfig);
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack crossbowStack = player.getItemInHand(usedHand);
        //BotaniaCombat.LOGGER.info("canCharge: " + String.valueOf(CanChargeWithMana(crossbowStack, player)));
        if (isCharged(crossbowStack)) {
            float shootingPower = containsChargedProjectile(crossbowStack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
            performShooting(level, player, usedHand, crossbowStack, shootingPower, 0.5F);
            CrossbowItem.setCharged(crossbowStack, false);
            return InteractionResultHolder.consume(crossbowStack);
        } else if (CanChargeWithMana(crossbowStack, player)) {
            if (!isCharged(crossbowStack)) {
                player.startUsingItem(usedHand);
            }

            return InteractionResultHolder.consume(crossbowStack);
        } else {
            return InteractionResultHolder.fail(crossbowStack);
        }
    }

    @Override
    public void releaseUsing(@NotNull ItemStack crossbowStack, @NotNull Level worldIn, LivingEntity livingEntity, int timeCharged) {
        if(livingEntity instanceof Player player) {
            int i = this.getUseDuration(crossbowStack) - timeCharged;
            float f = CrossbowItem.getPowerForTime(i, crossbowStack);
            if (f >= 1.0F && !isCharged(crossbowStack) && tryLoadProjectiles(livingEntity, crossbowStack)) {
                setCharged(crossbowStack, true);
                worldIn.playSound(player, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            }
        }
    }

    //modified vanilla method
    private static boolean tryLoadProjectiles(@NotNull LivingEntity itemUser, @NotNull ItemStack crossbowStack) {
        if(itemUser instanceof Player playerentity) {
            int multishotLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, crossbowStack);
            int arrowsToShoot = multishotLevel == 0 ? 1 : 3;
            boolean flag = CanChargeWithMana(crossbowStack, playerentity); //canCharge checks for creative mode
            ItemStack usedProjectileStack = null; //the stack that will be used to create the projectile entities
            ItemStack realProjectileStack = playerentity.getProjectile(crossbowStack); //the actual itemstack in the player's inventory (may be empty)
            ItemStack fakeProjectileStack = new ItemStack(Items.ARROW); //this will only be used if the arrow is made of mana

            if (flag) {
                if (realProjectileStack.isEmpty() || realProjectileStack.getItem() == Items.ARROW)
                {
                    usedProjectileStack = fakeProjectileStack; //at this point we know that we are loading with a "mana arrow", so use a dummy stack
                }
                else
                {
                    usedProjectileStack = realProjectileStack;
                }

                ItemStack multishotDummyAmmo = usedProjectileStack.copy();

                for (int i = 0; i < arrowsToShoot; ++i) {
                    if (i > 0) {
                        usedProjectileStack = multishotDummyAmmo.copy();
                    }

                    if (!CrossbowItem.loadProjectile(itemUser, crossbowStack, usedProjectileStack, i > 0, playerentity.getAbilities().instabuild)) {
                        return false;
                    }

                }
            }
            return true;
        }
        return false;
    }

    //modified vanilla method
    public static void performShooting(Level level, LivingEntity shooter, InteractionHand usedHand, ItemStack crossbowStack, float velocity, float inaccuracy) {
        List<ItemStack> list = CrossbowItem.getChargedProjectiles(crossbowStack);
        float[] soundPitches = CrossbowItem.getShotPitches(shooter.getRandom());

        for(int i = 0; i < list.size(); ++i) {
            ItemStack itemStack = list.get(i);
            boolean creativeMode = shooter instanceof Player && ((Player)shooter).getAbilities().instabuild;
            if (!itemStack.isEmpty()) {
                if (i == 0) {
                    shootProjectile(level, shooter, usedHand, crossbowStack, itemStack, soundPitches[i], creativeMode, velocity, inaccuracy, 0.0F);
                } else if (i == 1) {
                    shootProjectile(level, shooter, usedHand, crossbowStack, itemStack, soundPitches[i], creativeMode, velocity, inaccuracy, -15.0F);
                } else if (i == 2) {
                    shootProjectile(level, shooter, usedHand, crossbowStack, itemStack, soundPitches[i], creativeMode, velocity, inaccuracy, 15.0F);
                }
            }
        }

        CrossbowItem.onCrossbowShot(level, shooter, crossbowStack);
    }

    //modified vanilla method
    private static void shootProjectile(Level level, LivingEntity shooter, InteractionHand hand, ItemStack crossbowStack, ItemStack ammoStack, float soundPitch, boolean isCreativeMode, float velocity, float inaccuracy, float projectileAngle) {
        if (!level.isClientSide) {
            boolean isFirework = ammoStack.is(Items.FIREWORK_ROCKET);
            boolean isStandardArrow = ammoStack.is(Items.ARROW);
            Projectile projectile;
            if (isFirework) {
                projectile = new FireworkRocketEntity(level, ammoStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15000000596046448, shooter.getZ(), true);
            } else {
                projectile = CrossbowItem.getArrow(level, shooter, crossbowStack, ammoStack);
                if (isCreativeMode || projectileAngle != 0.0F || isStandardArrow) {
                    ((AbstractArrow)projectile).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }
            }

            if (shooter instanceof CrossbowAttackMob crossbowAttackMob) {
                crossbowAttackMob.shootCrossbowProjectile(crossbowAttackMob.getTarget(), crossbowStack, projectile, projectileAngle);
            } else {
                Vec3 vec3 = shooter.getUpVector(1.0F);
                Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(projectileAngle * 0.017453292F), vec3.x, vec3.y, vec3.z);
                Vec3 vec32 = shooter.getViewVector(1.0F);
                Vector3f vector3f = vec32.toVector3f().rotate(quaternionf);
                (projectile).shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), velocity, inaccuracy);
            }

            crossbowStack.hurtAndBreak(isFirework ? 3 : 1, shooter, (livingEntity) -> {
                livingEntity.broadcastBreakEvent(hand);
            });

            level.addFreshEntity(projectile);
            level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, soundPitch);
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        boolean infinity = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0; //archers mod makes this possible
        return ToolCommons.damageItemIfPossible(stack, amount, entity, ARROW_COST / (infinity ? 2 : 1));
    }

    private static boolean CanChargeWithMana(ItemStack stack, Player player) {
        boolean infinity = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
        return player.getAbilities().instabuild || ManaItemHandler.instance().requestManaExactForTool(stack, player, ARROW_COST / (infinity ? 2 : 1), false);
    }

}
