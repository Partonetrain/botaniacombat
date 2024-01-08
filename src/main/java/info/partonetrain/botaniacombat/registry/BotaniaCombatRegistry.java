package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.common.item.BotaniaItems;

import java.util.Map;

public class BotaniaCombatRegistry {

    static final ResourceLocation manasteel = new ResourceLocation("botania", "manasteel_ingot");

    public static void init() {
        BotaniaCombatItems.Init();
        for (Map.Entry<String,Item> entry : BotaniaCombatItems.items.entrySet()){
            registerItem(entry.getKey(), entry.getValue());
            ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(entry.getValue()));
        }
        for (Map.Entry<String,Item> entry : BotaniaCombatItems.callbackItems.entrySet()){
            registerItemWithCallback(entry.getKey(), entry.getValue());
            ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(entry.getValue()));
        }
    }

    public static void registerItem(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, name), item);
    }

    public static void registerItemWithCallback(String name, Item item){
        RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM).register((rawId, id, item1) ->  {
            if(id == manasteel){
                Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(BotaniaCombat.MOD_ID, name), item);
            }
        });
    }

}
