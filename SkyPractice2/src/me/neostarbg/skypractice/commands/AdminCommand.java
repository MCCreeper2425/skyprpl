package me.neostarbg.skypractice.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neostarbg.skypractice.Config;
import me.neostarbg.skypractice.SkyPractice;
import me.neostarbg.skypractice.Util;

public class AdminCommand implements CommandExecutor
{
	private SkyPractice plugin;
	public AdminCommand(SkyPractice pl)
	{
		plugin = pl; 
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		
		if(!(player.isOp())) return true;
		
		Config config = plugin.getC();
		if(args[0].equalsIgnoreCase("reload"))
		{
			
			config.reload();
			player.sendMessage("§aReloaded");
			return true; 
		}
		if(args[0].equalsIgnoreCase("arena"))
		{
			String name = args[1];
			if(args[2].equalsIgnoreCase("p1"))
			{
				Location l = player.getLocation();
				Util.setLocation(plugin, "Arenas."+name+".p1", l, config.getArenas(), config.getArenasfile());
				player.sendMessage("§aSuccess");
				return true;
			}
			if(args[2].equalsIgnoreCase("p2"))
			{
				Location l = player.getLocation();
				Util.setLocation(plugin, "Arenas."+name+".p2", l, config.getArenas(), config.getArenasfile());
				player.sendMessage("§aSuccess");
				return true;
			}
		}
		return true;
	}
}
