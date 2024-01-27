# BotaniaCombat

BotaniaCombat seeks to create a variety of weapons using [Botania](https://www.curseforge.com/minecraft/mc-mods/botania)'s metals, and integrate Botania with [BetterCombat](https://www.curseforge.com/minecraft/mc-mods/better-combat-by-daedelus).

This mod does the following:

- Adds a Dagger for Elementium and Terrasteel (Manasteel equivalent is Botania's Soulscribe)
- Adds the Soulstaff, a battlestaff version of the Soulscribe
- Adds a Spear for Elementium and Terrasteel
- If you are a patron of Vazkii, terrasteel weapons will emit mana beams with a random color from your [Psi CAD colorizer](https://github.com/VazkiiMods/Psi/blob/master/contributors.properties) colors
- Adds additional post-Gaia weapon, the Gaia Greatsword, which can use all of your Patreon colors
- Adds a secret weapon
- Buffs player-spawned pixies by making them always have the +2 damage bonus that would normally only be applied when the player is holding the Elementium Sword

If [Better Combat](https://www.curseforge.com/minecraft/mc-mods/better-combat-by-daedelus) is installed, it also does the following:

- Adds offhand pixie spawn chance attribute to Elementium weapons (including Elementium Sword)
- Adds pixie spawn chance attributes to the Elementium Axe
- Makes Terrasteel weapons (including Terra Blade) shoot a beam at the start of a BetterCombat swing; also works with offhand swings
- Reduces Thundercaller attack range to 1.5 (from BetterCombat's default 2.5), electricity range is unaffected

If [Fabric Shield Lib](https://www.curseforge.com/minecraft/mc-mods/fabric-shield-lib) is installed, it also does the following:
- Adds the Manasteel Buckler
- Adds the Elementium Shield
- Adds the Terra Tower Shield
- Adds a secret shield

If [Ranged Weapon API](https://www.curseforge.com/minecraft/mc-mods/ranged-weapon-api) is installed, it also does the following:
- Adds Pull Time and Projectile Damage attributes to Botania's bows
- Adds Livingwood Crossbow
- Adds Crystal Crossbow (w/ a different Multishot angle)
- Adds a secret bow

**NOTE**: If you want the text in the Lexica Botania and subtitles to be accurate, you will need [this resource pack](https://modrinth.com/resourcepack/botaniacombatlang/settings/description).

Credits:
Spear, Dagger, and Battlestaff render model implementation largely adapted from [Basic Weapons](https://www.curseforge.com/minecraft/mc-mods/basic-weapons) (Thanks June!)

## FAQ
#### *How much damage does the mana beam from the new weapons do?*
7 (or 3.5 hearts), same as Terra Blade's.

####  *Will you make a Forge version?*
Maybe, but it would be a different project, I don't feel like learning architecturey for such a simple mod

#### *Why so many (optional) dependencies*?
A couple of reasons, but mostly these are mods I already use myself, and I don't like doing mixins to vanilla if I can prevent it

#### *Is there a config?*
Not at this time. I'm not sure what there would be to configure that can't already be configured through datapacks.