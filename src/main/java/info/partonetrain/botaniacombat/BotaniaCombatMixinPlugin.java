package info.partonetrain.botaniacombat;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

//The purpose of this plugin is to not apply LivingwoodBowItemMixin if RangedWeaponAPI is not installed
//thx to arkosammy12 for the help
public class BotaniaCombatMixinPlugin implements IMixinConfigPlugin {
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] packageTree = mixinClassName.split("\\.");
        if(Arrays.asList(packageTree).contains("ranged")){
            return FabricLoader.getInstance().isModLoaded("ranged_weapon_api"); //this plugin can run before the main mod class
        }
        return true;
    }

    //everything below this is irrelevant

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
