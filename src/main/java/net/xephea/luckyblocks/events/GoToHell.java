package net.xephea.luckyblocks.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class GoToHell implements ILuckyBlockEffect
{
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		// Find hell
		String hellWorldName = player.getLocation().getWorld().getName() + "_nether";
		Location destination = Bukkit.getWorld(hellWorldName).getSpawnLocation();
		
		// Ensure safety
		Block blockBelow = destination.getBlock().getRelative(BlockFace.DOWN);
		if (blockBelow.getType() == Material.AIR ||
			blockBelow.getType() == Material.LAVA)
		{
			blockBelow.setType(Material.NETHERRACK);
		}
		
		// Send to hell
		player.teleport(destination);	
		
		// Give items to return
		ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
		obsidian.setAmount(10);
		ItemStack lighter = new ItemStack(Material.FIRE_CHARGE);
		lighter.setAmount(1);
		
		player.getInventory().addItem(obsidian);
		player.getInventory().addItem(lighter);
	}

	@Override
	public int MinimumLuck()
	{
		return -25;
	}

	@Override
	public int MaximumLuck()
	{
		return -15;
	}

	@Override
	public String Author()
	{
		return "Olivia";
	}
}
