package mfactions.cmds;

import org.bukkit.Bukkit;
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

public class Create {

	FactionManager manager = FactionManager.getInstance();

	@Command(name = "faction.create", aliases = { "f.create",
			"fac.create" }, description = "Creates a faction.", permission = "mfactions.faction.create", inGameOnly = true)
	public void factionSubCreate(CommandArgs args) {

		Player p = args.getPlayer();

		if (args.length() != 1) {
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou must provide a single name for your faction!"));
			
			return;
		}

		if (manager.createFaction(args.getArgs(0), p.getName())) {

			p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &aYou have successfully created the '&b&l" + args.getArgs(0) + "&a' faction!"));

			Bukkit.broadcastMessage("teams: " + manager.getFactionNames());
			
		} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cError making faction: are you already in a faction? Faction may already exist."));

	}

}
