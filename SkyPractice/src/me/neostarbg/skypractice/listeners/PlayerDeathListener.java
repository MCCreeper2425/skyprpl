package me.neostarbg.skypractice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.SkyPractice;
import me.neostarbg.skypractice.Util;

public class PlayerDeathListener implements Listener
{
	private SkyPractice plugin;
	public PlayerDeathListener(SkyPractice pl) {plugin = pl;}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		if(!e.getEntity().getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return;}
		
		e.setDeathMessage("");
		e.setKeepInventory(true);
		
		plugin.log(e.getEntity().getName()+" died");
		
		if(e.getEntity() instanceof Player)
		{
			Player victim = (Player) e.getEntity();
			
			for(Player p : Match.getGlobalIngame())
			{
				if(p.equals(victim))
				{
					plugin.log("victim found in global variable");
					for(Match m : Match.getActiveMatches())
					{
						for(Player pl : m.getPlayers())
						{
							if(pl.equals(victim))
							{
								plugin.log("Match found, starting m.finish(victim);");
								m.finish(victim);
							}
						}
					}
				}
			}
		}
	}
}
