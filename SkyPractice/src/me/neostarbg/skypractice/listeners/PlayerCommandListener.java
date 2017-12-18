package me.neostarbg.skypractice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.SkyPractice;

public class PlayerCommandListener implements Listener
{
	SkyPractice plugin;
	
	public PlayerCommandListener(SkyPractice pl)
	{
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e)
	{
		if(!e.getPlayer().getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return;}
		for(Player p : Match.getGlobalIngame())
		{
			if(e.getPlayer().equals(p))
			{
				e.setCancelled(true);
				p.sendMessage("§cYou can't do that during a fight");
			}
		}
	}
}
