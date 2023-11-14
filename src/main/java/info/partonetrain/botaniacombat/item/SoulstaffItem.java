/*
 * This class uses code from the Botania Mod by Vazkii.
 * The relevant source code is here:
 * https://github.com/VazkiiMods/Botania/blob/1.20.x/Xplat/src/main/java/vazkii/botania/common/item/equipment/tool/SoulscribeItem.java
 *
 */

package info.partonetrain.botaniacombat.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SoulstaffItem extends BotaniaCombatWeaponItem {
    public SoulstaffItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Properties properties) {
        super(mat, attackDamageFromWeaponType, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        if (!target.level().isClientSide
                && target instanceof EnderMan
                && attacker instanceof Player player) {
            target.hurt(player.damageSources().playerAttack(player), 20);
        }

        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity player, int slot, boolean selected) {}

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return amount;
    }
}
