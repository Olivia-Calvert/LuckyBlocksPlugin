package net.xephea.luckyblocks.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EndermanDeathListener implements Listener
{
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{
		if (e.getEntity().getType() == EntityType.ENDERMAN)
		{
			for (int i = 0; i < e.getDrops().size();)
			{
				if (!e.getDrops().get(i).hasItemMeta())
				{
					e.getDrops().remove(i);
				}
				else
				{
					i++;
				}
			}
		}
	}
}
