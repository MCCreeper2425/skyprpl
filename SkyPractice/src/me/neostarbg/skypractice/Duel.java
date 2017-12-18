package me.neostarbg.skypractice;

import org.bukkit.entity.Player;

public class Duel 
{
	Player challenger;
	Player opponent;
	String kit; 
	Match match;
	public Player getChallenger() {
		return challenger;
	}
	public void setChallenger(Player challenger) {
		this.challenger = challenger;
	}
	public Player getOpponent() {
		return opponent;
	}
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	public String getKit() {
		return kit;
	}
	public void setKit(String kit) {
		this.kit = kit;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
}
