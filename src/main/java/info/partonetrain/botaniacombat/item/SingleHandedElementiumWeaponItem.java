package info.partonetrain.botaniacombat.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.handler.PixieHandler;

public class SingleHandedElementiumWeaponItem extends BotaniaCombatWeaponItem {

    public SingleHandedElementiumWeaponItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Properties props) {
        super(mat, attackDamageFromWeaponType, attackSpeed, props);
    }

    @NotNull
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> ret = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.MAINHAND) {
            ret = HashMultimap.create(ret);
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Weapon modifier", 0.05));
        }

        if (slot == EquipmentSlot.OFFHAND && FabricLoader.getInstance().isModLoaded("bettercombat")) {
            ret = HashMultimap.create(ret);
            ret.put(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Weapon modifier", 0.05));
        }
        return ret;
    }

}
