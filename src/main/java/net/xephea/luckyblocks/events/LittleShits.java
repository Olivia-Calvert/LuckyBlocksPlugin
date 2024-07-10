package net.xephea.luckyblocks.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class LittleShits implements ILuckyBlockEffect
{
	private final double MinToSpawn = 12.0;
	private final double RangeToSpawn = 3.0;
	
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		World world = origin.getWorld();
		
		int numToSpawn = (int) (Math.random() * RangeToSpawn + MinToSpawn);
		for (int i = 0 ; i < numToSpawn; i++)
		{
			Silverfish silverfish = (Silverfish) world.spawnEntity(origin, EntityType.SILVERFISH);
			silverfish.setCustomName(ChatColor.GRAY + "Evil Little Shite #" + i);
			silverfish.setCustomNameVisible(true);
			silverfish.setTarget(player);
		}
	}

	@Override
	public int MinimumLuck()
	{
		return -30;
	}

	@Override
	public int MaximumLuck()
	{
		return -20;
	}

	@Override
	public String Author()
	{
		return "Olivia"; 
	}
}
