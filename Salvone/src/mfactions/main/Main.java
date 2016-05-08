package mfactions.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import mfactions.cmds.Claim;
import mfactions.cmds.Create;
import mfactions.cmds.FactionCommand;
import mfactions.cmds.Home;
import mfactions.cmds.Info;
import mfactions.cmds.Join;
import mfactions.cmds.Kick;
import mfactions.cmds.Leave;
import mfactions.cmds.Map;
import mfactions.cmds.SetHome;
import mfactions.cmds.SetVault;
import mfactions.cmds.Vault;
import mfactions.listeners.PlayerClaimChunk;
import mfactions.utils.FactionsConfiguration;
import mfactions.utils.command.CommandFramework;

public class Main extends JavaPlugin {
	
	/**
	 * 
	 * Copyright Jeremy Gooch (Googlelover1234) 2016
	 * All Rights Reserved.
	 * 
	 * This plugin contains code that isn't written by me. The classes that I did not create are NOT made by me (obviously) and credit
	 * is due to their respective owners. The following files are:
	 * 
	 * BukkitCommand.java; BukkitCompleter.java; Command.java; CommandArgs.java; CommandFramework.java; Completer.java;
	 * 
	 * Thanks to Minnymin3 on the Bukkit forums for the above files, once again, the files stated above are NOT my works and I am not stating
	 * that they are.
	 * 
	 * This plugin is a WIP for our server GummyPvP.
	 * 
	 */

	static Main instance;
	
	CommandFramework framework;
	
	public void onEnable() {
		
		log ("Creating instance...");
		
		instance = this;
		
		log ("Registering events...");
		
		Bukkit.getPluginManager().registerEvents(new PlayerClaimChunk(), this);
		
		log ("Loading factions into memory...");
		
		FactionsConfiguration.getFactionsFile().loadFactionsIntoMemory();
		
		log ("Loading command framework...");
		
		this.framework = new CommandFramework(this);
		
		this.framework.registerCommands(new FactionCommand());
		this.framework.registerCommands(new Create());
		this.framework.registerCommands(new Info());
		this.framework.registerCommands(new Join());
		this.framework.registerCommands(new SetHome());
		this.framework.registerCommands(new SetVault());
		this.framework.registerCommands(new Claim());
		this.framework.registerCommands(new Leave());
		this.framework.registerCommands(new Home());
		this.framework.registerCommands(new Kick());
		this.framework.registerCommands(new Vault());
		this.framework.registerCommands(new Map());
		
		this.framework.registerHelp();
		
		log ("Done!");
		
	}
	
	public void onDisable() {
		
		log ("Saving factions to file...");
		
		if (FactionsConfiguration.getFactionsFile().saveFactions()) {
			
			log ("Sucessfully saved factions to file!");
			
		} else log ("Files were not successfully saved...");
		
		log ("Unloading factions from memory...");
		
		FactionsConfiguration.getFactionsFile().unloadFactionsInMemory();
		
		instance = null;
		
		this.framework = null;
		
	}
	
	public static Plugin getPlugin() {
		
		return Bukkit.getPluginManager().getPlugin("mFactions");
		
	}
	
	public static Main get() {
		
		return instance;
		
	}
	
	private void log(String log) {
		
		System.out.println("[mFactions]: " + log);
		
	}
}
