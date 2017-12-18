package me.neostarbg.skypractice.commands.admin;

import org.bukkit.entity.Player;

import me.neostarbg.skypractice.Gametype;
import me.neostarbg.skypractice.SkyPractice;

public class GametypeCommand 
{
	
	private static SkyPractice plugin;
	private static Player player;
	
	public static boolean gtc(String[] args, Player p, SkyPractice pl)
	{
		plugin = pl; 
		player = p;
		
		new Gametype(plugin);
		
		if(args.length > 2)
		{
			if(args[2].equalsIgnoreCase("savekit"))
			{
				//pradmin gametype[0] name[1] savekit[2]
				//Set items to current inventory
				
				Gametype.saveKit(args[1], p, true);
				return true;
			}
			if(args[2].equalsIgnoreCase("loadkit"))
			{
				//Give player current items
				Gametype.giveKit(args[1], p, true);
				return true;
			}
			if(args[2].equalsIgnoreCase("display"))
			{
				//Set display icon to item currently being held
				return true;
			}
		}
		return true;
	}

	
	
}
