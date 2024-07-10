package net.xephea.luckyblocks;

import org.bukkit.plugin.java.JavaPlugin;

import net.xephea.luckyblocks.commands.framework.CommandManager;
import net.xephea.luckyblocks.commands.framework.LuckyBlocksTabCompleter;
import net.xephea.luckyblocks.events.framework.EffectManager;
import net.xephea.luckyblocks.listeners.EndermanDeathListener;
import net.xephea.luckyblocks.listeners.LuckyBlockPlaceBreakListener;

public final class LuckyBlocks extends JavaPlugin
{
	public LuckyBlockFactory LuckyBlockFactory;
	public EffectManager EffectManager;
	
	@Override
	public void onEnable()
	{
		// Initialise THE BLOCK!
		LuckyBlockFactory = new LuckyBlockFactory(this);
		
		// Load database
		EffectManager = new EffectManager(this);
		
		// Setup commands
		CommandManager cmdMgr = new CommandManager(this);
		getCommand("luckyblocks").setExecutor(cmdMgr);
		getCommand("luckyblocks").setTabCompleter(new LuckyBlocksTabCompleter(cmdMgr));
		
		// Setup events
		getServer().getPluginManager().registerEvents(new LuckyBlockPlaceBreakListener(this, LuckyBlockFactory), this);
		getServer().getPluginManager().registerEvents(new EndermanDeathListener(), this);
		
		// Log completion
		getLogger().info("Initialised successfully");
	}
}