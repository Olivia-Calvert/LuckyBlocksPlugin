package net.xephea.luckyblocks.commands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.xephea.luckyblocks.LuckyBlocks;
import net.xephea.luckyblocks.commands.framework.SubCommand;
import net.xephea.luckyblocks.utils.MessageManager;
import net.xephea.luckyblocks.utils.MessageManager.PrefixType;

public class GetCommand implements SubCommand
{
	private LuckyBlocks m_Plugin;

	public GetCommand(LuckyBlocks plugin)
	{
		m_Plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String[] args)
	{
		if (!(sender instanceof Player))
		{
			MessageManager.Send(sender, PrefixType.ERROR, "Only players can perform that command");
			return false;
		}
		
		int total = 1;
		int luck = 0;
		if (args.length > 0)
		{
			try
			{
				total = Integer.valueOf(args[0]);
			}
			catch (NumberFormatException e)
			{
				MessageManager.Send(sender, PrefixType.WARNING, args[0] + " is not a number");
				return false;
			}
			
			if (args.length > 1)
			{
				try
				{
					luck = Integer.valueOf(args[1]);
				}
				catch (NumberFormatException e)
				{
					MessageManager.Send(sender, PrefixType.WARNING, args[1] + " is not a number");
					return false;
				}
			}
		}
		
		Player player = (Player)sender;
		
		ItemStack luckyBlocks = m_Plugin.LuckyBlockFactory.GetLuckyBlockItem(total, luck);
		player.getInventory().addItem(luckyBlocks);
		
		MessageManager.Send(player, "Added " + total + " lucky blocks (" + luck + ") to your inventory!");
		return true;
	}

	@Override
	public String help()
	{
		return "/luckyblocks get [amount] [luck]";
	}

	@Override
	public String permission()
	{
		return "luckyblocks.cheat";
	}

	@Override
	public ArrayList<String> suggestions(int numArgs)
	{
		return new ArrayList<String>();
	}

}
