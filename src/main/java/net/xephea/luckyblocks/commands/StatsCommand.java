package net.xephea.luckyblocks.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.xephea.luckyblocks.commands.framework.SubCommand;
import net.xephea.luckyblocks.events.framework.EffectManager;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;
import net.xephea.luckyblocks.utils.MessageManager;

public class StatsCommand implements SubCommand
{
	private EffectManager m_EffectManager;
	
	public StatsCommand(EffectManager effectMgr)
	{
		m_EffectManager = effectMgr;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		Map<String, Integer> authorTotals = new HashMap<String, Integer>();
		
		for (ILuckyBlockEffect e : m_EffectManager.GetList())
		{
			if (!authorTotals.containsKey(e.Author()))
			{
				authorTotals.put(e.Author(), 0);
			}
			
			int currTotal = authorTotals.get(e.Author());
			authorTotals.put(e.Author(), currTotal + 1);
		}
		
		int totalEffects = 0;
		MessageManager.Send(sender, "Contributions by author: ");
		for (String author : authorTotals.keySet())
		{
			int total = authorTotals.get(author);
			sender.sendMessage(ChatColor.GOLD + author + ChatColor.RESET + " | " + total);
			totalEffects += total;
		}
		MessageManager.Send(sender, "Total: " + totalEffects);
		
		return true;
	}

	@Override
	public String help()
	{
		return "/luckyblocks stats";
	}

	@Override
	public String permission()
	{
		return "*";
	}

	@Override
	public ArrayList<String> suggestions(int numArgs)
	{
		return new ArrayList<String>();
	}
}
