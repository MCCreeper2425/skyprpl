package me.neostarbg.skypractice;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena 
{
	
	private Location p1l;
	private Location p2l;
	
	private Player p1;
	private Player p2;
	
	private String kit;
	
	private int state;
	
	private static List<Arena> arenas;
	private static List<Player> ingame;
	
	public Location getP1l() {	return p1l;	} 
	public void setP1l(Location p1l) {this.p1l = p1l;}
	
	public Location getP2l() {return p2l;}
	public void setP2l(Location p2l) {this.p2l = p2l;}
	
	public Player getP1() {	return p1;}
	public void setP1(Player p1) {this.p1 = p1;}
	
	public Player getP2() {	return p2;}
	public void setP2(Player p2) {this.p2 = p2;}
	
	public String getKit() {return kit;}
	public void setKit(String kit) {this.kit = kit;}
	
	public static List<Arena> getArenas() {return arenas;}
	public static List<Player> getIngame() {return ingame;}
	
	public int getState() {return state;}
	
	
}
