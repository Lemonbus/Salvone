package mfactions.cmds;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mfactions.utils.ChatManager;
import mfactions.utils.chunk.ChunkManager;
import mfactions.utils.command.Command;
import mfactions.utils.command.CommandArgs;
import mfactions.utils.faction.Faction;
import mfactions.utils.faction.FactionManager;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class Info {
	
	FactionManager manager = FactionManager.getInstance();
	
	@Command(name="faction.info", aliases={"f.info", "fac.info"}, permission="mfactions.faction.info", description="View information about a faction.")
	public void factionSubInfo(CommandArgs args) {
		
		CommandSender sender = args.getSender();
		
		if (args.length() == 0) {
			
			if (!(sender instanceof Player)) {
				
				ChatManager.getInstance().messageSenderPlayerOnly(sender);
				
				return;
			}
			
			if (manager.isInFaction(args.getPlayer().getName()) == false) {
				
				args.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cYou are not in a faction. Join one!"));
				
				return;
			}
			
			sendInformation(args.getPlayer(), manager.getFaction(args.getPlayer().getName()));
			
			return;
		}
		
		for (Player online : Bukkit.getOnlinePlayers()) {
			
			if (args.getArgs(0).equalsIgnoreCase(online.getName())) {
				
				if (manager.getFaction(online.getName()) != null) {
					
					sendInformation(sender, manager.getFaction(online.getName()));
					
				} else 
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cThat player does not have a faction!"));
				
				return;
			}
			
		}
		
		if (manager.factionExists(args.getArgs(0)) == false) {
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &cThat faction does not exist!"));
			
			return;
		}
		
		sendInformation(sender, manager.getFactionByName(args.getArgs(0)));
		
	}
	
	public void sendInformation(CommandSender sender, Faction faction) {
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m----&3[&9" + faction.getName() + "&3]&b&m----"));
		
		if (faction.containsPlayer(sender.getName())) {
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bHome: " + (faction.getHome() == null ? "&cNot set" : "&aSet")));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bVault: " + (faction.getVault() == null ? "&cNot set" : "&aSet")));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bFriendly Fire: " + (faction.isFriendyFireEnabled() ? "&aYes" : "&cNo")));
			
		}
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bPower: &d" + faction.getPower()));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bChunks owned: &d" + (ChunkManager.getInstance().getChunks(faction) != null ? ChunkManager.getInstance().getChunks(faction).size() : "None")));
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatManager.getInstance().getChatPrefix() + " &bMembers: "));
		
		StringBuilder sb = new StringBuilder();
		
		for (String members : faction.getMembers()) {
			
			sb.append(faction.getRank(members).getColor() + members + ChatColor.GOLD + ", ");
			
		}
		
		String factionList = sb.toString();
		
		Pattern pattern = Pattern.compile(", $");
		Matcher matcher = pattern.matcher(factionList);
		
		factionList = matcher.replaceAll("");
		
		sender.sendMessage(factionList);
		
	}

}
