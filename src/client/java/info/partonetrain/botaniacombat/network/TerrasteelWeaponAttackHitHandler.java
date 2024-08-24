package info.partonetrain.botaniacombat.network;

import info.partonetrain.botaniacombat.ITerrasteelWeapon;
import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TerrasteelWeaponAttackHitHandler implements BetterCombatClientEvents.PlayerAttackHit{
    @Override
    public void onPlayerAttackStart(LocalPlayer localPlayer, AttackHand attackHand, List<Entity> list, @Nullable Entity entity) {
        ItemStack stack = attackHand.isOffHand() ? localPlayer.getOffhandItem() : localPlayer.getMainHandItem();
        if (stack.isEmpty()) {
            return;
        }
        Item item = stack.getItem();
        if (item instanceof ITerrasteelWeapon) {
            ClientPlayNetworking.send(new TerrasteelWeaponHitPacket(attackHand.isOffHand()));
        }
    }
}
