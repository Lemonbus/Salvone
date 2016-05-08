package mfactions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import mfactions.utils.ChatManager;
import mfactions.utils.events.PlayerClaimChunkEvent;
import mfactions.utils.faction.Faction;
import mfactions.utils.faction.FactionManager;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class PlayerClaimChunk implements Listener {
	
	@EventHandler
	public void onPlayerClaim(PlayerClaimChunkEvent e) {
		
		Player player = e.getPlayer();
		Faction faction = FactionManager.getInstance().getFaction(player.getName());
		
		faction.messageFaction(ChatManager.getInstance().getChatPrefix() + " &a" + player.getName() + " &bclaimed &a" + e.getChunks().size() + (e.getChunks().size() == 1 ? " chunk" : " chunks") + " &bfor your faction!");
		
		faction.messageFaction(e.getChunks().toString());
	}

}
