package info.partonetrain.botaniacombat.block;

import info.partonetrain.botaniacombat.block.block_entity.AngryBozuBlockEntity;
import info.partonetrain.botaniacombat.registry.BotaniaCombatBlockEntities;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.TeruTeruBozuBlock;
import vazkii.botania.common.helper.EntityHelper;

public class AngryBozuBlock extends TeruTeruBozuBlock implements EntityBlock {
    public AngryBozuBlock(Properties builder) {
        super(builder);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity e) {
        if (!world.isClientSide && e instanceof ItemEntity item) {
            ItemStack stack = item.getItem();
            if (isWitherRose(stack) && startThunder(world)) {
                EntityHelper.shrinkItem(item);
                return;
            }
        }
        super.entityInside(state, world, pos, e);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && (isWitherRose(stack) && startThunder(world))) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    private boolean isWitherRose(ItemStack stack) {
        return stack.is(Blocks.WITHER_ROSE.asItem());
    }

    @NotNull
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AngryBozuBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return createTickerHelper(type, BotaniaCombatBlockEntities.ANGRY_BOZU, AngryBozuBlockEntity::serverTick);
        }
        return null;
    }

    private boolean startThunder(Level world) {
        if (!world.isThundering()) {
            if(!world.isRaining()){
                world.getLevelData().setRaining(true);
            }
            AngryBozuBlockEntity.resetThunderTime(world); //this is what actually makes it thunder
            return true;
        }
        return false;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
        return world.isThundering() ? 15 : 0;
    }
}
