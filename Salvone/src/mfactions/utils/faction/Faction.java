package mfactions.utils.faction;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import mfactions.utils.FactionsConfiguration;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class Faction {
	
	String name;
	String leader;
	Location home, vault;
	double power = 10.0D;
	boolean friendlyFire = false;
	HashMap<String, FactionRank> members = new HashMap<String, FactionRank>();
	
	public Faction(String name, String leader) {
		
		this.name = name;
		this.leader = leader;
		this.members.put(leader, FactionRank.LEADER);
		
	}
	
	public String getName() {
		
		 return name;
		
	}
	
	public String getLeader() {
		
		return leader;
		
	}
	
	public Set<String> getMembers() {
		
		return members.keySet();
		
	}
	
	public void addMember(String player) {
		
		this.members.put(player, FactionRank.MEMBER);
		
	}
	
	public void removeMember(String player) {
		
		this.members.remove(player);
		
	}
	
	public void setMember(String player, FactionRank rank) {
		
		this.members.put(player, rank);
		
	}
	
	public boolean containsPlayer(String player) {
		
		return this.members.containsKey(player);
		
	}
	
	public void setRank(String player, FactionRank rank) {
		
			this.members.replace(player, rank);
			
	}
	
	public FactionRank getRank(String player) {
		
			return this.members.get(player);
		
	}
	
	public void setHome(Location location) {
		
		home = location;
		
	}
	
	public Location getHome() {
		
		return home == null ? null : home;
		
	}
	
	public void setVault(Location location) {
		
		vault = location;
		
	}
	
	public Location getVault() {
		
		return vault == null ? null : vault;
		
	}
	
	public double getPower() {
		
		return power;
		
	}
	
	public void setPower(double power) {
		
		this.power = power;
		
	}
	
	public boolean isFriendyFireEnabled() {
		
		return friendlyFire;
		
	}
	
	public void setFriendlyFireEnabled(boolean friendlyFire) {
		
		this.friendlyFire = friendlyFire;
		
	}
	
	public void messageFaction(String message) {
		
		for (String member : members.keySet()) {
			
			Player target = Bukkit.getPlayer(member);
			
			if (target != null) {
				
				target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&bFaction Notifier&6] &3" + message));
				
			}
			
		}
		
	}
	
	public boolean hasManagementRights(String player) {
		
		if (!containsPlayer(player)) return false;
		
		return (getRank(player).getPower() > FactionRank.OFFICER.getPower());
		
	}
	
	public void save() {
		
		try {
			
			if (getHome() != null) {
				
				FactionsConfiguration.getFactionsFile().set("factions." + name + ".home.world", home.getWorld());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".home.x", home.getX());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".home.y", home.getY());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".home.z", home.getZ());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".home.yaw", home.getYaw());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".home.pitch", home.getPitch());
				
			}
			
			if (getVault() != null) {
				
				FactionsConfiguration.getFactionsFile().set("factions." + name + ".vault.world", vault.getWorld());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".vault.x", vault.getX());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".vault.y", vault.getY());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".vault.z", vault.getZ());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".vault.yaw", vault.getYaw());
				FactionsConfiguration.getFactionsFile().set("factions." + name +  ".vault.pitch", vault.getPitch());
				
			}
			
			FactionsConfiguration.getFactionsFile().set("factions." + name + ".leader", leader);
			
			for (String key : members.keySet()) {
				
				FactionsConfiguration.getFactionsFile().set("factions." + name + ".members." + key, members.get(key).toString());
				
			}
			
			FactionsConfiguration.getFactionsFile().set("factions." + name + ".power", getPower());
			FactionsConfiguration.getFactionsFile().set("factions." + name + ".friendlyFire", isFriendyFireEnabled());
			
			FactionsConfiguration.getFactionsFile().save();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
