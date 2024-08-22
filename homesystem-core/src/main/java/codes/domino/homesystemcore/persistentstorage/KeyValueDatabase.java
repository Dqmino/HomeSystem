package codes.domino.homesystemcore.persistentstorage;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface KeyValueDatabase<K, V> {
    CompletableFuture<V> getValue(K k);
    CompletableFuture<Boolean> setValue(K k, V v);
    CompletableFuture<Set<K>> getKeys();
    CompletableFuture<Set<V>> getValues();

}
