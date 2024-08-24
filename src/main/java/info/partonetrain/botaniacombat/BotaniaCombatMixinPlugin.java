package info.partonetrain.botaniacombat;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

//The purpose of this plugin is to not apply LivingwoodBowItemMixin if RangedWeaponAPI is not installed
//and to not apply enchantment related mixins if FarmersDelight isn't installed
public class BotaniaCombatMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        // NO-OP
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] packageTree = mixinClassName.split("\\.");

        if(Arrays.asList(packageTree).contains("nerf")){
            return true;
        }
        if (Arrays.asList(packageTree).contains("bettercombat")) {
            return BotaniaCombat.BETTER_COMBAT_INSTALLED;
        }
        if (Arrays.asList(packageTree).contains("ranged")) {
            return BotaniaCombat.RANGED_WEAPON_API_INSTALLED;
        }
        if (Arrays.asList(packageTree).contains("shield")) {
            return BotaniaCombat.FABRIC_SHIELD_LIB_INSTALLED;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // NO-OP
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // NO-OP
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // NO-OP
    }
}
