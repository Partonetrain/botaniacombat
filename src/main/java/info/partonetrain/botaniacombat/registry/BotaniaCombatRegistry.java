package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import vazkii.botania.api.BotaniaRegistries;

import java.util.Map;

public class BotaniaCombatRegistry {

    public static void init() {
        BotaniaCombatItems.Init();
        for (Map.Entry<String,Item> entry : BotaniaCombatItems.items.entrySet()){
            registerItem(entry.getKey(), entry.getValue());
            ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(entry.getValue()));
        }
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, name), item);
    }


}
