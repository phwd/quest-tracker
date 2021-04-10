package com.facebook.common.cache;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class WeakKeyWeakValueLoadingCache<K, V> {
    private final WeakHashMap<K, WeakReference<V>> mCache;
    private final CacheLoader<K, V> mLoader;

    public WeakKeyWeakValueLoadingCache(CacheLoader<K, V> loader) {
        this(loader, 16);
    }

    public WeakKeyWeakValueLoadingCache(CacheLoader<K, V> loader, int capacity) {
        this.mLoader = loader;
        this.mCache = new WeakHashMap<>(capacity);
    }

    public V get(K key) {
        V item;
        WeakReference<V> weakReference = this.mCache.get(key);
        if (weakReference != null && (item = weakReference.get()) != null) {
            return item;
        }
        V value = this.mLoader.load(key);
        set(key, value);
        return value;
    }

    public void set(K key, V value) {
        this.mCache.put(key, new WeakReference<>(value));
    }
}
