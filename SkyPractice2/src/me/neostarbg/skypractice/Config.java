package me.neostarbg.skypractice;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config 
{
	private File configfile, arenasfile, kitsfile, playersfile;
	private FileConfiguration config, arenas, kits, players;
	 
	private SkyPractice plugin;
	
	public Config(SkyPractice plugin)
	{
		this.plugin = plugin;
		createFiles();
	}
	
	private void createFiles()
	{
		configfile = new File(plugin.getDataFolder(), "config.yml");
		arenasfile = new File(plugin.getDataFolder(), "arenas.yml");
		kitsfile = new File(plugin.getDataFolder(), "kits.yml");
		playersfile = new File(plugin.getDataFolder(), "players.yml");
		
		if (!configfile.exists()) 
		{
            configfile.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }
		
		if (!arenasfile.exists()) 
		{
            arenasfile.getParentFile().mkdirs();
            plugin.saveResource("arenas.yml", false);
        }
		
		if (!kitsfile.exists()) 
		{
            kitsfile.getParentFile().mkdirs();
            plugin.saveResource("kits.yml", false);
        }
		
		if (!playersfile.exists()) 
		{
            playersfile.getParentFile().mkdirs();
            plugin.saveResource("players.yml", false);
        }
		
		config = new YamlConfiguration();
		arenas = new YamlConfiguration();
		kits = new YamlConfiguration();
		players = new YamlConfiguration();
		
		try 
		{
		    config.load(configfile);
		    arenas.load(arenasfile);
		    kits.load(kitsfile);
		    players.load(playersfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void reload()
	{
		createFiles();
	}
	
	//Getters and setters

	public File getConfigfile() {
		return configfile;
	}

	public void setConfigfile(File configfile) {
		this.configfile = configfile;
	}

	public File getArenasfile() {
		return arenasfile;
	}

	public void setArenasfile(File arenasfile) {
		this.arenasfile = arenasfile;
	}

	public File getKitsfile() {
		return kitsfile;
	}

	public void setKitsfile(File kitsfile) {
		this.kitsfile = kitsfile;
	}

	public File getPlayersfile() {
		return playersfile;
	}

	public void setPlayersfile(File playersfile) {
		this.playersfile = playersfile;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

	public FileConfiguration getArenas() {
		return arenas;
	}

	public void setArenas(FileConfiguration arenas) {
		this.arenas = arenas;
	}

	public FileConfiguration getKits() {
		return kits;
	}

	public void setKits(FileConfiguration kits) {
		this.kits = kits;
	}

	public FileConfiguration getPlayers() {
		return players;
	}

	public void setPlayers(FileConfiguration players) {
		this.players = players;
	}

	public SkyPractice getPlugin() {
		return plugin;
	}

	public void setPlugin(SkyPractice plugin) {
		this.plugin = plugin;
	}
	
	
}
