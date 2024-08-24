package info.partonetrain.botaniacombat.network;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;

public class StarcallerHitPacket implements FabricPacket {
    private final boolean offhand;

    public StarcallerHitPacket(boolean offhand){
        this.offhand = offhand;
    }
    public StarcallerHitPacket(FriendlyByteBuf buf){
        offhand = buf.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(offhand);
    }

    @Override
    public PacketType<?> getType() {
        return BotaniaCombat.STARCALLER_HIT_PACKET_PACKET_TYPE;
    }
}
