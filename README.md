# BotaniaCombat

BotaniaCombat seeks to create a variety of weapons using [Botania](https://www.curseforge.com/minecraft/mc-mods/botania)'s metals, and integrate Botania with [BetterCombat](https://www.curseforge.com/minecraft/mc-mods/better-combat-by-daedelus).

This mod does the following:

- Adds a Dagger for Elementium and Terrasteel (Manasteel equivalent is Botania's Soulscribe)
- Adds the Soulstaff, a battlestaff version of the Soulscribe
- Adds a Spear for Elementium and Terrasteel
- If you are a patron of Vazkii, terrasteel weapons will emit mana beams with a random color from your [Psi CAD colorizer](https://github.com/VazkiiMods/Psi/blob/master/contributors.properties) colors
- An additional post-Gaia weapon, the Gaia Greatsword, which can use all of your Patreon colors
- Buffs player-spawned pixies by making them always have the +2 damage bonus that would normally only be applied when the player is holding the Elementium Sword

If Better Combat is installed, it also does the following:

- Adds offhand pixie spawn chance attribute to Elementium weapons (including Elementium Sword)
- Makes Terrasteel weapons (including Terra Blade) shoot a beam at the start of a BetterCombat swing; also works with offhand swings
- Reduces Thundercaller attack range to 1.5 (from BetterCombat's default 2.5), electricity range is unaffected
- Increases Soulscribe attack range to 2.25 (from BetterCombat's default of 2)

**NOTE**: If you want the text in the Lexica Botania and subtitles to be accurate, you will need [this resource pack](https://modrinth.com/resourcepack/botaniacombatlang/settings/description).

Credits:

Spear, Dagger, and Battlestaff stats and implementation largely adapted from [Basic Weapons](https://www.curseforge.com/minecraft/mc-mods/basic-weapons) (Thanks June!)

## FAQ
#### *How much damage does the mana beam from the new weapons do?*
7 (or 3.5 hearts), same as Terra Blade's.

####  *Will you make a Forge version?*
Maybe, but it would be a different project, I don't feel like learning architecturey for such a simple mod

#### *What about the daggers/spears for Starcaller/Thundercaller?*
I intentionally left out additional forms of these weapons. 
They aren't really attached to Botania's tiers, and I rarely see them used over the Terra Blade. 
I think it's better if they "stick out" in terms of integration, to make them more unique.

#### *What about (cross)bows?*
A separate mod, likely called BotaniaBows, will be made. This is because it will need to depend on [Archers mod](https://www.curseforge.com/minecraft/mc-mods/archers), and I am waiting on [a Botania bug to be fixed](https://github.com/VazkiiMods/Botania/issues/4476)

#### *Is there a config?*
Not at this time. I'm not sure what there would be to configure that can't already be configured through datapacks.