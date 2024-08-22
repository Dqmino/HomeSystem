package codes.domino.homesystemcore.persistentstorage.impl;

import codes.domino.homesystemcore.persistentstorage.PlayerHomeDatabase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RedisDatabase implements PlayerHomeDatabase {

    private static final JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");

    @Override
    public CompletableFuture<Location> getValue(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedisInstance = jedisPool.getResource()) {
                String locationAsString = jedisInstance.get(uuid.toString());
                if (locationAsString == null) {
                    return null;
                }
                return stringToLocation(locationAsString);
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> setValue(UUID uuid, Location location) {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedisInstance = jedisPool.getResource()) {
                jedisInstance.set(uuid.toString(), locationToString(location));
                return true;
            }
        });
    }

    @Override
    public CompletableFuture<Set<UUID>> getKeys() {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedisInstance = jedisPool.getResource()) {
                return jedisInstance.keys("*").stream().map(UUID::fromString).collect(Collectors.toSet());
            }
        });
    }

    @Override
    public CompletableFuture<Set<Location>> getValues() {
        return CompletableFuture.supplyAsync(() -> {
            try (Jedis jedisInstance = jedisPool.getResource()) {
                try {
                    return jedisInstance.mget((String[]) getKeys().get().stream().map(UUID::toString).toArray())
                            .stream().map(RedisDatabase::stringToLocation).collect(Collectors.toSet());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static String locationToString(final Location loc) {
        return loc.getWorld().getName() + "/" + loc.getX() + "/" + loc.getY() + "/" + (loc.getZ());
    }

    private static Location stringToLocation(final String string) {
        final String[] split = string.split("/");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
    }
}
