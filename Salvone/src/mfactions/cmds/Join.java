package mfactions.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.FactionManager;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class Join {
	
	FactionManager manager = FactionManager.getInstance();
	
	@Command(name="faction.join", aliases={"f.join", "fac.join"}, permission="mfactions.faction.join", description="Join a faction, if you've been invited.", inGameOnly=true)
	public void factionSubJoin(CommandArgs args) {
		
		if (args.length() != 1) {
			
			
			return;
		}
		
		for (Player online : Bukkit.getOnlinePlayers()) {
			
			if (args.getArgs(0).equalsIgnoreCase(online.getName())) {
				
				if (manager.isInFaction(online.getName())) {
					
					manager.addPlayer(args.getPlayer().getName(), manager.getFaction(online.getName()).getName());
					
				} // else they aren't in a faction
				
				return;
				
			}
			
		}
		
		if (manager.factionExists(args.getArgs(0))) {
			
			manager.addPlayer(args.getPlayer().getName(), args.getArgs(0));
			
		}
		
	}

}
