package codes.domino.homesystemcore.manager;

import codes.domino.homesystemapi.manager.HomeManager;
import codes.domino.homesystemcore.cache.HomeManagerCache;
import codes.domino.homesystemcore.persistentstorage.PlayerHomeDatabase;
import codes.domino.homesystemcore.persistentstorage.impl.RedisDatabase;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.UUID;

public class SimpleHomeManager implements HomeManager {

    private final HomeManagerCache homeManagerCache;
    private final PlayerHomeDatabase database;

    private static final class InstanceHolder {
        private static final SimpleHomeManager instance = new SimpleHomeManager(new HomeManagerCache(),
                new RedisDatabase());
    }

    public static SimpleHomeManager getInstance() {
        return InstanceHolder.instance;
    }

    private SimpleHomeManager(HomeManagerCache cache, PlayerHomeDatabase database) {
        this.homeManagerCache = cache;
        this.database = database;
    }

    @Nullable
    @Override
    public Location getHomeLocation(UUID playerUUID) {
        return homeManagerCache.getCachedHomeLocation(playerUUID);
    }

    @Override
    public boolean setHomeLocation(UUID playerUUID, Location newHomeLocation) {
        return homeManagerCache.cacheHomeLocation(playerUUID, newHomeLocation);
    }
    public boolean saveToDatabase() {
        return homeManagerCache.saveCache(this.database);
    }
    public boolean loadFromDatabase() {
        return homeManagerCache.loadCache(this.database);
    }
}
