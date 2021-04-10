package java.util;

import java.util.Map;

public interface SortedMap<K, V> extends Map<K, V> {
    Comparator<? super K> comparator();

    @Override // java.util.Map
    Set<Map.Entry<K, V>> entrySet();

    K firstKey();

    SortedMap<K, V> headMap(K k);

    @Override // java.util.Map
    Set<K> keySet();

    K lastKey();

    SortedMap<K, V> subMap(K k, K k2);

    SortedMap<K, V> tailMap(K k);

    @Override // java.util.Map
    Collection<V> values();
}
