package me.neostarbg.skypractice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neostarbg.skypractice.SkyPractice;
import me.neostarbg.skypractice.Util;
import me.neostarbg.skypractice.commands.admin.ArenaCommand;
import me.neostarbg.skypractice.commands.admin.GametypeCommand;
import me.neostarbg.skypractice.commands.admin.HelpCommand;

public class AdminCommand implements CommandExecutor
{
	SkyPractice plugin;
	public AdminCommand(SkyPractice pl)
	{
		plugin = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		if(!(sender instanceof Player))
		{
			return true;
		}
		Player player = (Player) sender;
		if(!player.getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return true;}
		if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("arena"))
			{
				ArenaCommand.arenaCommand(args, player, plugin);
				return true;
			}
			if(args[0].equalsIgnoreCase("gametype"))
			{
				GametypeCommand.gtc(args, player, plugin);
				return true;
			}
			if(args[0].equalsIgnoreCase("setlobby"))
			{
				Util.setLocation(plugin, "Lobby", player.getLocation());
				player.sendMessage("§aLobby set");
				return true;
			}
		}
		sender.sendMessage(HelpCommand.helpCommand(HelpCommand.ADMIN));
		return true;
	}

	
}
