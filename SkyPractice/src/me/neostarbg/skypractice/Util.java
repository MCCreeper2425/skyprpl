package me.neostarbg.skypractice;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Util 
{
	public static void setLocation(SkyPractice plugin, String path, Location loc)
	{
		plugin.getConfig().set(path +".x", loc.getX());
		plugin.getConfig().set(path +".y", loc.getY());
		plugin.getConfig().set(path +".z", loc.getZ());
		plugin.getConfig().set(path +".yaw", loc.getYaw());
		plugin.getConfig().set(path +".pitch", loc.getPitch());
		plugin.saveConfig();
	}
	
	public static Location getLocation(SkyPractice plugin, String path)
	{
		World world = Bukkit.getWorld(plugin.getConfig().getString("world"));
		double x1 = plugin.getConfig().getDouble(path+".x");
		double y1 = plugin.getConfig().getDouble(path+".y");
		double z1 = plugin.getConfig().getDouble(path+".z");
		float yaw = (float) plugin.getConfig().getDouble(path+".yaw");
		float pitch = (float) plugin.getConfig().getDouble(path+".pitch");
		
		return new Location(world, x1, y1, z1, yaw, pitch);
	}
}
