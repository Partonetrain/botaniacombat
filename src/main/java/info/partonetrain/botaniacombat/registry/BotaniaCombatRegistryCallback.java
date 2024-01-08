package info.partonetrain.botaniacombat.registry;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import vazkii.botania.api.BotaniaRegistries;


public class BotaniaCombatRegistryCallback {

    static final ResourceLocation MANASTEEL_ID = new ResourceLocation("botania", "manasteel_ingot");

    public static void init(){

            RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM).register((rawId, id, item1) ->  {
                if(id == MANASTEEL_ID){
                    BotaniaCombatRegistryCallbackImpl.init();
                }
            });


    }
}
