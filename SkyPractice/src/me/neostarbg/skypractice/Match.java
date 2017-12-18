package me.neostarbg.skypractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import mkremins.fanciful.FancyMessage;

public class Match 
{
	private SkyPractice plugin;
	
	
	private Arena arena;    
	public void setArena(Arena a) { arena = a; }    
	public Arena getArena() { return arena; }

	private Player p1, p2; 
	public void setPlayer1(Player p) {p1=p;} public void setPlayer2(Player p){p2=p;}
	public Player getPlayer1(){return p1;} public Player getPlayer2() {return p2;} 
	
	private List<Player> activePlayers;
	public List<Player> getPlayers(){return activePlayers;}
	
	private String kit;
	public void setKit(String kit) {this.kit = kit;}
	public String getKit(){return kit;}
	
	private boolean activequeue = true;
	private static List<Player> globalIngame;
	private static List<Match> matches;
	
	public static void init()
	{
		globalIngame = new ArrayList<Player>();
		matches = new ArrayList<Match>();
	}
	public Match(SkyPractice pl)
	{
		plugin = pl;
		matches.add(this);
		activePlayers = new ArrayList<Player>();
	}
	
	public boolean isQueueActive(){return activequeue;}
	public static List<Player> getGlobalIngame(){return globalIngame;} public static boolean isIngame(Player p) {if(globalIngame.contains(p)) return true; else return false;}
	public static List<Match> getActiveMatches() {return matches;}
	
	public void start(String gt)
	{
		Queue.removeFromQueue(this);
		
		Location p1l = arena.getP1();
		p1.teleport(p1l);
		
		Location p2l = arena.getP2();
		p2.teleport(p2l);
		
		p1.setGameMode(GameMode.SURVIVAL);
		p2.setGameMode(GameMode.SURVIVAL);
		
		Gametype.giveKit(gt, p1, true); //TODO check if player has pkit
		Gametype.giveKit(gt, p2, true);
	}
	
	public void finish(Player victim)
	{
		Player killer = null;
		if(p1.equals(victim)) killer = p2;
		else killer = p1; 
		
		plugin.log("Killer found: " + killer.getName());
		
		plugin.log("Setting arena as free and removing from the list");
		arena.setFree(true);
		matches.remove(this);
		plugin.log("Done");
		
		globalIngame.remove(victim);
		globalIngame.remove(killer);
		
		plugin.log("Players removed from the lists");
		
		killer.getInventory().clear();
		victim.getInventory().clear();
		
		plugin.log("Clearing inventories");

		killer.sendMessage(Messages.WINNER.replaceAll("%1", killer.getName()).replaceAll("%2", victim.getName()));
		victim.sendMessage(Messages.WINNER.replaceAll("%1", killer.getName()).replaceAll("%2", victim.getName()));
		
		//showinventory <player>
		new FancyMessage("Inventories: ").color(ChatColor.GOLD).style(ChatColor.BOLD)
		.then("["+killer.getName()+"] ").color(ChatColor.BLUE).style(ChatColor.BOLD).command("/showinventory "+killer.getName())
		.then("["+victim.getName()+"] ").color(ChatColor.BLUE).style(ChatColor.BOLD).command("/showinventory "+victim.getName()+" l").send(killer);
		
		new FancyMessage("Inventories: ").color(ChatColor.GOLD).style(ChatColor.BOLD)
		.then("["+killer.getName()+"] ").color(ChatColor.BLUE).style(ChatColor.BOLD).command("/showinventory "+killer.getName())
		.then("["+victim.getName()+"] ").color(ChatColor.BLUE).style(ChatColor.BOLD).command("/showinventory "+victim.getName()+" l").send(victim);
		
		plugin.log("Dispatched viewinventory commands");
		
		Location spawn = Util.getLocation(plugin, "Lobby");
		killer.teleport(spawn);
		
		killer.setHealth((double) 20);
		killer.setFoodLevel(20);
		
		
	}

}
