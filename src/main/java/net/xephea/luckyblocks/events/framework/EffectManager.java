package net.xephea.luckyblocks.events.framework;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.LuckyBlocks;
import net.xephea.luckyblocks.events.EventName;
import net.xephea.luckyblocks.events.FlyingWitches;
import net.xephea.luckyblocks.events.GoToHell;
import net.xephea.luckyblocks.events.LGBSheep;
import net.xephea.luckyblocks.events.LittleShits;
import net.xephea.luckyblocks.events.LuckyEnderman;
import net.xephea.luckyblocks.events.RandomGravity;
import net.xephea.luckyblocks.events.SwordFromLightning;
import net.xephea.luckyblocks.events.Yeet;
import net.xephea.luckyblocks.utils.MessageManager;

public class EffectManager
{
	private LuckyBlocks m_Plugin;
	private ArrayList<ILuckyBlockEffect> m_FXList;
	
	public EffectManager(LuckyBlocks plugin)
	{
		m_Plugin = plugin;
		
		// Register effects
		m_FXList = new ArrayList<ILuckyBlockEffect>();
		m_FXList.add(new EventName());
		m_FXList.add(new LuckyEnderman());
		m_FXList.add(new GoToHell());
		m_FXList.add(new LGBSheep());
		m_FXList.add(new FlyingWitches());
		m_FXList.add(new RandomGravity(m_Plugin));
		m_FXList.add(new Yeet());
		m_FXList.add(new LittleShits());
		m_FXList.add(new SwordFromLightning());
		
		// Attempt bug detection
		for (ILuckyBlockEffect effect : m_FXList)
		{
			if (effect.MaximumLuck() < effect.MinimumLuck())
			{
				plugin.getLogger().warning("Bad Luck values at '" + effect.getClass().getSimpleName() + "'");
			}
		}
	}
	
	public void PerformEffect(int luck, Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		ArrayList<ILuckyBlockEffect> shortList = new ArrayList<ILuckyBlockEffect>();
		for (ILuckyBlockEffect effect : m_FXList)
		{
			if (effect.MinimumLuck() <= luck && effect.MaximumLuck() >= luck)
			{
				shortList.add(effect);
			}
		}
		
		if (shortList.isEmpty())
		{
			MessageManager.Send(player, "...nothing happened.");
			m_Plugin.getLogger().info("Nothing happened");
			return;
		}
		
		int effectIndex = (int)Math.floor(Math.random() * shortList.size());
		ScheduleEffect(shortList.get(effectIndex), origin, player, lbFactory);
	}
	
	private void ScheduleEffect(final ILuckyBlockEffect effect, final Location origin, final Player player, final ILuckyBlockFactory lbFactory)
	{
		BukkitRunnable effectTask = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				try
				{
					effect.Do(origin, player, lbFactory);
					m_Plugin.getLogger().info("Performing '" + effect.getClass().getSimpleName() + "'");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					MessageManager.Send(player, "That lucky block broke!\nPlease direct all complaints to " + effect.Author() + " :)");
				}
			}
		};
		
		effectTask.runTaskLater(m_Plugin, 0L);
	}
	
	public ArrayList<ILuckyBlockEffect> GetList()
	{
		return m_FXList;
	}
}
