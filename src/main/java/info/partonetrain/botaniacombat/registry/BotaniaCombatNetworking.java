package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.IStarcaller;
import info.partonetrain.botaniacombat.ITerrasteelWeapon;
import info.partonetrain.botaniacombat.network.StarcallerHitPacket;
import info.partonetrain.botaniacombat.network.TerrasteelWeaponHitPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BotaniaCombatNetworking {
    public static final ResourceLocation TERRASTEEL_WEAPON_PACKET_ID = new ResourceLocation(BotaniaCombat.MOD_ID, "terrasteel_weapon");
    public static final ResourceLocation STARCALLER_PACKET_ID = new ResourceLocation(BotaniaCombat.MOD_ID, "starcaller");
    public static final PacketType<StarcallerHitPacket> STARCALLER_HIT_PACKET_PACKET_TYPE = PacketType.create(STARCALLER_PACKET_ID, StarcallerHitPacket::new);
    public static final PacketType<TerrasteelWeaponHitPacket> TERRASTEEL_WEAPON_PACKET_TYPE = PacketType.create(TERRASTEEL_WEAPON_PACKET_ID, TerrasteelWeaponHitPacket::new);

    public static void initBetterCombat(){
        ServerPlayNetworking.registerGlobalReceiver(TERRASTEEL_WEAPON_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean offhand = buf.getBoolean(0);
            server.execute(()->{
                ItemStack stack = offhand ? player.getOffhandItem(): player.getMainHandItem();
                Item item = stack.getItem();
                ITerrasteelWeapon terraWeapon = (ITerrasteelWeapon)item;
                terraWeapon.botaniacombat$spawnBeamBetterCombat(stack, player.level(), player, offhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(STARCALLER_PACKET_ID, (server, player, handler, buf, responseSender) -> {
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
