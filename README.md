# LuckyBlocks
_(last update Nov-19)_

Lucky Blocks is a plugin for Minecraft Servers.

The blocks can be spawned in by a server command and placed as normal.

Once broken, a random effect will occur based on the 'luck' value of the block.

## Adding Effects

For convenience, a template effect has been added `EventName.java` in `src/main/java/net/xephea/luckyblocks/events`

1. Create a new file in package `src/main/java/net/xephea/luckyblocks/events` with a unique effect name
2. Implement the `ILuckyBlockEffect` interface
3. Complete the functions for your effect
4. Test it
	- Open `EffectManager.java`
	- Ensure your effect is the only one added to the `fxList` variable
	- Build the plugin and test on a local server
	- Undo all changes to `EffectManager.java` other than adding your effect
5. Create a pull request
