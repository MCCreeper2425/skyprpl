package me.neostarbg.skypractice;

import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Gametype 
{
	private static SkyPractice plugin;
	private String name;
	private ItemStack[] items;
	
	public Gametype(SkyPractice pl)
	{
		plugin = pl; 
	}
	
	public static void giveKit(String name, Player player, boolean global)
	{
		PlayerInventory inventory = player.getInventory();
		inventory.clear();
		YamlConfiguration gt = plugin.getGametypes();
		ConfigurationSection itemsList = gt.getConfigurationSection("gametypes."+name+".items");
		
		for(String key : itemsList.getKeys(true))
		{
			ItemStack i = gt.getItemStack("gametypes."+name+".items."+key);
			if(i != null)inventory.setItem(Integer.parseInt(key), i);
		}
		
		ConfigurationSection armorList = gt.getConfigurationSection("gametypes."+name+".armor");
		ItemStack[] armorContents = new ItemStack[4];
		int x = 0; 
		if(armorList == null || armorList.getKeys(true).isEmpty()) {return;}
		for(String key : armorList.getKeys(true))
		{
			ItemStack i = gt.getItemStack("gametypes."+name+".armor."+key);
			armorContents[x] = i;
			x++;
		}
	}
	
	public static void saveKit(String name, Player player, boolean global)
	{
		PlayerInventory inventory = player.getInventory();
		ItemStack[] armor = inventory.getArmorContents();
	

		YamlConfiguration gt = plugin.getGametypes();
		
		for(int x = 0; x < inventory.getContents().length; x++)
		{
			ItemStack i = inventory.getItem(x);
			gt.set("gametypes."+name+".items."+Integer.toString(x), i);
		}
		
		for(int x = 0; x < 4; x++)
		{
			ItemStack i = armor[x];
			gt.set("gametypes."+name+".armor."+Integer.toString(x), i);
		}
		try
		{
			gt.save(plugin.getGametypesFile());
		}
		catch(IOException e){}
		
		player.sendMessage(Messages.SUCCESS);
	}
	
	public static boolean doesPKitExist(Player player)
	{
		return false; 
	}
	
	public String getName() {return name;}
}
