package mfactions.utils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import mfactions.main.Main;
import mfactions.utils.faction.Faction;
import mfactions.utils.faction.FactionManager;
import mfactions.utils.faction.FactionRank;

	/**
	 * 
	 * Copyright GummyPvP. Created on Apr 12, 2016
	 * All Rights Reserved.
	 * 
	 */

public class FactionsConfiguration {
	
	private static FactionsConfiguration factions = new FactionsConfiguration("factions"), configuration = new FactionsConfiguration("config"), chunks = new FactionsConfiguration("chunks");
	
	public static FactionsConfiguration getFactionsFile() {
		return factions;
	}
	
	public static FactionsConfiguration getConfig() {
		
		return configuration;
		
	}
	
	public static FactionsConfiguration getChunksFile() {
		
		return chunks;
		
	}
		
	private File file;
	private FileConfiguration config;
	
	public FactionsConfiguration(String fileName) {
		
		if (!Main.getPlugin().getDataFolder().exists()) {
			
			Main.getPlugin().getDataFolder().mkdir();
			
		}
		
		file = new File(Main.getPlugin().getDataFolder(), fileName + ".yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
		
	}
	
	// Since our host isn't that great, saving the factions periodically might not be a great idea. For now, we are only going to save the factions when the plugin is disabling. 
	public boolean saveFactions() {
		
		try {
			
			for (Faction faction : FactionManager.getInstance().getFactions()) {
				
				faction.save();
				
			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure to save the factions, something is wrong!");
			return false;
		}
		
	}
	
	// Call this method only after you save the files, or server will be messed up
	
	public void unloadFactionsInMemory() {
		
		FactionManager.getInstance().clearMaps();
		
	}
	
	// We'll call this method when the server loads, so that factions can be put into the hashmap.
	public void loadFactionsIntoMemory() {
		
		if (getFactionsFile().getSection("factions") == null) return;
		
		for (String factions : getFactionsFile().getSection("factions").getKeys(false)) {
			
			Faction faction = new Faction(factions, getFactionsFile().get("factions." + factions + ".leader"));
			
			if (getFactionsFile().contains("factions." + factions + ".home")) {
				
				World w;
				int x, y, z;
				float yaw, pitch;
				
				w = Bukkit.getWorld(getFactionsFile().getString("factions." + factions + ".home.world"));
				x = getFactionsFile().getInt("factions." + factions + ".home.x");
				y = getFactionsFile().getInt("factions." + factions + ".home.y");
				z = getFactionsFile().getInt("factions." + factions + ".home.z");
				yaw = getFactionsFile().getFloat("factions." + factions + ".home.yaw");
				pitch = getFactionsFile().getFloat("factions." + factions + ".home.pitch");
				
				faction.setHome(new Location(w, x, y, z, yaw, pitch));
				
			}
			
			if (getFactionsFile().contains("factions." + factions + ".vault")) {
				
				World w;
				int x, y, z;
				float yaw, pitch;
				
				w = Bukkit.getWorld(getFactionsFile().getString("factions." + factions + ".vault.world"));
				x = getFactionsFile().getInt("factions." + factions + ".vault.x");
				y = getFactionsFile().getInt("factions." + factions + ".vault.y");
				z = getFactionsFile().getInt("factions." + factions + ".vault.z");
				yaw = getFactionsFile().getFloat("factions." + factions + ".vault.yaw");
				pitch = getFactionsFile().getFloat("factions." + factions + ".vault.pitch");
				
				faction.setVault(new Location(w, x, y, z, yaw, pitch));
				
			}
			
			faction.setPower(getFactionsFile().getDouble("factions." + factions + ".power"));
			faction.setFriendlyFireEnabled(getFactionsFile().getBoolean("factions." + factions + ".friendlyFire"));
			
			FactionManager.getInstance().addFaction(factions, faction);
			
			for (String key : getFactionsFile().getSection("factions." + factions + ".members").getKeys(false)) {
				
				faction.setMember(key, FactionRank.valueOf(getFactionsFile().get("factions." + factions + ".members." + key)));
				
				FactionManager.getInstance().setPlayer(key, factions, FactionRank.valueOf(getFactionsFile().get("factions." + factions + ".members." + key)));
				
			}
		}
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}
	
	public List<?> getList(String path) {
		
		return config.getList(path);
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		
		return (T) config.get(path);
		
	}
	
	public Set<String> getKeys() {
		return config.getKeys(false);
	}
	
	public Set<String> getKeys(boolean b) {
		return config.getKeys(b);
	}
	
	public List<String> getStringList(String path) {
		
		return config.getStringList(path);
		
	}
	
	public String getString(String path) {
		
		return config.getString(path);
		
	}
	
	public List<Map<?, ?>> getMapList(String path) {
		
		return config.getMapList(path);
		
	}
	
	public boolean contains(String path) {
		return config.contains(path);
	}
	
	public ConfigurationSection createSection(String path) {
		ConfigurationSection section = config.createSection(path);
		save();
		return section;
	}
	
	public ConfigurationSection getSection(String path) {
		
		ConfigurationSection section = config.getConfigurationSection(path);
		return section;
		
	}
	
	public void save() {
		try {
			config.save(file);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getBoolean(String string) {
		return config.getBoolean(string);
	}
	
	public Float getFloat(String string) {
		
		return ((float) config.getDouble(string));
		
	}
	
	public Double getDouble(String string) {
		
		return config.getDouble(string);
		
	}
	
	public int getInt(String string) {
		return config.getInt(string);
	}
}
