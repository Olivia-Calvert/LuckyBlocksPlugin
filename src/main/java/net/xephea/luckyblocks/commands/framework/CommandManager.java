package net.xephea.luckyblocks.commands.framework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.xephea.luckyblocks.LuckyBlocks;
import net.xephea.luckyblocks.commands.GetCommand;
import net.xephea.luckyblocks.commands.StatsCommand;
import net.xephea.luckyblocks.utils.MessageManager;
import net.xephea.luckyblocks.utils.MessageManager.PrefixType;

public class CommandManager implements CommandExecutor
{
	private LuckyBlocks m_Plugin;
	private HashMap<String, SubCommand> m_Commands;
	
	public CommandManager(LuckyBlocks plugin)
	{
		m_Plugin = plugin;
		LoadCommands();
	}
	
	private void LoadCommands()
	{
		m_Commands = new HashMap<String, SubCommand>();
		m_Commands.put("get", new GetCommand(m_Plugin));
		m_Commands.put("stats", new StatsCommand(m_Plugin.EffectManager));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (command.getName().equalsIgnoreCase("luckyblocks"))
		{
			// No arguments - Show version
			if (args == null || args.length < 1)
			{
				MessageManager.Send(sender, "Version " + m_Plugin.getDescription().getVersion());
				return true;
			}
			
			// Extract sub-command + sub-arguments
			String subCommand = args[0];
			Vector<String> subArgs = new Vector<String>(Arrays.asList((args)));
			subArgs.remove(0);
			
			// Check if sub-command exists
			if (!m_Commands.containsKey(subCommand))
			{
				MessageManager.Send(sender, PrefixType.WARNING, "That command does not exist");
				MessageManager.Send(sender, "Use /luckyblocks help for more information");
				return true;
			}
			
			// Try run sub-command
			try 
			{
				SubCommand cmd = m_Commands.get(subCommand.toLowerCase());
				if (!sender.hasPermission(cmd.permission()) && !sender.isOp())
				{
					MessageManager.Send(sender, PrefixType.WARNING, "You are not allowed to do that");
					return true;
				} 
				
				if (!cmd.onCommand(sender, (String[])subArgs.toArray(new String[0])))
				{
					MessageManager.Send(sender, cmd.help());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				MessageManager.Send(sender, PrefixType.ERROR, "Unexpected error.  Check the console for more info");
				return true;
			}
		}
		
		return true;
	}
	
	public HashMap<String, SubCommand> GetCommands()
	{
		return m_Commands;
	}
}
