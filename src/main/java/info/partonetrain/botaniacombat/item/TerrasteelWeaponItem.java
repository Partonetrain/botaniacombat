package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.BotaniaNerfConfiguredValues;
import info.partonetrain.botaniacombat.ITerrasteelWeapon;
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
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraBladeItem;
import vazkii.botania.network.serverbound.LeftClickPacket;
import vazkii.botania.xplat.ClientXplatAbstractions;

public class TerrasteelWeaponItem extends BotaniaCombatWeaponItem implements ITerrasteelWeapon {
    public TerrasteelWeaponItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Item.Properties properties) {
        super(mat, attackDamageFromWeaponType, attackSpeed, properties);
    }

    public static void leftClick(ItemStack stack) { //vanilla combat ONLY
        if (!stack.isEmpty() && stack.getItem() instanceof TerrasteelWeaponItem) {
            ClientXplatAbstractions.INSTANCE.sendToServer(LeftClickPacket.INSTANCE);
        }
    }

    public static InteractionResult attackEntity(Player player, Level world, InteractionHand hand, Entity target, @Nullable EntityHitResult hit) { //vanilla combat ONLY
        if (!player.level().isClientSide() && !player.isSpectator()) {
            trySpawnBurst(player);
        }
        return InteractionResult.PASS;
    }

    public static void trySpawnBurst(Player player) { //vanilla combat ONLY
        trySpawnBurst(player, player.getAttackStrengthScale(0F));
    }

    public static void trySpawnBurst(Player player, float attackStrength) { //vanilla combat ONLY
        if (!player.isSpectator()) {
            final ItemStack DUMMY_TERRABLADE = new ItemStack(BotaniaItems.terraSword);
            if (!player.getMainHandItem().isEmpty()
                    && player.getMainHandItem().getItem() instanceof TerrasteelWeaponItem
                    && attackStrength == 1) {
                ManaBurstEntity burst = TerraBladeItem.getBurst(player, DUMMY_TERRABLADE);
                player.level().addFreshEntity(burst);
                player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            }
        }
    }

    @Override
    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE_TERRA;
    }

    @Override
    public void botaniacombat$spawnBeamBetterCombat(ItemStack stack, Level level, Player player, InteractionHand interactionHand) {
        if(ManaItemHandler.INSTANCE.requestManaExactForTool(stack, player, BotaniaNerfConfiguredValues.terrasteelBeamCost, true)) {
            final ItemStack DUMMY_TERRABLADE = new ItemStack(BotaniaItems.terraSword);
            float attackStrength = player.getAttackStrengthScale(0F);
            if (!player.isSpectator()
                    && stack.getItem() instanceof ITerrasteelWeapon
                    && attackStrength == 1) {
                ManaBurstEntity burst = TerraBladeItem.getBurst(player, stack);
                burst.setSourceLens(DUMMY_TERRABLADE);
                player.level().addFreshEntity(burst);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(interactionHand));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            }
        }
    }
}
