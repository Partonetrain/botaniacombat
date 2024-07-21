package info.partonetrain.botaniacombat.block.block_entity;

import info.partonetrain.botaniacombat.registry.BotaniaCombatBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntity;

public class AngryBozuBlockEntity extends BotaniaBlockEntity {
    private boolean wasThundering = false;
    public AngryBozuBlockEntity(BlockPos pos, BlockState state) {
        super(BotaniaCombatBlockEntities.ANGRY_BOZU, pos, state);
    }

    public static void serverTick(Level level, BlockPos worldPosition, BlockState state, AngryBozuBlockEntity self) {
        boolean isThundering = level.isThundering();

        if (self.wasThundering != isThundering) {
            level.updateNeighbourForOutputSignal(worldPosition, state.getBlock());
        }
        self.wasThundering = isThundering;
    }

    public static void resetThunderTime(Level w) { //sets how long the thunder will last
        int time = w.random.nextInt(w.getLevelData().isRaining() ? 12000 : 168000) + 12000;
        if(w instanceof ServerLevel sl){
            sl.setWeatherParameters(0, time, true, true);
            //setWeatherParameters sets LevelData
        }
    }
}
