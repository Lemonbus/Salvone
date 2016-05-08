package mfactions.utils.chunk;

import org.bukkit.Chunk;
import org.bukkit.Location;

import mfactions.utils.faction.Faction;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class ClaimedChunk {
	
	Chunk chunk;
	Faction faction;
	
	public ClaimedChunk(Chunk chunk) {
		
		this.chunk = chunk;
		
	}
	
	public ClaimedChunk(Location location) {
		
		this.chunk = location.getChunk();
		
	}
	
	public Chunk getChunk() {
		
		return chunk;
		
	}
	
	public Faction getOwningFaction() {
		
		return faction;
		
	}
	
	public void setOwningFaction(Faction faction) {
		
		this.faction = faction;
		
	}

	@Override
	public String toString() {
		return "ClaimedChunk [chunk=" + chunk + ", faction=" + faction + "]";
	}

}
