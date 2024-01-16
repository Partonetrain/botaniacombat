package info.partonetrain.botaniacombat.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShield;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.UUID;
import java.util.function.Consumer;

public class ManasteelShieldItem extends FabricShieldItem implements CustomDamageItem, FabricShield {

    public final UUID ATTR_UUID = UUID.fromString("1b711943-4f29-43af-8d55-0b41d5983acf");
    public ManasteelShieldItem(Properties properties, int cooldownTicks, Tier tier) {
        super(properties, cooldownTicks, tier);
    }

    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && entity instanceof Player player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true)) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @NotNull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> ret = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.OFFHAND) {
            ret = HashMultimap.create(ret);
            ret.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTR_UUID, "Manasteel Shield modifier", 0.2, AttributeModifier.Operation.ADDITION));
        }

        return ret;
    }

}
