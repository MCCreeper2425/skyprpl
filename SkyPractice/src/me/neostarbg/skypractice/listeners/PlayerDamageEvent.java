package me.neostarbg.skypractice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.neostarbg.skypractice.InventoryPlayer;
import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.SkyPractice;

public class PlayerDamageEvent implements Listener
{
	private SkyPractice plugin;
	public PlayerDamageEvent(SkyPractice pl)
	{
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e)
	{
		if(!e.getEntity().getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return;}
		
		if(!(e.getDamager() instanceof Player))
		{
			return; 
		}
		if(!(e.getEntity() instanceof Player))
		{
			return;
		}
		
		Player damager = (Player) e.getDamager();
		Player damagee = (Player) e.getEntity();
		
		if(Match.isIngame(damagee) && Match.isIngame(damager))
		{
			Match m;
			boolean br = false;
			for(Match x : Match.getActiveMatches())
			{
				if(br) break;
				for(Player y : x.getPlayers())
				{
					if(y.equals(damagee) || y.equals(damager))
					{
						m = x;
						
						br = true;
						break;
					}
				}
			}
			//plugin.log("spam");
			InventoryPlayer.save(damager);
			InventoryPlayer.save(damagee);
		}
	}
}
