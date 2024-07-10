package net.xephea.luckyblocks.events;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import net.xephea.luckyblocks.ILuckyBlockFactory;
import net.xephea.luckyblocks.events.framework.ILuckyBlockEffect;

public class LuckyEnderman implements ILuckyBlockEffect
{
	@Override
	public void Do(Location origin, Player player, ILuckyBlockFactory lbFactory)
	{
		int reddie = (int) (Math.random() * 4.0 + 1.0);
		for (int i = 0; i < reddie; i++) 
		{
			int luck = (int) (Math.random() * 200.0 - 100.0);
			ItemStack luckyBlock = lbFactory.GetLuckyBlockItem(1, luck);
			
			Enderman eddie = (Enderman) origin.getWorld().spawnEntity(origin, EntityType.ENDERMAN);
			eddie.setCarriedMaterial(luckyBlock.getData());
			eddie.setTarget(player);
			
			EntityEquipment eddiesEquipment = eddie.getEquipment();
			eddiesEquipment.setItemInMainHand(luckyBlock);
			eddiesEquipment.setItemInMainHandDropChance(1.0f);
			
			eddie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60.0);
			eddie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0);
			eddie.setHealth(60.0f);
		}
	}

	@Override
	public int MinimumLuck()
	{
		return -15;
	}

	@Override
	public int MaximumLuck()
	{
		return 15;
	}

	@Override
	public String Author()
	{
		return "Jasmine";
	}
}
