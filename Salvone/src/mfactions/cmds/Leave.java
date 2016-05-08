package mfactions.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import mfactions.utils.ChatManager;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.FactionManager;

/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class Leave {
	
	FactionManager manager = FactionManager.getInstance();

	@Command(name = "faction.leave", aliases = { "f.leave",
			"fac.leave" }, description = "Leave your faction.", permission = "mfactions.faction.leave", inGameOnly = true)
	public void factionSubCreate(CommandArgs args) {

		Player p = args.getPlayer();

		if (manager.getFaction(p.getName()) == null) { 
			
			// tell them they aren't in faction
			
			return;
			
		}
		
		if (manager.getFaction(p.getName()).getLeader().equals(p.getName())) {
			
			// tell them that they were leader, faction is disbanded
			
			// message entire faction too
			
		}
			
		FactionManager.getInstance().removePlayer(p.getName());
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &aYou have successfully left the '&b&l" + args.getArgs(0) + "&a' faction."));

	}

}
