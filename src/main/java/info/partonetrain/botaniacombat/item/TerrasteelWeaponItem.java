package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;
import vazkii.botania.network.serverbound.LeftClickPacket;
import vazkii.botania.xplat.ClientXplatAbstractions;

public class TerrasteelWeaponItem extends BotaniaCombatWeaponItem {
    public TerrasteelWeaponItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Item.Properties properties) {
        super(mat, attackDamageFromWeaponType, attackSpeed, properties);
    }

    public static void leftClick(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof TerrasteelWeaponItem) {
            ClientXplatAbstractions.INSTANCE.sendToServer(LeftClickPacket.INSTANCE);
        }
    }

    public static InteractionResult attackEntity(Player player, Level world, InteractionHand hand, Entity target, @Nullable EntityHitResult hit) {
        if (!player.level().isClientSide && !player.isSpectator()) {
            trySpawnBurst(player);
        }
        return InteractionResult.PASS;
    }

    public static void trySpawnBurst(Player player) {
        trySpawnBurst(player, player.getAttackStrengthScale(0F));
    }

    public static void trySpawnBurst(Player player, float attackStrength) {
        final ItemStack DUMMY_TERRABLADE = new ItemStack(BotaniaItems.terraSword);
        //mainhand
        if (!player.getMainHandItem().isEmpty()
                && player.getMainHandItem().getItem() instanceof TerrasteelWeaponItem
                && attackStrength == 1) {
            ManaBurstEntity burst = TerraBladeItem.getBurst(player, DUMMY_TERRABLADE);
            player.level().addFreshEntity(burst);
            player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            return; //at this point don't bother checking the offhand
        }
        //offhand
        if (!player.getOffhandItem().isEmpty()
                && player.getOffhandItem().getItem() instanceof TerrasteelWeaponItem
                && attackStrength == 1) {
            ManaBurstEntity burst = TerraBladeItem.getBurst(player, DUMMY_TERRABLADE);

            player.level().addFreshEntity(burst);
            player.getOffhandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.OFF_HAND));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
        }
    }

    @Override
    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE_TERRA;
    }

}
