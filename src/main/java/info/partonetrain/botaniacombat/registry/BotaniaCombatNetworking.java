package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.IStarcaller;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BotaniaCombatNetworking {
    public static void init(){
        ServerPlayNetworking.registerGlobalReceiver(BotaniaCombat.STARCALLER_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean offhand = buf.getBoolean(0);
            server.execute(()->{
                ItemStack stack = offhand ? player.getOffhandItem(): player.getMainHandItem();
                Item item = stack.getItem();
                IStarcaller starcaller = (IStarcaller)item;
                starcaller.botaniacombat_summonStarBetterCombat(stack, player.level(), player);
            });
        });
    }
}
