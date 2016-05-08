package mfactions.cmds;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import mfactions.utils.ChatManager;
import mfactions.utils.chunk.ChunkManager;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.events.PlayerClaimChunkEvent;
import mfactions.utils.faction.Faction;
import mfactions.utils.faction.FactionManager;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class Claim {
	
	@Command(name="faction.claim", aliases={"fac.claim", "f.claim"}, permission="mfactions.faction.claim", inGameOnly=true, description="Allows you to claim territory for your faction.")
	public void factionSubClaim(CommandArgs args) {
		
		Player player = args.getPlayer();
		
		if (!FactionManager.getInstance().isInFaction(player.getName())) {
			
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou are not in a faction. Join one!"));
			
			return;
		}
		
		Faction faction = FactionManager.getInstance().getFaction(player.getName());
		
		if (!faction.hasManagementRights(player.getName())) {
			
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou do not have management rights!"));
			
			return;
		}
		
		if (ChunkManager.getInstance().claimChunk(faction, player.getLocation().getChunk())) {
			
			Set<Chunk> chunks = new HashSet<Chunk>();
			
			chunks.add(player.getLocation().getChunk());
			
			Bukkit.getServer().getPluginManager().callEvent(new PlayerClaimChunkEvent(player, chunks, faction));
			
		}
	}
}