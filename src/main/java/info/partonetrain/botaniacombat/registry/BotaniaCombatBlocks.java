package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.block.AngryBozuBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.common.item.block.BlockItemWithSpecialRenderer;

public class BotaniaCombatBlocks {

    public static final Block angryBozu = registerBlock ("angry_bozu", new AngryBozuBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).instrument(NoteBlockInstrument.GUITAR).mapColor(DyeColor.RED)));

    public static void init() {}

    public static <T extends Block> T registerBlock(String name, T block) {
        ResourceLocation id = new ResourceLocation(BotaniaCombat.MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        BlockItem blockItem = new BlockItemWithSpecialRenderer(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, id, blockItem);

        ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.accept(block.asItem()));

        return block;
    }
}
