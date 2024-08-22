package codes.domino.homesystemapi.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.UUID;

public interface HomeManager {
    /**
     * Get the home location of a player if they have one.
     * @param playerUUID The UUID of the player
     * @return the player's home location or null if none.
     */
    @Nullable
    Location getHomeLocation(UUID playerUUID);

    /**
     * Set the home location of a player
     * @param playerUUID The UUID of the player
     * @param newHomeLocation the player's new home location
     * @return true for success, false for failure
     */
    boolean setHomeLocation(UUID playerUUID, Location newHomeLocation);
}
