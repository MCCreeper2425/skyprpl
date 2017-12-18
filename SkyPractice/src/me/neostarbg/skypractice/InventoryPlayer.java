package me.neostarbg.skypractice;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryPlayer 
{
	private static List<InventoryPlayer> list;
	
	private Player p; 
	public Player getPlayer() {return p;}
	public void setPlayer(Player p) { this.p = p; }
	
	private double health; 
	public double getHealth() {return health;}
	public void setHealth(double h) {health = h;}
	
	private int hunger; 
	public int getHunger() {return hunger;}
	public void setHunger(int h) {hunger = h;}
	
	private ItemStack[] inventory; 
	public ItemStack[] getInvenotry() {return inventory;}
	public void setInventory(ItemStack[] inventory) {this.inventory = inventory;}
	
	private ItemStack[] armor;
	public ItemStack[] getArmor() {return armor;}
	public void setArmor(ItemStack[] armor) {this.armor = armor;}
	
	
	
	public static void init()
	{
		list = new ArrayList<InventoryPlayer>();
	}
	
	public static void save(Player p)
	{
		double health = p.getHealth();
		int hunger = p.getFoodLevel();
		
		PlayerInventory pi = p.getInventory();
		ItemStack[] inv = pi.getStorageContents();
		ItemStack[] armor = pi.getArmorContents();
		
		InventoryPlayer ip = new InventoryPlayer();
		ip.setHealth(health);
		ip.setHunger(hunger);
		ip.setPlayer(p);
		ip.setInventory(inv);
		ip.setArmor(armor);
		
		if(!list.isEmpty())
		{
			for(InventoryPlayer i : list)
			{
				if(i.getPlayer().equals(p))
				{
					list.remove(i);
				}
			}
		}
		
		list.add(ip);
	}
	
	public static InventoryPlayer getPlayer(Player p)
	{
		for(InventoryPlayer ip : list)
		{
			if(ip.getPlayer().equals(p))
			{
				return ip;
			}
		}
		return null;
	}
	
}
