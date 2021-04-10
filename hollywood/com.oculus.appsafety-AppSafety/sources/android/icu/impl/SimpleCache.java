package android.icu.impl;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleCache<K, V> implements ICUCache<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private volatile Reference<Map<K, V>> cacheRef;
    private int capacity;
    private int type;

    public SimpleCache() {
        this.cacheRef = null;
        this.type = 0;
        this.capacity = 16;
    }

    public SimpleCache(int cacheType) {
        this(cacheType, 16);
    }

    public SimpleCache(int cacheType, int initialCapacity) {
        this.cacheRef = null;
        this.type = 0;
        this.capacity = 16;
        if (cacheType == 1) {
            this.type = cacheType;
        }
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
        }
    }

    @Override // android.icu.impl.ICUCache
    public V get(Object key) {
        Map<K, V> map;
        Reference<Map<K, V>> ref = this.cacheRef;
        if (ref == null || (map = ref.get()) == null) {
            return null;
        }
        return map.get(key);
    }

    @Override // android.icu.impl.ICUCache
    public void put(K key, V value) {
        Reference<Map<K, V>> ref;
        Reference<Map<K, V>> ref2 = this.cacheRef;
        Map<K, V> map = null;
        if (ref2 != null) {
            map = ref2.get();
        }
        if (map == null) {
            map = Collections.synchronizedMap(new HashMap(this.capacity));
            if (this.type == 1) {
                ref = new WeakReference<>(map);
            } else {
                ref = new SoftReference<>(map);
            }
            this.cacheRef = ref;
        }
        map.put(key, value);
    }

    @Override // android.icu.impl.ICUCache
    public void clear() {
        this.cacheRef = null;
    }
}
