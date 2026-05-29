# Optometrist
A cross-platform render distance manipulation mod

# About
This mod provides a command for adjusting per-player render distances

# Usage
The `vd` command is used to set a specific view distance for a player (or group of players).
- `vd <targets> <view_distance>`

The `default_view_distance` gamerule is used to set the view distance given to a player when they join for the first time.

# Supported Platforms
- Fabric/Quilt
- Forge
- NeoForge
- PaperMC/Spigot/Folia via Ignite
- Sponge

# Installation
## Fabric, Quilt, Forge, NeoForge
Simply put the mod in the mods folder
## Sponge
Simply put the plugin in the plugins folder
## Spigot/PaperMC
1. Install the [Ignite](https://github.com/vectrix-space/ignite) Mixin loader
2. Run the ignite jar alongside the paper/spigot jar
3. Put the mod in the mods folder and restart
## Folia
1. Install the [Ignite](https://github.com/vectrix-space/ignite) Mixin loader
2. Rename the Folia jar to "paper.jar". Alternatively, you can launch the game with the following JVM args: `-Dignite.locator=paper -Dignite.jar=./folia.jar`
3. Run the ignite jar alongside the folia jar
4. Put the mod in the mods folder and restart
