/*
 * This class uses code adapted from the Botania mod.
 * Relevant code: https://github.com/VazkiiMods/Botania/blob/1.20.x/Xplat/src/main/java/vazkii/botania/common/item/equipment/tool/ThundercallerItem.java
 */

package info.partonetrain.botaniacombat.item;

import info.partonetrain.botaniacombat.BotaniaCombat;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.network.EffectType;
import vazkii.botania.network.clientbound.BotaniaEffectPacket;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;
import java.util.function.Predicate;

public class MjolnirItem extends BotaniaCombatWeaponItem {
    private static final int BOLT_MANA_COST = 2500;
    private static final int BIFROST_SHATTER_COST = 50;
    public static final TagKey<Block> SHATTERABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(BotaniaCombat.MOD_ID, "mjolnir_shatterable"));
    private static final int COOLDOWN_TIME = 100; //5 secs
    private static final int MAX_RECURSION = 200; //160 is the max length of the bifrost rod

    public MjolnirItem(Tier mat, int attackDamageFromWeaponType, float attackSpeed, Properties properties) {
        super(mat, attackDamageFromWeaponType, attackSpeed, properties);
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext ctx) {
        Player player = ctx.getPlayer();

        if (player != null) {
            if (ctx.getHand() == InteractionHand.MAIN_HAND && (ctx.getPlayer().isCreative() || (ManaItemHandler.instance().requestManaExactForTool(ctx.getItemInHand(), player, BOLT_MANA_COST, true)))) {
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, ctx.getLevel());

                if (!ctx.getLevel().isClientSide()) {
                    bolt.setCause((ServerPlayer) player);
                }

                bolt.setPos(ctx.getClickLocation());
                ctx.getLevel().addFreshEntity(bolt);
                player.getCooldowns().addCooldown(this, COOLDOWN_TIME);

                BlockState blockBelow = bolt.getBlockStateOn();
                if(blockBelow.is(SHATTERABLE)){
                    shatterBifrostBlocks(0, ctx, bolt.getOnPos(), blockBelow, bolt);
                }

                return InteractionResult.sidedSuccess(ctx.getLevel().isClientSide()); //this is what does the "used" animation
            }
        }

        return InteractionResult.PASS;
    }

    //derived from ThundercallerItem
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, @NotNull LivingEntity attacker) {
        double range = 10;
        IntList alreadyTargetedEntities = new IntArrayList();

        Predicate<Entity> selector = e -> e instanceof LivingEntity && e instanceof Enemy && !(e instanceof Player) && !alreadyTargetedEntities.contains(e.getId());

        LivingEntity prevTarget = entity;
        int hops = 10;
        int dmg = (int) attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue();

        for (int i = 0; i < hops; i++) {
            List<Entity> entities = entity.level().getEntities(prevTarget, new AABB(prevTarget.getX() - range, prevTarget.getY() - range, prevTarget.getZ() - range, prevTarget.getX() + range, prevTarget.getY() + range, prevTarget.getZ() + range), selector);
            if (entities.isEmpty() || dmg < 1) {
                break;
            }

            LivingEntity target = (LivingEntity) entities.get(entity.level().getRandom().nextInt(entities.size()));
            if (attacker instanceof Player player) {
                target.hurt(player.damageSources().playerAttack(player), dmg);
            } else {
                target.hurt(attacker.damageSources().mobAttack(attacker), dmg);
            }

            alreadyTargetedEntities.add(target.getId());
            prevTarget = target;
            dmg -= 2;
        }

        if (!alreadyTargetedEntities.isEmpty()) {
            XplatAbstractions.INSTANCE.sendToTracking(attacker,
                    new BotaniaEffectPacket(EffectType.THUNDERCALLER_EFFECT,
                            attacker.getX(), attacker.getY() + attacker.getBbHeight() / 2.0, attacker.getZ(),
                            alreadyTargetedEntities.toArray(new int[0])));
        }

        return super.hurtEnemy(stack, entity, attacker);
    }

    public void shatterBifrostBlocks(int recursions, UseOnContext ctx, BlockPos blockPos, BlockState blockState, LightningBolt bolt){
        Level level = ctx.getLevel();
        if (recursions <= MAX_RECURSION){
            if(!level.isClientSide){
                ServerLevel sl = (ServerLevel) level;
                level.removeBlock(blockPos, false);
                sl.levelEvent(2001, blockPos, Block.getId(blockState));
                for(Direction d: Direction.values()){
                    BlockState nextBlock = sl.getBlockState(blockPos.relative(d));
                    if(nextBlock.is(SHATTERABLE))
                    {
                        if(ctx.getPlayer().isCreative() || ManaItemHandler.INSTANCE.requestManaExactForTool(ctx.getItemInHand(), ctx.getPlayer(), BIFROST_SHATTER_COST, true)){
                            shatterBifrostBlocks(++recursions, ctx, blockPos.relative(d), blockState, bolt);
                        }

                    }
                }
            }
        }else{
            if(!level.isClientSide){
                ctx.getPlayer().thunderHit((ServerLevel) level, bolt);
            }

        }
    }
}
