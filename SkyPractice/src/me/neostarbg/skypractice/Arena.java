package me.neostarbg.skypractice;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Arena 
{
	private static SkyPractice plugin;
	private static List<Arena> freeArenas; public static List<Arena> getAreans() {return freeArenas;}
	
	private String name;
	private Location p1, p2; 
	private boolean free;
	
	public static void init(SkyPractice pl) 
	{
		plugin = pl;
		freeArenas = new ArrayList<Arena>();
	}
	
	public Arena(String name, Location p1, Location p2)
	{
		this.name = name; 
		this.p1 = p1;
		this.p2 = p2;
		free = true;
		
		freeArenas.add(this);
	}
	
	public void setFree(boolean free)
	{
		this.free = free;
		
		if(free)
		{
			for(Arena a : freeArenas)
			{
				if(a.equals(this))
				{
					return;
				}
			}
			
			freeArenas.add(this);
			Queue.check();
		}
		else
		{
			try {freeArenas.remove(this);} catch(Exception e) {}
		}
	}
	
	public boolean isFree()
	{
		return free; 
	}
	
	public Location getP1(){return p1;}
	public Location getP2(){return p2;}
	public String getName() {return name;}
	
}
