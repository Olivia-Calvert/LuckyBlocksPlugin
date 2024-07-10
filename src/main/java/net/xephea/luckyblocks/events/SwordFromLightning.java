package net.xephea.luckyblocks.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class SwordFromLightning implements ILuckyBlockEffect
{
	private ItemStack m_Sword;
	private final String Name = ChatColor.BLUE + "A Sword Compensating For Something";
	private final String Lore = "Legend has it that the carrier of this sword has a really tiny penis...";
	
	public SwordFromLightning()
	{
		// Make the sword
		m_Sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta swordMeta = m_Sword.getItemMeta();
		swordMeta.setDisplayName(Name);
		swordMeta.setLore(new ArrayList<String>());
		swordMeta.getLore().add(Lore);
		swordMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, 
				new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", -0.3, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND));
		
		m_Sword.setItemMeta(swordMeta);
		m_Sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		m_Sword.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 10);
		m_Sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	}
	
	
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		World world = origin.getWorld();
		
		// Do the lightning
		world.strikeLightningEffect(origin);
		
		// Do the sword
		world.dropItem(origin, m_Sword);
	}

	@Override
	public int MinimumLuck()
	{
		return 60;
	}

	@Override
	public int MaximumLuck()
	{
		return 80;
	}

	@Override
	public String Author()
	{
		return "Olivia";
	}
}
