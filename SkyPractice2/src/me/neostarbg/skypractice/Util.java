package me.neostarbg.skypractice;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class Util 
{
	public static void setLocation(SkyPractice plugin, String path, Location loc, FileConfiguration config, File configfile)
	{
		config.set(path +".x", loc.getX());
		config.set(path +".y", loc.getY());
		config.set(path +".z", loc.getZ());
		config.set(path +".yaw", loc.getYaw());
		config.set(path +".pitch", loc.getPitch());
		
		try {
			config.save(configfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Location getLocation(SkyPractice plugin, String path, FileConfiguration config)
	{
		World world = Bukkit.getWorld(plugin.getConfig().getString("world"));
		double x1 = config.getDouble(path+".x");
		double y1 = config.getDouble(path+".y");
		double z1 = config.getDouble(path+".z");
		float yaw = (float) config.getDouble(path+".yaw");
		float pitch = (float) config.getDouble(path+".pitch");
		
		return new Location(world, x1, y1, z1, yaw, pitch);
	}
}
