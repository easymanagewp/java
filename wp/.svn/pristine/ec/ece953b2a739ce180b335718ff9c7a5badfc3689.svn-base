package cn.wp.commons.wp_tools;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * A <code>MapCache</code> is a {@link Cache Cache} implementation that uses a backing {@link Map} instance to store
 * and retrieve cached data.
 *
 * @since 1.0
 */
public class MapCache<K, V> implements Cache<K, V> {

    /**
     * Backing instance.
     */
    private final Map<K, V> map;

    /**
     * The name of this cache.
     */
    private final String name;

    public MapCache(String name, Map<K, V> backingMap) {
        if (name == null) {
            throw new IllegalArgumentException("Cache name cannot be null.");
        }
        if (backingMap == null) {
            throw new IllegalArgumentException("Backing map cannot be null.");
        }
        this.name = name;
        this.map = backingMap;
    }

    public V get(K key) {
        return map.get(key);
    }

    public V put(K key, V value) {
        return map.put(key, value);
    }

    public V remove(K key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }

    public Set<K> keys() {
        Set<K> keys = map.keySet();
        if (!keys.isEmpty()) {
            return Collections.unmodifiableSet(keys);
        }
        return Collections.emptySet();
    }

    public Collection<V> values() {
        Collection<V> values = map.values();
        if (WPTools.execute("collection.CollectionTools.isNotEmpty", Boolean.class, values)) {
            return Collections.unmodifiableCollection(values);
        }
        return Collections.emptySet();
    }

    public String toString() {
        return new StringBuilder("MapCache '")
                .append(name).append("' (")
                .append(map.size())
                .append(" entries)")
                .toString();
    }
}
