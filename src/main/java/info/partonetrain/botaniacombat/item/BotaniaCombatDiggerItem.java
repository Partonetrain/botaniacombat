package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ManasteelSwordItem;

import java.util.function.Consumer;

public class BotaniaCombatDiggerItem extends DiggerItem implements CustomDamageItem {
    public BotaniaCombatDiggerItem(float attackDamageModifier, float attackSpeedModifier, Tier tier, TagKey<Block> mineableTag, Properties properties) {
        super(attackDamageModifier, attackSpeedModifier, tier, mineableTag ,properties);
    }

    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        int manaPerDamage = ((BotaniaCombatDiggerItem) stack.getItem()).getManaPerDamage();
        return ToolCommons.damageItemIfPossible(stack, amount, entity, manaPerDamage);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide() && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true)) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }
}
