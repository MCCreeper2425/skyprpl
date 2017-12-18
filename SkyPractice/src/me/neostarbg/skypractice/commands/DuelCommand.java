package me.neostarbg.skypractice.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.black_ixx.bossshop.api.BossShopAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neostarbg.skypractice.Duel;
import me.neostarbg.skypractice.Match;
import me.neostarbg.skypractice.Messages;
import me.neostarbg.skypractice.Queue;
import me.neostarbg.skypractice.SkyPractice;
import me.neostarbg.skypractice.commands.admin.HelpCommand;
import mkremins.fanciful.FancyMessage;

public class DuelCommand implements CommandExecutor
{
	//Map<Challenger, Challengee>
	private static List<Duel> duels; 
	private static SkyPractice plugin; 
	private String gt; 
	private Player player;
	
	//duel player -> open bossshop, create Duel object to player and add to list 
	//duel setkit <kit> -> check command sender, comparents all duel objects, if found set the kit and send out request
	//duel accept player -> finds player and start the Duel between player and sender
	
	public DuelCommand(SkyPractice pl)
	{
		plugin = pl;
		duels = new ArrayList<Duel>();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		String arglist = "";
		for(String a : args) {arglist += a + " ";}
		plugin.log(sender.getName() + ": " + cmd.getName() + " " + arglist);
		
		//duel player
		//duel accept player
		//duel deny player
		if(!(sender instanceof Player))
		{
			return true;
		}
		Player player = (Player) sender;
		
		if(args.length == 0)
		{
			player.sendMessage(HelpCommand.helpCommand(HelpCommand.DUEL));
			return true;
		}
		
		if(args[0].equalsIgnoreCase("accept"))
		{
			//duel accept
			if(args.length == 1)
			{
				player.sendMessage(HelpCommand.helpCommand(HelpCommand.DUEL));
				return true;
			}
			//duel accept player
			if(duels.size() == 0) {return true;}
			
			for(Duel d : duels)
			{
				if(d.getOpponent().equals(player))
				{
					if(d.getChallenger().getName().equalsIgnoreCase(args[0]))
					{
						//opponent = player
						Player challenger = d.getChallenger();
						String kit = d.getKit();
						
						Match m = new Match(plugin);
						
						m.setArena(Queue.getFreeArena());
					}
				}
			}
			player.sendMessage(Messages.NO_REQUESTS);
			return true; 
		}
		if(args[0].equalsIgnoreCase("deny"))
		{
			//duel deny
			if(args.length == 1)
			{
				player.sendMessage(HelpCommand.helpCommand(HelpCommand.DUEL));
				return true;
			}
			if(duels.size() == 0) {return true;}
			
			 
			
			player.sendMessage(Messages.NO_REQUESTS);
			return true; 
		}
		if(args[0].equalsIgnoreCase("setkit"))
		{
			if(args.length == 1)
			{return true;}
			
			String kit = args[1];
			plugin.log("Kit is " + kit);
			for(Duel d : duels)
			{
				if(d.getChallenger().equals(player))
				{
					//send duel request
					d.setKit(kit);
					plugin.log("Kit set");
					
					new FancyMessage(player.getName()+" sent you a duel request. ").color(ChatColor.GOLD).style(ChatColor.BOLD)
					.then("[Accept] ").color(ChatColor.GREEN).style(ChatColor.BOLD).style(ChatColor.ITALIC).command("/duel accept "+player.getName())
					.then("[Deny] ").color(ChatColor.RED).style(ChatColor.BOLD).style(ChatColor.ITALIC).command("/duel deny "+player.getName())
					.send(d.getOpponent());

					plugin.log("Sent "+d.getOpponent().getName() +" fancy challenge message");
					
					player.sendMessage(Messages.SUCCESS);
					
					return true;
				}
			}
			return true;
		}
		plugin.log("Searching for player");
		//duel player
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(p.getName().equalsIgnoreCase(args[0]))
			{
				plugin.log("Player found: "+ p.getName());
				
				Duel d = new Duel();
				d.setChallenger(player);
				d.setOpponent(p);
				
				duels.add(d);
				
				plugin.log("Opening kit selection thingy");
				//open GUI
				BossShopAPI bsapi = plugin.getBs().getBSApi();
				
				bsapi.openShop(player, bsapi.getShop("duelkitchoose"));
			}
		}
		return true;
	}
}
