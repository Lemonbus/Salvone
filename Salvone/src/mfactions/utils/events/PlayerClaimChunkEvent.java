package mfactions.utils.events;

import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import mfactions.utils.faction.Faction;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class PlayerClaimChunkEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Set<Chunk> chunks;
	private Faction faction;
	
	public PlayerClaimChunkEvent(Player player, Set<Chunk> chunks, Faction faction) {
		
		this.player = player;
		this.chunks = chunks;
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public Set<Chunk> getChunks() {
		
		return chunks;
		
	}
	
	public Faction getFaction() {
		
		return faction;
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		
		return handlers;
		
	}

}
