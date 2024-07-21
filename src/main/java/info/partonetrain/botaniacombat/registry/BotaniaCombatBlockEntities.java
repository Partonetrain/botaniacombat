package info.partonetrain.botaniacombat.registry;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.block.block_entity.AngryBozuBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public class BotaniaCombatBlockEntities {

    public static final BlockEntityType<AngryBozuBlockEntity> ANGRY_BOZU = registerBlockEntity("angry_bozu.json", AngryBozuBlockEntity::new);

    public static void init() {}

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BiFunction<BlockPos, BlockState, T> func) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(BotaniaCombat.MOD_ID, name), FabricBlockEntityTypeBuilder.create(func::apply, BotaniaCombatBlocks.angryBozu).build());
    }
}
