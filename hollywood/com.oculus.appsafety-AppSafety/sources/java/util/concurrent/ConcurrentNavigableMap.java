package java.util.concurrent;

import java.util.NavigableMap;
import java.util.NavigableSet;

public interface ConcurrentNavigableMap<K, V> extends ConcurrentMap<K, V>, NavigableMap<K, V> {
    @Override // java.util.NavigableMap
    NavigableSet<K> descendingKeySet();

    @Override // java.util.NavigableMap
    ConcurrentNavigableMap<K, V> descendingMap();

    @Override // java.util.NavigableMap, java.util.SortedMap
    ConcurrentNavigableMap<K, V> headMap(K k);

    @Override // java.util.NavigableMap
    ConcurrentNavigableMap<K, V> headMap(K k, boolean z);

    @Override // java.util.Map, java.util.SortedMap
    NavigableSet<K> keySet();

    @Override // java.util.NavigableMap
    NavigableSet<K> navigableKeySet();

    @Override // java.util.NavigableMap, java.util.SortedMap
    ConcurrentNavigableMap<K, V> subMap(K k, K k2);

    @Override // java.util.NavigableMap
    ConcurrentNavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2);

    @Override // java.util.NavigableMap, java.util.SortedMap
    ConcurrentNavigableMap<K, V> tailMap(K k);

    @Override // java.util.NavigableMap
    ConcurrentNavigableMap<K, V> tailMap(K k, boolean z);
}
