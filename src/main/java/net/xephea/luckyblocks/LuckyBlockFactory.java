package net.xephea.luckyblocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

public class LuckyBlockFactory implements ILuckyBlockFactory
{
	private LuckyBlocks m_Plugin;
	
	private final String m_LuckKeyName = "LUCKYBLOCK_LUCK";
	private NamespacedKey m_LuckKey;
	private final PersistentDataType<Integer, Integer> PDType = PersistentDataType.INTEGER;
	
	private ItemStack m_LuckyBlockItem;
	
	public LuckyBlockFactory(LuckyBlocks plugin)
	{
		m_Plugin = plugin;
		m_LuckKey = new NamespacedKey(plugin, m_LuckKeyName);

		CreateLuckyBlockItemTemplate();
	}
	
	private void CreateLuckyBlockItemTemplate()
	{
		m_LuckyBlockItem = new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA);
		ItemMeta luckyBlockMeta = m_LuckyBlockItem.getItemMeta();
		luckyBlockMeta.setDisplayName(ChatColor.GOLD + "Lucky Block");
		ArrayList<String> loreLines = new ArrayList<String>();
		loreLines.add(GetLuckValueFlavourText(0));
		luckyBlockMeta.setLore(loreLines);
		luckyBlockMeta.getPersistentDataContainer().set(m_LuckKey, PDType, 0);
		m_LuckyBlockItem.setItemMeta(luckyBlockMeta);
	}
	
	// --- Begin interface --- //
	
	public ItemStack GetLuckyBlockItem(int amount, int luck)
	{
		ItemStack items = new ItemStack(m_LuckyBlockItem);
		items.setAmount(amount);
		ItemMeta itemMeta = items.getItemMeta();
		itemMeta.getPersistentDataContainer().set(m_LuckKey, PDType, luck);
		
		List<String> loreLines = itemMeta.getLore();
		loreLines.set(0, GetLuckValueFlavourText(luck));
		itemMeta.setLore(loreLines);
		
		items.setItemMeta(itemMeta);
		return items;
	}
	
	public Block MakeLuckyBlock(Block block, int luck)
	{
		block.setMetadata(m_LuckKeyName, new FixedMetadataValue(m_Plugin, luck));
		return block;
	}
	
	public Block MakeLuckyBlock(Block block, ItemStack luckyBlockItem)
	{
		block.setMetadata(m_LuckKeyName, new FixedMetadataValue(m_Plugin, GetLuck(luckyBlockItem)));
		return block;
	}
	
	public boolean IsLuckyBlock(Block block)
	{
		return block.hasMetadata(m_LuckKeyName);
	}
	
	public boolean IsLuckyBlock(ItemStack item)
	{
		if (!item.hasItemMeta())
		{
			return false;
		}
		ItemMeta meta = item.getItemMeta();
		
		return meta.getPersistentDataContainer().has(m_LuckKey, PDType);
	}
	
	public int GetLuck(ItemStack luckyBlockItem)
	{
		if (!IsLuckyBlock(luckyBlockItem))
		{
			return 0;
		}
		
		int luck = luckyBlockItem.getItemMeta().getPersistentDataContainer().get(m_LuckKey, PDType);
		luck = Math.max(-100, Math.min(100, luck));
		
		return luck;
	}
	
	public int GetLuck(Block luckyBlock)
	{
		if (!IsLuckyBlock(luckyBlock))
		{
			return 0;
		}
		
		int luck;
		try
		{
			luck = luckyBlock.getMetadata(m_LuckKeyName).get(0).asInt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		luck = Math.max(-100, Math.min(100, luck));
		
		return luck;
	}
	
	// --- End interface --- //
	
	private String GetLuckValueFlavourText(int value)
	{
		if (value >= 0)
		{
			if (value > 25)
			{
				if (value > 50)
				{
					if (value > 95)
					{
						return  ChatColor.BLUE + "" + 
								ChatColor.MAGIC + "g" + 
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g" +
								ChatColor.MAGIC + "g";
					}
					else
					{
						return ChatColor.GREEN + "A very lucky block";
					}
				}
				else
				{
					return ChatColor.GREEN + "Quite a lucky block";
				}
			}
			else
			{
				return ChatColor.YELLOW + "A lucky block";
			}
		}
		else
		{
			if (value < -25)
			{
				if (value < -50)
				{
					if (value < -95)
					{
						return  ChatColor.BLUE + "" + 
								ChatColor.MAGIC + "b" + 
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b" +
								ChatColor.MAGIC + "b";
					}
					else
					{
						return ChatColor.RED + "A very unlucky block";
					}
				}
				else
				{
					return ChatColor.RED + "Quite an unlucky block";
				}
			}
			else
			{
				return ChatColor.YELLOW + "An unlucky block";
			}
		}
	}
}
