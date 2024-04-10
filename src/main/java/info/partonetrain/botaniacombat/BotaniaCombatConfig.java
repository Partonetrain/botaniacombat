package info.partonetrain.botaniacombat;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = BotaniaCombat.MOD_ID)
public class BotaniaCombatConfig implements ConfigData {
    @Comment("Modifier of dagger damage amount relative to tier damage")
    public int daggerDamageModifier = -3;
    @Comment("Modifier of dagger speed relative to default speed (4)")
    public float daggerSpeed = -2.0f;
    @Comment("Whether or not the Slaughtersaw can ONLY hit entities in the #botaniacombat:meaty entity tag")
    public boolean slaughtersawRestricted = false;
    @Comment("Modifier of Slaughtersaw damage amount relative to Manasteel tier damage")
    public float slaughtersawDamageModifier = -5;
    @Comment("Modifier of Slaughtersaw speed relative to default speed (4)")
    public float slaughtersawSpeed = -2f;
    @Comment("Modifier of spear damage amount relative to tier damage")
    public int spearDamageModifier = -1;
    @Comment("Modifier of spear speed relative to default speed (4)")
    public float spearSpeed = -2.8f;
    @Comment("Modifier of Soulstaff damage amount relative to Manasteel tier damage")
    public int soulstaffDamageModifier = -1;
    @Comment("Modifier of Soulstaff speed relative to default speed (4)")
    public float soulstaffSpeed = -2.3f;
    @Comment("Modifier of Gaia Greatsword damage amount relative to Terrasteel tier damage")
    public int greatswordDamageModifier = 3;
    @Comment("Modifier of Gaia Greatsword speed relative to default speed (4)")
    public float greatswordSpeed = -3.4f;
    @Comment("Modifier of Mjolnir damage relative to Terrasteel tier damage")
    public int mjolinirDamageModifier = 4;
    @Comment("Modifier of Mjolnir speed relative to default speed (4)")
    public float mjolnirSpeed = -2.8f;

    @ConfigEntry.Gui.CollapsibleObject
    public RangedItemConfig rangedItemConfig = new RangedItemConfig();

    public static class RangedItemConfig{
        @Comment("Damage of both Livingwood and Crystal crossbows")
        public float crossbowDamage = 9f;
        @Comment("Pull time in ticks of Livingwood Crossbow")
        public int livingwoodPullTime = 25;
        @Comment("Pull time in ticks of Crystal Crossbow")
        public int crystalPullTime = 15;
        @Comment("Base damage of Skadi Bow (does not account for velocity)")
        public float skadiDamage = 10f;
        @Comment("Pull time in ticks of Skadi Bow")
        public int skadiPullTime = 30;
    }
}
