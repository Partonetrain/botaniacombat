package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import info.partonetrain.botaniacombat.ColorContainer;
import info.partonetrain.botaniacombat.ITerrasteelWeapon;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.network.serverbound.LeftClickPacket;
import vazkii.botania.xplat.ClientXplatAbstractions;

public class GaiaGreatswordItem extends BotaniaCombatWeaponItem implements ITerrasteelWeapon {
    private static final int[] BURST_ANGLES = new int[]{-30, -20, -10, 0, 10, 20, 30};

    public GaiaGreatswordItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Properties props) {
        super(mat, attackDamageFromWeaponType, attackSpeed, props);
    }

    public static void leftClick(ItemStack stack) {  //vanilla combat ONLY
        if (!stack.isEmpty() && stack.getItem() instanceof GaiaGreatswordItem) {
            ClientXplatAbstractions.INSTANCE.sendToServer(LeftClickPacket.INSTANCE);
        }
    }

    public static InteractionResult attackEntity(Player player, Level world, InteractionHand hand, Entity target, @Nullable EntityHitResult hit) {
        if (!player.level().isClientSide() && !player.isSpectator()) {  //vanilla combat ONLY
            trySpawnBursts(player);
        }

        return InteractionResult.PASS;
    }

    public static void trySpawnBursts(Player player) { //vanilla combat ONLY
        trySpawnBursts(player, player.getAttackStrengthScale(0F));
    }

    public static void trySpawnBursts(Player player, float attackStrength) { //vanilla combat ONLY
        if (!player.isSpectator()) {
            //mainhand
            if (!player.getMainHandItem().isEmpty()
                    && player.getMainHandItem().getItem() instanceof GaiaGreatswordItem
                    && attackStrength == 1) {
                spawnSevenBursts(player);
                player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            }
        }
    }

    public static void spawnSevenBursts(Player player) {
        final ItemStack DUMMY_TERRABLADE = new ItemStack(BotaniaItems.terraSword);
        ColorContainer cc = new ColorContainer(player.getName().getString());

        for (int i = 0; i < BURST_ANGLES.length; i++) {
            ManaBurstEntity burst = new ManaBurstEntity(player);

            float motionModifier = 10F;

            burst.setColor(cc.getColorAt(i));
            burst.setMana(BotaniaCombat.MANA_PER_DAMAGE_TERRA);
            burst.setStartingMana(BotaniaCombat.MANA_PER_DAMAGE_TERRA);
            burst.setMinManaLoss(40);
            burst.setManaLossPerTick(4F);
            burst.setGravity(0F);
            burst.setDeltaMovement(ManaBurstEntity.calculateBurstVelocity(burst.getXRot(), burst.getYRot() + BURST_ANGLES[i]).scale(motionModifier));

            burst.setSourceLens(DUMMY_TERRABLADE);

            //BotaniaCombat.LOGGER.info(String.valueOf(i) + " " + String.valueOf(ANGLES[i]) +  burst.xRotO);
            player.level().addFreshEntity(burst);
        }
    }

    @Override
    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE_TERRA * BURST_ANGLES.length;
    }

    @Override
    public void botaniacombat$summonBeamBetterCombat(ItemStack stack, Level level, Player player, InteractionHand interactionHand) {
        float attackStrength = player.getAttackStrengthScale(0F);
        if (!player.isSpectator()
                && stack.getItem() instanceof ITerrasteelWeapon
                && attackStrength == 1) {
            spawnSevenBursts(player);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(interactionHand));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
        }
    }
}
