package me.neostarbg.skypractice;

import org.black_ixx.bossshop.BossShop;
import org.black_ixx.bossshop.api.BossShopAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class GUIManager 
{
	private BossShop bs;
	
	public GUIManager()
	{
		Plugin plugin = Bukkit.getPluginManager().getPlugin("BossShop");
		if(plugin == null)
		{
			System.out.println("Dependency not found: Bossshop. Disabling");
			Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("SkyPractice"));
			return; 
		}
		
		bs = (BossShop) plugin;
	}
	public BossShopAPI getBSApi()
	{
		return bs.getAPI();
	}
}
