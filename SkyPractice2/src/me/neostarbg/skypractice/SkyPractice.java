package me.neostarbg.skypractice;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.neostarbg.skypractice.commands.AdminCommand;
import me.neostarbg.skypractice.commands.TestCommand;

public class SkyPractice extends JavaPlugin
{
	private boolean debug = true;
	private Config config;
	
	public void onEnable()
	{
		initCommands();
		config = new Config(this);
	}
	
	public void onDisable()
	{
		
	}
	public void initCommands()
	{
		getCommand("test").setExecutor(new TestCommand(this));
		getCommand("practice").setExecutor(new AdminCommand(this));
	}
	
	public boolean isInDebug() {return debug;}
	public Config getC() {return config;}
	
	public void debug(String a)
	{
		if(!debug) return;
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(p.isOp())
			{
				p.sendMessage("�6�lDebug: �a" + a);
			}
		}
	}
}
