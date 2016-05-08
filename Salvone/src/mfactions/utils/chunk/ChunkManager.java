package mfactions.utils.chunk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

import mfactions.utils.FactionsConfiguration;
import mfactions.utils.faction.Faction;

/**
 * 
 * Copyright GummyPvP. Created on Apr 12, 2016 All Rights Reserved.
 * 
 */

public class ChunkManager {

	static ChunkManager instance = new ChunkManager();

	private ChunkManager() {
	}

	public static ChunkManager getInstance() {

		return instance;

	}

	public Map<Faction, Set<ClaimedChunk>> chunks = new HashMap<Faction, Set<ClaimedChunk>>();

	/**
	 * 
	 * Returns the chunks that a faction owns.
	 * 
	 * @param faction
	 *            Faction you're checking.
	 * @return Set<ClaimedChunk>
	 */

	public Set<ClaimedChunk> getChunks(Faction faction) {

		return chunks.get(faction) == null ? null : chunks.get(faction);

	}

	/**
	 * 
	 * Attempts to claim a chunk for a faction.
	 * 
	 * @param faction
	 *            Faction you're trying to add a ClaimedChunk to
	 * @param chunk
	 *            The chunk you're talking about
	 * @return true if successful, false otherwise
	 */

	public boolean claimChunk(Faction faction, Chunk chunk) {

		if (chunkIsClaimed(chunk))
			return false;

		if (faction.getPower() < 1D)
			return false;

		ClaimedChunk claimed = new ClaimedChunk(chunk);

		claimed.setOwningFaction(faction);

		if (getChunks(faction) == null) {

			Set<ClaimedChunk> tempSet = new HashSet<ClaimedChunk>();

			tempSet.add(claimed);

			chunks.put(faction, tempSet);

			tempSet.clear();

			FactionsConfiguration.getChunksFile().set("chunks." + faction.getName() + ".chunkData", chunks);
			FactionsConfiguration.getChunksFile().save();

			return true;
		}

		Set<ClaimedChunk> set = getChunks(faction);

		set.add(claimed);

		chunks.put(faction, set);

		FactionsConfiguration.getChunksFile().set("chunks." + faction.getName() + ".chunkData", chunks);
		FactionsConfiguration.getChunksFile().save();

		return true;

	}

	/**
	 * 
	 * Checks if a chunk is claimed.
	 * 
	 * @param chunk
	 *            The chunk you are checking
	 * @return true if chunk is claimed, false otherwise.
	 */

	public boolean chunkIsClaimed(Chunk chunk) {

		for (Set<ClaimedChunk> chunkss : chunks.values()) {

			for (ClaimedChunk claimed : chunkss) {

				if (claimed.getChunk().equals(chunk))
					return true;

			}

		}

		return false;

	}

	/**
	 * 
	 * Getting the ClaimedChunk object from a Chunk.
	 * 
	 * @param chunk
	 *            The chunk you are doing stuff with
	 * @return ClaimedChunk if chunk is claimed, null otherwise.
	 */

	public ClaimedChunk getClaimedChunk(Chunk chunk) {

		for (Set<ClaimedChunk> chunkss : chunks.values()) {

			for (ClaimedChunk claimed : chunkss) {

				if (claimed.getChunk().equals(chunk))
					return claimed;

			}

		}
		return null;
	}

	public Set<Chunk> getNearbyChunks(Location location, int distance) {

		Set<Chunk> chunks = new HashSet<Chunk>();

		for (Chunk chunk : location.getWorld().getLoadedChunks()) {

			if (location.getX() - chunk.getX() <= 16 * distance && location.getZ() - chunk.getZ() <= 16 * distance) {

				chunks.add(chunk);

			}
		}

		Bukkit.broadcastMessage("chunks: " + chunks.toString());

		return chunks;

	}

	public Set<ClaimedChunk> getNearbyClaimedChunks(Location location, int distance) {

		Set<ClaimedChunk> chunks = new HashSet<ClaimedChunk>();

		for (Chunk chunk : getNearbyChunks(location, distance)) {

			chunks.add(getClaimedChunk(chunk));

		}

		Bukkit.broadcastMessage("claimed: " + chunks.toString());

		return chunks;
	}
}
