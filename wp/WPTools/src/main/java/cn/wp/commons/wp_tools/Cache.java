package cn.wp.commons.wp_tools;

import java.util.Collection;
import java.util.Set;


public interface Cache<K, V> {

    
    public V get(K key);

    public V put(K key, V value);

    public V remove(K key);

    public void clear();

    public int size();

    public Set<K> keys();

    public Collection<V> values();
}
