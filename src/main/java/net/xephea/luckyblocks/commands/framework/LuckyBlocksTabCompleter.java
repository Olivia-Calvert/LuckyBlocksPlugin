package net.xephea.luckyblocks.commands.framework;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class LuckyBlocksTabCompleter implements TabCompleter
{
	private CommandManager m_CommandManager;
	
	public LuckyBlocksTabCompleter(CommandManager cmdMgr)
	{
		m_CommandManager = cmdMgr;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args)
	{
		ArrayList<String> suggestions = new ArrayList<String>();
		
		if (!cmd.getName().equalsIgnoreCase("luckyblocks"))
		{
			return suggestions;
		}
		
		if (args.length == 1)
		{
			ArrayList<String> commands = new ArrayList<String>(m_CommandManager.GetCommands().keySet());
			for (String c : commands)
			{
				if (c.startsWith(args[0]))
				{
					suggestions.add(c);
				}
			}
		}
		else if (args.length > 1)
		{
			ArrayList<String> cmdArgs = m_CommandManager.GetCommands().get(args[0]).suggestions(args.length);
			if (cmdArgs == null)
			{
				return suggestions;
			}
			
			for (String arg : cmdArgs)
			{
				if(arg.startsWith(args[args.length - 1]))
				{
					suggestions.add(arg);
				}
			}
		}
		
		return suggestions;
	}
}
