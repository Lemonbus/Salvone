package mfactions.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import mfactions.utils.ChatManager;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.Faction;
import mfactions.utils.faction.FactionManager;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 13, 2016 by Jeremy Gooch.
	 * All Rights Reserved.
	 * 
	 */

public class Home {
	
	@Command(name="faction.home", aliases={"fac.home", "f.home"}, permission="mfactions.faction.home", description="Allows you to teleport home.", inGameOnly=true)
	public void factionSubHome(CommandArgs args) {
		
		Player player = args.getPlayer();
		
		if (!FactionManager.getInstance().isInFaction(player.getName())) {
			
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou are not in a faction. Join one!"));
			
			return;
		}
		
		Faction faction = FactionManager.getInstance().getFaction(player.getName());
		
		if (faction.getHome() == null) {
			
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYour faction does not have a home set!"));
			
			return;
		}
		
		// send player home
		
		if (args.length() == 1) {
			
			if (player.hasPermission("mfactions.faction.home.others")) {
				
				// tp an admin to their home (home of args) (first check if they are attempting to tp to a player, if not, check if they are tping to a faction, if not, tell them so)
				
				return;
			}
			
			// send a player to their own home since they don't have permission to tp to others
			
		}
		
	}

}
