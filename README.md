# BotaniaCombat

BotaniaCombat seeks to create a variety of weapons using [Botania](https://www.curseforge.com/minecraft/mc-mods/botania)'s metals, and integrate Botania with [BetterCombat](https://www.curseforge.com/minecraft/mc-mods/better-combat-by-daedelus) as well as some other combat library mods.

This mod does the following:

- Adds a Dagger for Elementium and Terrasteel (Manasteel equivalent is Botania's Soulscribe)
- Adds the Soulstaff, a battlestaff version of the Soulscribe
- Adds a Spear for Elementium and Terrasteel
- If you are a patron of Vazkii, terrasteel weapons will emit mana beams with a random color from your [Psi CAD colorizer](https://github.com/VazkiiMods/Psi/blob/master/contributors.properties) colors
- Adds additional post-Gaia weapon, the Gaia Greatsword, which can use all of your Patreon colors
- Buffs player-spawned pixies by making them always have the +2 damage bonus that would normally only be applied when the player is holding the Elementium Sword
- Adds Gaia Gifts: "secret" weapon(s) obtained by defeating Gaia II under specific conditions

If [Better Combat](https://www.curseforge.com/minecraft/mc-mods/better-combat-by-daedelus) is installed, it also does the following:

- Adds offhand pixie spawn chance attribute to Elementium weapons (including Elementium Sword)
- Adds pixie spawn chance attributes to the Elementium Axe
- Makes Terrasteel weapons (including Terra Blade) shoot a beam at the start of a BetterCombat swing; also works with offhand swings
- Reduces Thundercaller attack range to 1.5 (from BetterCombat's default 2.5), electricity range is unaffected

If [Fabric Shield Lib](https://www.curseforge.com/minecraft/mc-mods/fabric-shield-lib) is installed, it also does the following:
- Adds the Manasteel Buckler
- Adds the Elementium Shield
- Adds the Terra Tower Shield
- Adds shield Gaia Gift

If [Ranged Weapon API](https://www.curseforge.com/minecraft/mc-mods/ranged-weapon-api) is installed, it also does the following:
- Adds Pull Time and Projectile Damage attributes to Botania's bows
- Adds Livingwood Crossbow
- Adds Crystal Crossbow
- Adds bow Gaia Gift

**NOTE**: To ensure that the Lexica and subtitles from Botania are accurate, this mod includes an enabled-by-default resource pack that overrides some of Botania's lang file.
This mod only supports en_us.json currently.

Credits / Thanks:
- Unilock for [cleaning up the code and fixing compat with Sinytra Connector](https://github.com/Partonetrain/botaniacombat/pull/1)
- Daedulus_dev for making Ranged Weapon API
- arkosammy12 for helping with the Elementium Axe mixin
- Adarsh and everyone else on the Violet Moon forums giving me feedback and ideas
- June (of BasicWeapons) for letting me use that mod's method for dynamic item renders in the older versions

If you like this mod, [consider supporting me on ko-fi](https://ko-fi.com/partonetrain) ^_^

## FAQ
####  *Will you make a Forge version?*
Maybe, but I am going to have to learn Architecturey first. Considering this uses a lot of Fabric libraries, I'm not sure that will happen without some outside help.

####  *Does this work with Sinytra Connector?*
It should, thanks to Unilock's PR. Support for it is lower priority than support for Fabric/Quilt.

#### *How much damage does the mana beam from the new weapons do?*
7 (or 3.5 hearts), same as Terra Blade's.

#### *Why so many optional dependencies*?
A couple of reasons, but mostly these are mods I already use myself, and I don't like doing mixin on vanilla when there's just a library I could use that does it

#### *Is there a config?*
Not at this time. I'm not sure what there would be to configure that can't already be configured through datapacks. If you need a config for something, [create an issue on the GitHub](https://github.com/Partonetrain/botaniacombat/issues).
