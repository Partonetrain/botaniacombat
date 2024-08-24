package info.partonetrain.botaniacombat.network;

import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.item.equipment.tool.StarcallerItem;

import java.util.List;

public class StarcallerAttackHitHandler implements BetterCombatClientEvents.PlayerAttackHit {
    @Override
    public void onPlayerAttackStart(LocalPlayer player, AttackHand attackHand, List<Entity> list, @Nullable Entity entity) {
        ItemStack stack = attackHand.isOffHand() ? player.getOffhandItem() : player.getMainHandItem();
        if (stack.isEmpty()) {
            return;
        }
        Item item = stack.getItem();
        if (item instanceof StarcallerItem) {
            ClientPlayNetworking.send(new StarcallerHitPacket(attackHand.isOffHand()));
        }
    }
}
