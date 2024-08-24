package info.partonetrain.botaniacombat;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BotaniaNerfConfiguredValues { //have default values loaded so mixins don't classload config
    public static boolean conformSoulscribe = false;
    public static int daggerDamageModifier = -3;
    public static float daggerSpeed =  -1.6f;
    public static float terraBladeDamage = 7;
    public static float chakramDamage = 12;
    public static float fallingStarNormalDamage = 5;
    public static float fallingStarHighDamage = 10;
    public static float dmgLensDamage = 8;
    public static float odinHealthMod = 20f;
    public static AttributeModifier.Operation odinHealthOperation = AttributeModifier.Operation.ADDITION;
    public static float babylonDamage = 20f;
    public static float missileDamage = 7f;
    public static void init(){
        conformSoulscribe = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.conformSoulscribe;
        daggerDamageModifier = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().daggerDamageModifier;
        daggerSpeed = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().daggerSpeed;
        terraBladeDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.terraBladeDamage;
        chakramDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.chakramDamage;
        fallingStarNormalDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.fallingStarNormalDamage;
        fallingStarHighDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.fallingStarHighDamage;
        dmgLensDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.dmgLensDamage;
        odinHealthMod = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthMod;
        odinHealthOperation = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.odinHealthOperation;
        babylonDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.babylonDamage;
        missileDamage = AutoConfig.getConfigHolder(BotaniaCombatConfig.class).getConfig().botaniaNerfsConfig.missileDamage;
    }
}
