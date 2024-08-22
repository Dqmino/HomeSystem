package codes.domino.homesystemcore.cache;

import codes.domino.homesystemcore.persistentstorage.PlayerHomeDatabase;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeManagerCache {
    private final Map<UUID, Location> playerHomes = new HashMap<>();

    @Nullable
    public Location getCachedHomeLocation(UUID playerUUID) {
        return playerHomes.get(playerUUID);
    }

    /**
     * Set the cached home location of a player.
     * @param playerUUID
     * @param homeLocation
     * @return true if there was no previous home for this player
     */
    public boolean cacheHomeLocation(UUID playerUUID, Location homeLocation) {
        return playerHomes.put(playerUUID, homeLocation) == null;
    }

    public boolean loadCache(PlayerHomeDatabase keyValueDatabase) {
        for (UUID player : keyValueDatabase.getKeys().join()) {
            Location homeLocation = keyValueDatabase.getValue(player).join();
            cacheHomeLocation(player, homeLocation);
        }
        return true;
    }

    public boolean saveCache(PlayerHomeDatabase keyValueDatabase) {
        for (Map.Entry<UUID, Location> playerHomeEntry : playerHomes.entrySet()) {
            keyValueDatabase.setValue(playerHomeEntry.getKey(), playerHomeEntry.getValue()).join();
        }
        return true;
    }

}
