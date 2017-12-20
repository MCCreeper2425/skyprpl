package me.neostarbg.skypractice.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.neostarbg.skypractice.Config;
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
		
		Config config = plugin.getC();
		
		if(args.length == 0) 
		{
			plugin.debug(config.getConfig().getString("hello"));
			return true;
		}
		
		String word = "";
		for(String a : args)
		{
			word += a + " ";
		}
		
		config.getConfig().set("hello", word);
		try {
			config.getConfig().save(config.getConfigfile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plugin.debug("'hello' set to " + word);
		
		return true; 
	}

}
