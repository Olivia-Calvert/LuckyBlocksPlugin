package net.xephea.luckyblocks.events;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class LGBSheep implements ILuckyBlockEffect
{
	private final String SheepName = ChatColor.RED + "T" 
								   + ChatColor.GOLD + "h" 
								   + ChatColor.YELLOW + "e" 
								   + ChatColor.RESET + " " 
								   + ChatColor.GREEN + "L" 
								   + ChatColor.AQUA + "G" 
								   + ChatColor.BLUE + "B" 
								   + ChatColor.LIGHT_PURPLE + "S" 
								   + ChatColor.RED + "h" 
								   + ChatColor.GOLD + "e"
								   + ChatColor.YELLOW + "e" 
								   + ChatColor.GREEN + "p"; 
	private final int SheepToSpawn = 150;
	
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		for (int i = 0; i < SheepToSpawn; i++)
		{
			Sheep sheep = (Sheep) origin.getWorld().spawnEntity(origin, EntityType.SHEEP);
			sheep.setCustomName(SheepName);
			sheep.setCustomNameVisible(true);
			sheep.setColor(RandomiseDye());
		}
	}
	
	private DyeColor RandomiseDye()
	{
		int rand = (int) (Math.random() * 15.0);
		return DyeColor.values()[rand];
	}

	@Override
	public int MinimumLuck()
	{
		return -30;
	}

	@Override
	public int MaximumLuck()
	{
		return 30;
	}

	@Override
	public String Author()
	{
		return "Olivia";
	}
}
