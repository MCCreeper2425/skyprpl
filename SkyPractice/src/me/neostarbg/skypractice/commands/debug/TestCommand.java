package me.neostarbg.skypractice.commands.debug;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.Queue;
import me.neostarbg.skypractice.SkyPractice;

public class TestCommand implements CommandExecutor
{
	private SkyPractice plugin; 
	public TestCommand(SkyPractice pl)
	{
		plugin = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			for(Player p :Bukkit.getOnlinePlayers())
			{
				Bukkit.dispatchCommand(p, "test nodebuff");
			}
			return true;
		}
		Player player = (Player) sender;
		if(!player.getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return true;}
		
		if(args[0].equalsIgnoreCase("debug"))
		{
			if(args[1].equalsIgnoreCase("true"))
			{
				plugin.debug_enabled = true;
			}
			else
			{
				plugin.debug_enabled = false;
			}
			
			return true;
		}
		
		if(player.getName().equalsIgnoreCase("NeoStarBG"))
		{
			plugin.log("Creating match");
			Match m = new Match(plugin);
			plugin.log("Player 1 is "+player.getName());
			m.setPlayer1(player);
			plugin.log("Player 2 is Skyzao");
			m.setPlayer2(Bukkit.getPlayer("Skyzao"));
			plugin.log("Kit is "+args[0]);
			m.setKit(args[0]);
			//m.setArena(Queue.getFreeArena());
			plugin.log("Queing match");
			Queue.queueMatch(m);
		}
		
		//Debug begins here
		plugin.log("Queued for match");
		Queue.queuePlayer(player, args[0].toLowerCase());
		
		return true;
	}

}
