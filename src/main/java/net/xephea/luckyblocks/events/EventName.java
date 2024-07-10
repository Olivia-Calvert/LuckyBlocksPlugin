package net.xephea.luckyblocks.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class EventName implements ILuckyBlockEffect
{

	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		// TODO The thing
	}

	@Override
	public int MinimumLuck()
	{
		// TODO Minimum luck to trigger
		return -100;
	}

	@Override
	public int MaximumLuck()
	{
		// TODO Maximum luck to trigger
		return 100;
	}

	@Override
	public String Author()
	{
		// TODO Write my name here
		return "Olivia";
	}

}
