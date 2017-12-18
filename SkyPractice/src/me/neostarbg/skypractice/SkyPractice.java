package me.neostarbg.skypractice;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.neostarbg.skypractice.commands.AdminCommand;
import me.neostarbg.skypractice.commands.DuelCommand;
import me.neostarbg.skypractice.commands.ShowInventoryCommand;
import me.neostarbg.skypractice.commands.debug.TestCommand;
import me.neostarbg.skypractice.listeners.PlayerCommandListener;
import me.neostarbg.skypractice.listeners.PlayerDamageEvent;
import me.neostarbg.skypractice.listeners.PlayerDeathListener;

public class SkyPractice extends JavaPlugin 
{
	private FileConfiguration config;
	private YamlConfiguration playerKits, gameTypes;
	private File playerKitsFile, gameTypesFile;
	
	private static GUIManager bs;
	private static SkyPractice plugin;
	
	
	public void onEnable()
	{
		
		plugin = this; 
		bs = new GUIManager();
		
		setupConfig();
		setupFiles();
		registerEvents();
		registerCommands();
		Match.init();
		Queue.init(this);
		InventoryPlayer.init();
		new Gametype(this);
		initArenas();

	}
	public void onDisable()
	{
		
	}
	
	public void setupConfig()
	{
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
	}
	public void setupFiles()
	{
		playerKitsFile = new File(getDataFolder(), "playerkits.yml");
		gameTypesFile = new File(getDataFolder(), "gametypes.yml");
		if(!playerKitsFile.exists())
		{
			playerKitsFile.getParentFile().mkdirs();
			saveResource("playerkits.yml", false);
		}
		if(!gameTypesFile.exists())
		{
			gameTypesFile.getParentFile().mkdirs();
			saveResource("gametypes.yml", false);
		}
		playerKits = new YamlConfiguration();
		gameTypes = new YamlConfiguration();
		try
		{
			playerKits.load(playerKitsFile);
			gameTypes.load(gameTypesFile);
		}
		catch(IOException e){} catch (InvalidConfigurationException e){}
		
	}
	
	public void registerEvents()
	{
		getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerCommandListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDamageEvent(this), this);
	}
	
	public void registerCommands()
	{
		getCommand("test").setExecutor(new TestCommand(this));
		getCommand("pradmin").setExecutor(new AdminCommand(this));
		getCommand("showinventory").setExecutor(new ShowInventoryCommand(this));
		getCommand("duel").setExecutor(new DuelCommand(this));
	}
	
	public void initArenas()
	{
		System.out.println("Initialising arenas");
		Arena.init(this);
		ConfigurationSection csa = config.getConfigurationSection("Arenas");
		
		for(String x :csa.getKeys(false))
		{
			Location p1, p2;
			System.out.println("Arena " + x);
			p1 = Util.getLocation(this, "Arenas."+x+".p1");
			p2 = Util.getLocation(this, "Arenas."+x+".p2");
			System.out.println("XYZ: " + p1.getBlockX() + ", " + p1.getBlockY() + ", " + p1.getBlockZ());
			new Arena(x, p1, p2);
		}
		
	}
	
	public YamlConfiguration getKits() {return playerKits;}
	public YamlConfiguration getGametypes() {return gameTypes;}
	public File getKitsFile() {return playerKitsFile;}
	public File getGametypesFile() {return gameTypesFile; }
	
	private static Player debug;
	public static boolean debug_enabled = false;
	public static void log(String message)
	{
		if(debug == null)
		{
			for(Player p : plugin.getServer().getOnlinePlayers())
			{
				if(p.getName().equalsIgnoreCase("NeoStarBG"))
				{
					debug = p;
				}
			}
			
			if(debug == null)
			{
				return;
			}
		}
		
		if(debug_enabled)	debug.sendMessage("§aDebug: §6"+message);
	}
	
	public static GUIManager getBs() {return bs;}

}
