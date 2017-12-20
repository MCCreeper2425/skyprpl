package me.neostarbg.skypractice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neostarbg.skypractice.Config;
import me.neostarbg.skypractice.SkyPractice;

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
		
		if(args[0].equalsIgnoreCase("reload"))
		{
			Config config = plugin.getC();
			config.reload();
			player.sendMessage("§aReloaded");
		}
		return true;
	}
}
