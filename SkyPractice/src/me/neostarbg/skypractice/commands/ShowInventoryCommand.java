package me.neostarbg.skypractice.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.neostarbg.skypractice.InventoryPlayer;
import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.SkyPractice;
public class ShowInventoryCommand implements CommandExecutor
{

	SkyPractice plugin;
	
	public ShowInventoryCommand(SkyPractice pl)
	{
		plugin = pl; 
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(!(sender instanceof Player)) {return true;}
		Player player = (Player) sender;
		if(!player.getLocation().getWorld().getName().equals(plugin.getConfig().getString("world"))) {return true;}
		if(args.length == 0) {return true;}
		
		Player target = Bukkit.getPlayer(args[0]);
		
		InventoryPlayer i = InventoryPlayer.getPlayer(target);
		
		if(i.equals(null)) {return true;}
		
		plugin.log("Creating inventory");
		Inventory inv = Bukkit.createInventory(null, 5*9, "§9§l"+args[0]);

		double health = i.getHealth();
		int hunger = i.getHunger();
		ItemStack[] items = i.getInvenotry();
		
		for(int x = 0; x < i.getInvenotry().length; x++)
		{
			inv.setItem(x+9, items[x]);
		}
		
		ItemStack[] armor = i.getArmor();
		
		for(int x = 0; x < armor.length; x++)
		{
			inv.setItem(x+1, armor[x]);
		}
		
		Potion pot = new Potion(PotionType.INSTANT_HEAL, 1);
		pot.setSplash(true);
		
		List<String> healthLore = new ArrayList<String>();
		if(args.length == 1) healthLore.add("§c" + Double.toString(Math.floor(health)/2));
		else if(args[1].equalsIgnoreCase("l")) healthLore.add("§c" + (double) 0);
		
		List<String> hungerLore = new ArrayList<String>();
		hungerLore.add("§c"+ Float.toString(hunger/2));
		
		ItemStack healthItem = pot.toItemStack(1);
		ItemMeta healthMeta =  healthItem.getItemMeta();
		healthMeta.setDisplayName("§4§1Health");
		healthMeta.setLore(healthLore);
		healthItem.setItemMeta(healthMeta);
		
		ItemStack hungerItem = new ItemStack(Material.COOKED_BEEF, 1);
		ItemMeta hungerMeta = hungerItem.getItemMeta();
		hungerMeta.setDisplayName("§6§lHunger");
		hungerMeta.setLore(hungerLore);
		hungerItem.setItemMeta(hungerMeta);
		
		inv.setItem(6, healthItem);
		inv.setItem(7, hungerItem);
		
		player.openInventory(inv);
		
		return true; 
	}	
}
