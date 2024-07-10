package net.xephea.luckyblocks.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class FlyingWitches implements ILuckyBlockEffect
{
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		World world = origin.getWorld();
		
		int numToSpawn = (int) (Math.random() * 4.0 + 8.0);
		for (int i = 0; i < numToSpawn; i++)
		{
			// Spawn bat
			Bat bat = (Bat) world.spawnEntity(origin, EntityType.BAT);
			bat.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1.0);
			
			// Spawn witch
			Witch witch = (Witch) world.spawnEntity(origin, EntityType.WITCH);
			witch.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
			witch.setTarget(player);
			
			// Put witch on bat
			bat.addPassenger(witch);
		}
	}

	@Override
	public int MinimumLuck()
	{
		return -60;
	}

	@Override
	public int MaximumLuck()
	{
		return -45;
	}

	@Override
	public String Author()
	{
		return "Olivia";
	}

}
