package me.neostarbg.skypractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class Queue 
{
	private static List<Match> queue;
	private static SkyPractice plugin;
	public static void init(SkyPractice pl)
	{
		queue = new ArrayList<Match>();
		plugin = pl;
	}
	public static Arena getFreeArena()
	{
		for(Arena a : Arena.getAreans())
		{
			if(a.isFree()) {return a;}
		}
		
		return null; 
	}
	
	public static void queuePlayer(Player p, String gt)
	{
		for(Match m : queue)
		{
			if(m.getKit().equalsIgnoreCase(gt))
			{
				if(!(m.getPlayer1() == null))
				{
					if(!(m.getPlayer2()==null))
					{
						continue;
					}
					
					m.setPlayer2(p);
					return;
				}
			}
		}
		
		Match m = new Match(plugin);
		
		m.setKit(gt);
		m.setPlayer1(p);
		queueMatch(m);
		
	}
	
	public static void queueMatch(Match m)
	{
		if(queue.contains(m)) {return;}
		
		queue.add(m);
		check();
	}
	
	public static void check()
	{
		Arena a = getFreeArena();
		if(a==null){return;}
		
		for (Match m : queue)
		{
			if(m.getPlayer1() != null && m.getPlayer2() != null)
			{
				m.start(m.getKit());
				return;
			}
		}
		
	}
	
	public static void removeFromQueue(Match m)
	{
		queue.remove(m);
	}
	
}
