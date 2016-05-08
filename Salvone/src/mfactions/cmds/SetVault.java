package mfactions.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import mfactions.utils.ChatManager;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.FactionManager;
import mfactions.utils.faction.FactionRank;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class SetVault {
	
	@Command(name="faction.setvault", aliases={"fac.setvault", "f.setvault"}, permission="mfactions.setvault", inGameOnly=true)
	public void factionSubHomeSet(CommandArgs args) {
		
		Player p = args.getPlayer();
		
		if (!FactionManager.getInstance().isInFaction(p.getName())) {
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou are not in a faction. Join one!"));
			
			return;
		}
		
		FactionRank rank = FactionManager.getInstance().getFaction(p.getName()).getRank(p.getName());
		
		if (!FactionManager.getInstance().getFaction(p.getName()).hasManagementRights(p.getName())) {
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou do not have management rights!"));
			
			return;
		}
		
		FactionManager.getInstance().getFaction(p.getName()).setVault(p.getLocation());
		
		FactionManager.getInstance().getFaction(p.getName()).messageFaction(rank.getColor() + p.getName() + " &3has set the faction vault!");
		
	}

}
