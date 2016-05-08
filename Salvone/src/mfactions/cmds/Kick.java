package mfactions.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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

public class Kick {
	
	@Command(name="faction.kick", aliases={"fac.kick", "f.kick"}, permission="mfactions.faction.kick", description="Kick a player from your faction.", inGameOnly=true)
	public void factionSubKick(CommandArgs args) {
		
		Player player = args.getPlayer();
		
		if (!FactionManager.getInstance().isInFaction(player.getName())) {
			
			
			return;
		}
		
		Faction faction = FactionManager.getInstance().getFaction(player.getName());
		
		if (!faction.hasManagementRights(player.getName())) {
			
			
			return;
		}
		
		if (args.length() != 1) {
			
			
			return;
		}
		
		Player target = Bukkit.getPlayer(args.getArgs(0));
		
		if (target == null) {
			
			
			return;
		}
		
		if (!FactionManager.getInstance().isInFaction(target.getName())) {
			
			
			return;
		}
		
		if (FactionManager.getInstance().getFaction(target.getName()) != faction) {
			
			
			return;
		}
		
		if (faction.getRank(target.getName()).getPower() > faction.getRank(player.getName()).getPower()) {
			
			
			return;
		}
		
		FactionManager.getInstance().removePlayer(target.getName());
		faction.messageFaction("&c" + target.getName() + " was kicked from your faction!");
		
	}

}
