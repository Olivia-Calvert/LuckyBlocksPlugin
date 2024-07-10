package net.xephea.luckyblocks.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.LuckyBlocks;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class RandomGravity implements ILuckyBlockEffect
{
	private LuckyBlocks m_Plugin;
	
	private final double MinInterval = 5.0;
	private final double MaxInterval = 10.0;
	
	private final double MinDuration = 30.0;
	private final double MaxDuration = 60.0;
	
	public RandomGravity(LuckyBlocks plugin)
	{
		m_Plugin = plugin;
	}
	
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		player.sendMessage("<???> You look a bit light-headed...");

		// Create task to occasionally flip gravity
		BukkitRunnable flipGravity = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				// Flip gravity
				player.setGravity(!player.hasGravity());
			}
		};
		
		// Create task to return gravity to normal when done
		BukkitRunnable returnGravity = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				// Cancel flip task
				flipGravity.cancel();
				
				// Give gravity back to player
				player.setGravity(true);
				player.sendMessage("<???> All better now?");
			}
		};
		
		// 20 ticks per second
		final double ticksPerSecond = 20.0;

		// Determine random interval
		long interval = (long) (Math.random() * (ticksPerSecond * (MaxInterval - MinInterval)) + (ticksPerSecond * MinInterval));
		
		// Determine random duration
		long duration = (long) (Math.random() * (ticksPerSecond * (MaxDuration - MinDuration)) + (ticksPerSecond * MinDuration));
		
		// Schedule tasks
		flipGravity.runTaskTimer(m_Plugin, 0L, interval);
		returnGravity.runTaskLater(m_Plugin, duration);
	}

	@Override
	public int MinimumLuck()
	{
		return -10;
	}

	@Override
	public int MaximumLuck()
	{
		return 0;
	}

	@Override
	public String Author()
	{
		return "Olivia";
	}
}
