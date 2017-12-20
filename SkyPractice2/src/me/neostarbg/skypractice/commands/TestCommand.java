package me.neostarbg.skypractice.commands;

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
		plugin.debug("Hello world");
		
		Config config = plugin.getC();
		
		plugin.debug(config.getConfig().getString("hello"));
		
		return true; 
	}

}
