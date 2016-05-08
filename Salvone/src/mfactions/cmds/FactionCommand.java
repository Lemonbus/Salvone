package mfactions.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.FactionManager;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class FactionCommand {
	
	FactionManager manager = FactionManager.getInstance();

	@Command(name = "faction", aliases = { "f",
			"fac" }, description = "Provides faction functionality.", permission = "mfactions.faction")
	public void factionCommand(CommandArgs args) {

		CommandSender sender = args.getSender();

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "----[Faction]----"));
		
		Bukkit.broadcastMessage("teams: "  + manager.getFactionNames());
		
	}
}
