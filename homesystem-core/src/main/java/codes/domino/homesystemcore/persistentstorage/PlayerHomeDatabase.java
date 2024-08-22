package codes.domino.homesystemcore.persistentstorage;

import org.bukkit.Location;

import java.util.UUID;

public interface PlayerHomeDatabase extends KeyValueDatabase<UUID, Location> {
}
