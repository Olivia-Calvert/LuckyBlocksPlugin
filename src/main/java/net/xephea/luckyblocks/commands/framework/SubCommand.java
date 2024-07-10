package net.xephea.luckyblocks.commands.framework;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public interface SubCommand
{
	public boolean onCommand(CommandSender sender, String[] args);
	
	public String help();
	
	public String permission();
	
	public ArrayList<String> suggestions(int numArgs);
}
