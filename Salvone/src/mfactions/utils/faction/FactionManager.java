package mfactions.utils.faction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import mfactions.utils.FactionsConfiguration;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class FactionManager {
	
	public FactionManager() {  }
	
	static FactionManager instance = new FactionManager();
	
	public static FactionManager getInstance() {
		
		return instance;
		
	}
	
	private HashMap<String, Faction> factions = new HashMap<String, Faction>();
	private HashMap<String, String> players = new HashMap<String, String>();
	
	public void clearMaps() {
		
		factions.clear();
		players.clear();
		
	}
	
	public boolean createFaction(String factionName, String leader) {
		
		if (players.containsKey(leader) || factions.containsKey(factionName)) return false;
		
		Faction faction = new Faction(factionName, leader);
		
		factions.put(factionName, faction);
		players.put(leader, factionName);
		
		faction.save();
		
		return true;
		
	}
	
	public void addFaction(String factionName, Faction faction) {
		
		factions.put(factionName, faction);
		
	}
	
	public boolean addPlayer(String player, String factionName) {
		
		if (factions.containsKey(factionName) && !players.containsKey(player))  {
			
			players.put(player, factionName);
			factions.get(factionName).addMember(player);
			
			factions.get(factionName).save();
			
			return true;
		}
		
		return false;
		
	}
	
	public void setPlayer(String player, String factionName, FactionRank rank) {
		
		players.put(player, factionName);
		factions.get(factionName).setMember(player, rank);

		factions.get(factionName).save();
		
	}
	
	public void removePlayer(String player) {
		
		if (players.containsKey(player)) {
			
			Faction faction = factions.get(players.get(player));
			
			if (faction.getRank(player) == FactionRank.LEADER) {
				
				for (String members : faction.getMembers()) {
					
					players.remove(members);

				}
				
				FactionsConfiguration.getFactionsFile().set("factions." + faction.getName(), "");
				factions.remove(faction.getName());
			}
			
			FactionsConfiguration.getFactionsFile().set("factions." + faction.getName() + ".members." + player, "");
			
			players.remove(player);
			faction.removeMember(player);
			
			faction.save();
			
		}
	}
	
	public boolean isInFaction(String player) {
		
		return players.containsKey(player);
		
	}
	
	public boolean factionExists(String faction) {
		
		return factions.containsKey(faction);
		
	}
	
	public Faction getFaction(String player) {
		
		if (!players.containsKey(player)) return null;
		
		return factions.get(players.get(player));
		
		
	}
	
	public Faction getFactionByName(String faction) {
		
		if (!factions.containsKey(faction)) return null;
		
		return factions.get(faction);
		
	}
	
	public Set<String> getFactionNames() {
		
		return factions.keySet();
		
	}
	
	public Collection<Faction> getFactions() {
		
		return factions.values();
		
	}
}
