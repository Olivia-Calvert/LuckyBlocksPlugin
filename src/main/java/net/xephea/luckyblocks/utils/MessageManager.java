package net.xephea.luckyblocks.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.EnumWrappers.ChatType;

public class MessageManager
{
	// Singleton instance
	public static MessageManager instance = new MessageManager();
	public static MessageManager getInstance() {
		return instance;
	}

	// Initialisation
	private static ProtocolManager m_ProtocolManager;
	public enum PrefixType { DEFAULT, WARNING, ERROR };
	private static HashMap<PrefixType, String> m_Prefixes;

	private MessageManager()
	{
		m_ProtocolManager = ProtocolLibrary.getProtocolManager();

		m_Prefixes = new HashMap<PrefixType, String>();
		m_Prefixes.put(PrefixType.DEFAULT, ChatColor.DARK_RED + "[" + ChatColor.GOLD + "Lucky Blocks" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " ");
		m_Prefixes.put(PrefixType.WARNING, ChatColor.BOLD + "" + ChatColor.GOLD + "[" + ChatColor.YELLOW + "Lucky Blocks" + ChatColor.GOLD + "]" + ChatColor.RESET + " ");
		m_Prefixes.put(PrefixType.ERROR, ChatColor.BOLD + "" + ChatColor.DARK_RED + "[" + ChatColor.RED + "Lucky Blocks" + ChatColor.DARK_RED + "]" + ChatColor.RESET + "" + ChatColor.RED + " ");
	}

	// Send messages
	public static void Send(CommandSender recipient, String message)
	{
		recipient.sendMessage(m_Prefixes.get(PrefixType.DEFAULT) + message);
	}
	public static void Send(CommandSender recipient, PrefixType prefix, String message)
	{
		recipient.sendMessage(m_Prefixes.get(prefix) + message);
	}

	public static void SendActionBarText(Player player, String message)
	{
		PacketContainer packet = m_ProtocolManager.createPacket(PacketType.Play.Server.CHAT);
		packet.getChatComponents().write(0, WrappedChatComponent.fromJson("{\"text\": \"" + message + "\"}"));
		packet.getChatTypes().write(0, ChatType.GAME_INFO);

		try
		{
			m_ProtocolManager.sendServerPacket(player, packet);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			Send(player, message);
		}
	}

	// Prefixes
	public static String GetPrefix(PrefixType type)
	{
		return m_Prefixes.get(type);
	}

	// Formatting
	public static String Format(Location loc)
	{
		return loc.getX() + ", " + loc.getY() + ", " + loc.getZ();
	}
}
