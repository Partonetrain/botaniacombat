package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import vazkii.botania.api.BotaniaRegistries;

public class BotaniaCombatRegistryCallbackImpl {
    public static void init() {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, "manasteel_shield"), BotaniaCombatItems.callbackItems.get("manasteel_shield"));
        ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(BotaniaCombatItems.callbackItems.get("manasteel_shield")));
    }
}
