package info.partonetrain.botaniacombat;

import info.partonetrain.botaniacombat.registry.BotaniaCombatItems;
import net.minecraft.Util;
import net.minecraft.util.Mth;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.render.ColorHandler.ItemHandlerConsumer;

public class ColorHandler {

    //in the future Botania's color-changing textures *may* be animated through conventional .png.mcmeta instead.
    //The lines in this method are based on the equivalent lines in Botania's ColorHandler. https://github.com/VazkiiMods/Botania/blob/1.20.x/Xplat/src/main/java/vazkii/botania/client/render/ColorHandler.java
    public static void submitBotaniaCombatItems(ItemHandlerConsumer items) {
        items.register((s, t) -> t == 0 ? Mth.hsvToRgb(ClientTickHandler.ticksInGame * 2 % 360 / 360F,  0.5F, 1F) : -1, BotaniaCombatItems.items.get("gaia_greatsword"));

        items.register((s, t) -> t == 1 ? Mth.hsvToRgb(0.75F, 1F, 1.5F - (float) Math.min(1F, Math.sin(Util.getMillis() / 100D) * 0.5 + 1.2F)) : -1, BotaniaCombatItems.items.get("soulstaff"));

    }

}
