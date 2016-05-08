package mfactions.cmds;

import org.bukkit.entity.Player;

import mfactions.utils.chunk.ChunkManager;
import mfactions.utils.chunk.ClaimedChunk;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 13, 2016 by Jeremy Gooch.
	 * All Rights Reserved.
	 * 
	 */

public class Map {
	
	@Command(name="faction.map", aliases={"fac.map", "f.map"}, permission="mfactions.faction.map", description="Allows you to see faction owned chunks.", inGameOnly=true)
	public void factionSubMap(CommandArgs args) {
		
		Player player = args.getPlayer();
		
		for (ClaimedChunk chunk : ChunkManager.getInstance().getNearbyClaimedChunks(player.getLocation(), 20)) {
			
			if (chunk == null) player.sendMessage("null");
			
			player.sendMessage(chunk.toString());
			
		}
		
	}

}
