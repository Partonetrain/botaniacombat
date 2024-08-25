package info.partonetrain.botaniacombat.mixin.bettercombat;

import info.partonetrain.botaniacombat.IStarcaller;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.tool.StarcallerItem;

@Mixin(StarcallerItem.class)
public class StarcallerItemMixin implements IStarcaller { //StarcallerItem will implement IStarcaller
    @Shadow(remap = false) //summonFallingStar is private for some reason
    private void summonFallingStar(ItemStack stack, Level world, Player player) {}
    @Override
    public void botaniacombat$summonStarBetterCombat(ItemStack stack, Level level, Player player, InteractionHand interactionHand){
        long timeSinceLast = level.getGameTime() - ItemNBTHelper.getLong(stack, "lastTriggerTime", level.getGameTime());
        if (timeSinceLast > 12L && !level.isClientSide()) { //12L = StarcallerItem.INTERVAL
            ItemNBTHelper.setLong(stack, "lastTriggerTime", level.getGameTime());
            this.summonFallingStar(stack, level, player);
        }
    }
}
