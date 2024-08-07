package info.partonetrain.botaniacombat.item.shield;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShield;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import info.partonetrain.botaniacombat.BotaniaCombat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.List;
import java.util.function.Consumer;

public class TerrasteelShieldItem extends FabricShieldItem implements CustomDamageItem, FabricShield {
    public final int PUSH_COST = 250;
    public static final int PUSH_COOLDOWN_TIME = 40; //2 secs

    public TerrasteelShieldItem(Properties properties, int cooldownTicks, Tier tier) {
        super(properties, cooldownTicks, tier);
    }

    public int getManaPerDamage() {
        return BotaniaCombat.MANA_PER_DAMAGE_TERRA;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if(!world.isClientSide() && entity instanceof Player player){
            if (stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, getManaPerDamage() * 2, true)) {
                stack.setDamageValue(stack.getDamageValue() - 1);
            }
            this.inventoryTickExtra(stack, player, false);
        }

    }

    public void inventoryTickExtra(ItemStack stack, Player player, boolean extraFlag) {
        // NO-OP
    }

    //push entities in front of player when blocking is started
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.startUsingItem(hand);

        ItemStack itemStack = user.getItemInHand(hand);

        if (itemStack.getItem() instanceof TerrasteelShieldItem && user.isShiftKeyDown() && !world.isClientSide() && ManaItemHandler.instance().requestManaExactForTool(itemStack, user, PUSH_COST, true)) {
            final double range = 2.0D;

            Vec3 lookAngle = user.getLookAngle();
            Vec3 srcVec = new Vec3(user.getX(), user.getY(), user.getZ());
            Vec3 lookVec = lookAngle.scale(2);
            Vec3 destVec = srcVec.add(lookVec);

            AABB areaInFrontOfPlayer = new AABB(destVec.x() + range, destVec.y() + range, destVec.z() + range, destVec.x() - range, destVec.y(), destVec.z() - range);
            List<LivingEntity> livingInFront = world.getEntitiesOfClass(LivingEntity.class, areaInFrontOfPlayer);

            for (LivingEntity living : livingInFront) {
                if (living.isAlive() && !living.is(user) && !user.isAlliedTo(living)) {
                    living.knockback((getPushPower(user)), -lookAngle.x, -lookAngle.z);
                    this.useExtra(living, world);
                }

                this.doFX(world, areaInFrontOfPlayer, user.getOnPos().above(), user);
                user.getCooldowns().addCooldown(this, PUSH_COOLDOWN_TIME);
            }
        }

        return InteractionResultHolder.consume(itemStack);
    }

    public void useExtra(LivingEntity living, Level world) {
        // NO-OP
    }

    public double getPushPower(Player player) {
        return 2 + (2 * player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue()); //IDE says this may result in NPE but how?
    }

    public void doFX(Level world, AABB aabb, BlockPos soundLocation, Player player) {
        world.playSound(null, soundLocation, BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1.0F, 2.0F);

        int poofParticleMult = (int) getPushPower(player);

        if (world instanceof ServerLevel ws) {
            ws.sendParticles(ParticleTypes.POOF, aabb.getCenter().x(), aabb.getCenter().y(), aabb.getCenter().z(), 5 * poofParticleMult, 0, 0, 0, 0.5D);
            this.doFXExtra(ws, aabb);

            /*
            // bounding box indicators
            ws.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, BotaniaBlocks.cellBlock.defaultBlockState()), aabb.maxX, aabb.maxY, aabb.maxZ, 5, 0, 0, 0, 0);
            ws.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, BotaniaBlocks.cellBlock.defaultBlockState()), aabb.minX, aabb.minY, aabb.minZ, 5, 0, 0, 0, 0);
            ws.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, BotaniaBlocks.bifrost.defaultBlockState()), soundLocation.getX(), soundLocation.getY(), soundLocation.getZ(), 5, 0, 0, 0, 0);
            */
        }
    }

    public void doFXExtra(ServerLevel ws, AABB aabb) {
        // NO-OP
    }
}
