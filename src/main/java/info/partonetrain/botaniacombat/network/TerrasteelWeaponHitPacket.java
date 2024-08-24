package info.partonetrain.botaniacombat.network;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.registry.BotaniaCombatNetworking;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;

public class TerrasteelWeaponHitPacket implements FabricPacket {
    private final boolean offhand;
    public TerrasteelWeaponHitPacket(boolean offhand){
        this.offhand = offhand;
    }
    public TerrasteelWeaponHitPacket(FriendlyByteBuf buf){
        offhand = buf.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(offhand);
    }

    @Override
    public PacketType<?> getType() {
        return BotaniaCombatNetworking.TERRASTEEL_WEAPON_PACKET_TYPE;
    }
}
