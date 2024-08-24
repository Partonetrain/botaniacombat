package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.IStarcaller;
import info.partonetrain.botaniacombat.ITerrasteelWeapon;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BotaniaCombatNetworking {
    public static void initBetterCombat(){
        ServerPlayNetworking.registerGlobalReceiver(BotaniaCombat.TERRASTEEL_WEAPON_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean offhand = buf.getBoolean(0);
            server.execute(()->{
                ItemStack stack = offhand ? player.getOffhandItem(): player.getMainHandItem();
                Item item = stack.getItem();
                ITerrasteelWeapon terraWeapon = (ITerrasteelWeapon)item;
                terraWeapon.botaniacombat$summonBeamBetterCombat(stack, player.level(), player, offhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(BotaniaCombat.STARCALLER_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean offhand = buf.getBoolean(0);
            server.execute(()->{
                ItemStack stack = offhand ? player.getOffhandItem(): player.getMainHandItem();
                Item item = stack.getItem();
                IStarcaller starcaller = (IStarcaller)item;
                starcaller.botaniacombat$summonStarBetterCombat(stack, player.level(), player, offhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            });
        });
    }
}
