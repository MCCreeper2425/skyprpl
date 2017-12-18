package me.neostarbg.skypractice.commands.admin;

import org.bukkit.entity.Player;

import me.neostarbg.skypractice.SkyPractice;
import me.neostarbg.skypractice.Util;

public class ArenaCommand 
{
	public static boolean arenaCommand(String[] args, Player sender, SkyPractice plugin)
	{
		//pradmin arena[0] name[1] p1[2]
		
		Player player = sender;
		
		if(args.length < 2)
		{
			sender.sendMessage(HelpCommand.helpCommand(HelpCommand.ARENA));
			return true;
		}
		
		if(args[2].equals("p1"))
		{
			Util.setLocation(plugin, "Arenas."+args[1]+".p1", player.getLocation());
			player.sendMessage("§aPlayer 1 position set");
			return true;
		}
		if(args[2].equals("p2"))
		{
			Util.setLocation(plugin, "Arenas."+args[1]+".p2", player.getLocation());
			player.sendMessage("§aPlayer 2 position set");
			return true;
		}
		return true;
	}
}
