package info.partonetrain.botaniacombat;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ConfiguredValues {
    public static float terraBladeDamage = 7;
    public static float chakramDamage = 12;
    public static float dmgLensDamage = 8;
    public static float odinHealthMod = 20f;
    public static AttributeModifier.Operation odinHealthOperation = AttributeModifier.Operation.ADDITION;
    public static float babylonDamage = 20f;
    public static float missileDamage = 7f;
    public static void init(){
        terraBladeDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.terraBladeDamage;
        chakramDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.chakramDamage;
        dmgLensDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.dmgLensDamage;
        odinHealthMod = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthMod;
        odinHealthOperation = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthOperation;
        babylonDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.babylonDamage;
        missileDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.missileDamage;
    }
}
