package mfactions.utils.faction;

import org.bukkit.ChatColor;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public enum FactionRank {
	
	LEADER(2, ChatColor.DARK_RED), OFFICER(1, ChatColor.RED), MEMBER(0, ChatColor.GREEN);
	
	int power;
	ChatColor color;

	FactionRank(int power, ChatColor color) {
		
		this.power = power;
		this.color = color;
		
	}
	
	public int getPower() {
		return power;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public boolean rankMorePowerfulThan(FactionRank rank, FactionRank check) {
		
		return rank.getPower() > check.getPower();
		
	}

}
