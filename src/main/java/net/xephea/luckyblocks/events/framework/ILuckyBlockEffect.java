package net.xephea.luckyblocks.events.framework;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.xephea.luckyblocks.ILuckyBlockFactory;

public interface ILuckyBlockEffect
{
	// Perform the effect
	// origin = The origin of the effect - where the block was broken
	// player = The player that opened the block
	// lbFactory = Provides access to create more lucky blocks, verify lucky blocks or get luck from lucky blocks
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory);
	
	// The minimum luck required for this effect
	// Range -100 <-> 100
	public int MinimumLuck();
	
	// The maximum luck required for this effect
	// Range -100 <-> 100
	public int MaximumLuck();
	
	// Your name for stats
	public String Author();
}
