package info.partonetrain.botaniacombat;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IStarcaller {
    void botaniacombat$summonStarBetterCombat(ItemStack stack, Level level, Player player, InteractionHand interactionHand);
}
