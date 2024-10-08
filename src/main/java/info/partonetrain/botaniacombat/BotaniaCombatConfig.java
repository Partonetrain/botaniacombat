package info.partonetrain.botaniacombat;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

@Config(name = BotaniaCombat.MOD_ID)
public class BotaniaCombatConfig implements ConfigData {
    @Comment("Modifier of dagger damage amount relative to tier damage")
    public int daggerDamageModifier = -3;
    @Comment("Modifier of dagger speed relative to default speed (4)")
    public float daggerSpeed = -1.6f;
    @Comment("Whether or not the Slaughtersaw can ONLY hit entities in the #botaniacombat:meaty entity tag")
    public boolean slaughtersawRestricted = false;
    @Comment("Modifier of Slaughtersaw damage amount relative to Manasteel tier damage")
    public float slaughtersawDamageModifier = -5;
    @Comment("Modifier of Slaughtersaw speed relative to default speed (4)")
    public float slaughtersawSpeed = -2f;
    @Comment("Modifier of spear damage amount relative to tier damage")
    public int spearDamageModifier = 0;
    @Comment("Modifier of spear speed relative to default speed (4)")
    public float spearSpeed = -2.8f;
    @Comment("Modifier of Soulstaff damage amount relative to Manasteel tier damage")
    public int soulstaffDamageModifier = -1;
    @Comment("Modifier of Soulstaff speed relative to default speed (4)")
    public float soulstaffSpeed = -2.3f;
    @Comment("Modifier of Gaia Greatsword damage amount relative to Terrasteel tier damage")
    public int greatswordDamageModifier = 4;
    @Comment("Modifier of Gaia Greatsword speed relative to default speed (4)")
    public float greatswordSpeed = -3f;
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

    @ConfigEntry.Gui.CollapsibleObject
    public BotaniaNerfsConfig botaniaNerfsConfig = new BotaniaNerfsConfig();
    public static class BotaniaNerfsConfig{
        @Comment("Conform Soulscribe to Dagger stats specified above (extra Enderman damage is unchanged)")
        public boolean conformSoulscribe = false;
        @Comment("Adds negative movespeed modifier to Terrasteel Armor")
        public boolean slowTerraArmor = false;
        @Comment("Keep in mind this is in addition to 100 mana per durability. This mana is lost and not propagated to the beam. If set, mana is required for firing beams")
        public int terrasteelBeamCost = 0;
        @Comment("Terrasteel weapon projectile damage")
        public float terraBladeDamage = 8;
        @Comment("Chakram damage, poison/fire damage is unchanged")
        public float chakramDamage = 12;
        @Comment("Keep in mind this is in addition to 120 mana per durability. If set, mana is required for summoning stars")
        public int starcallerProjectileCost = 0;
        @Comment("Starcaller projectile")
        public float fallingStarNormalDamage = 5;
        @Comment("Starcaller projectile, 25% chance of using this instead of normal damage")
        public float fallingStarHighDamage = 10;
        @Comment("Missile from Unstable Reservoir Rod (does not effect Gaia Gaurdian)")
        public float missileDamage = 7;
        @Comment("Flugel Tiara mana cost per tick")
        public int tiaraCost = 35;
        @Comment("Flugel Tiara mana cost per tick when flight meter is empty and Eye of the Flugel is in inventory")
        public int tiaraOverkillCost = 105;
        @Comment("Additional time to be removed from the tiara each tick of flight (should be positive). 0 = 60 secs max (default lexicon is wrong), 1 = 30 secs, 2 = 15 secs, etc")
        public int tiaraExtraTime = 0;
        @Comment("(Client Only) Force the flight bar to move this many rows up/down")
        public int tiaraAdditionalRow = 0;
        @Comment("Mana cost of firing Mana Blaster")
        public int manaBlasterCost = 120;
        @Comment("Damaging Lens damage, recommended to lower if RWAPI is installed")
        public float dmgLensDamage = 8;
        @Comment("Ring of Odin health modifier amount")
        public float odinHealthMod = 20f;
        @Comment("Ring of Odin operation: ADDITION/MULTIPLY_BASE/MULTIPLY_TOTAL")
        public AttributeModifier.Operation odinHealthOperation = AttributeModifier.Operation.ADDITION;
        @Comment("Key of King's Law projectile damage; explosion power (and the damage from explosion) is unaffected")
        public float babylonDamage = 20f;
    }
}
