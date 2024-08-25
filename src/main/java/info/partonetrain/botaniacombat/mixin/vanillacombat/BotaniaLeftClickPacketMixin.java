package info.partonetrain.botaniacombat.mixin.vanillacombat;

import info.partonetrain.botaniacombat.item.GaiaGreatswordItem;
import info.partonetrain.botaniacombat.item.TerrasteelWeaponItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.network.serverbound.LeftClickPacket;

@Mixin(LeftClickPacket.class)
public class BotaniaLeftClickPacketMixin {
    /*
        partonetrain: will mixing into a packet cause problems :clueless~1:
        <williewillus> probably yes
        <williewillus> what are you trying?
        partonetrain: I got my other terra weapons to do the beam
        partonetrain: by mixing into botania LeftClickPacket
        <williewillus> like mixing into the packet to do what
        <williewillus> just call another method?
        <williewillus> that's fine
        <williewillus> but why mix into the packet when you can just send your own?
        partonetrain: good question. it's because I've never done that before
     */
    @Inject(method = "handle", at = @At(value = "TAIL"))
    public void botaniacombat$handle(MinecraftServer server, ServerPlayer player, CallbackInfo ci) {
        float scale = player.getAttackStrengthScale(0F);
        server.execute(() -> TerrasteelWeaponItem.trySpawnBurst(player, scale));
        server.execute(() -> GaiaGreatswordItem.trySpawnBursts(player, scale));
    }
}
