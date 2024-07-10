package net.xephea.luckyblocks.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.LuckyBlocks;

public class LuckyBlockPlaceBreakListener implements Listener
{
	private LuckyBlocks m_Plugin;
	private ILuckyBlockFactory m_LuckyBlockFactory;
	
	public LuckyBlockPlaceBreakListener(LuckyBlocks plugin, ILuckyBlockFactory luckyBlockFactory)
	{
		m_Plugin = plugin;
		m_LuckyBlockFactory = luckyBlockFactory;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		// Check is lucky block
		if (!m_LuckyBlockFactory.IsLuckyBlock(event.getItemInHand()))
		{
			return;
		}
		
		// Try add luck to block meta
		m_LuckyBlockFactory.MakeLuckyBlock(event.getBlockPlaced(), event.getItemInHand());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		// Check is lucky block
		if (!m_LuckyBlockFactory.IsLuckyBlock(event.getBlock()))
		{
			return;
		}

		// Try get luck
		int luck = m_LuckyBlockFactory.GetLuck(event.getBlock());
		
		// Calculate range based on equation
		double range = -0.75 * Math.abs(luck) + 50.0;
		
		// Fuzzy the value
		double eventValue = (luck - range) + Math.random() * range * 2.0;
		eventValue = Math.max(-100, Math.min(100, eventValue));

		// Handle the event
		event.getBlock().setType(Material.AIR);
		event.setCancelled(true);
		m_Plugin.getLogger().info("Broke lucky block | Value = " + eventValue);
		
		// Perform the effect
		m_Plugin.EffectManager.PerformEffect((int)eventValue, event.getBlock().getLocation(), event.getPlayer(), m_LuckyBlockFactory);
	}
}
