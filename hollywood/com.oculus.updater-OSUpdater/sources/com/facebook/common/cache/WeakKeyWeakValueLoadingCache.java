package com.facebook.common.cache;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class WeakKeyWeakValueLoadingCache<K, V> {
    private final WeakHashMap<K, WeakReference<V>> mCache;
    private final CacheLoader<K, V> mLoader;

    public WeakKeyWeakValueLoadingCache(CacheLoader<K, V> cacheLoader) {
        this(cacheLoader, 16);
    }

    public WeakKeyWeakValueLoadingCache(CacheLoader<K, V> cacheLoader, int i) {
        this.mLoader = cacheLoader;
        this.mCache = new WeakHashMap<>(i);
    }

    public V get(K k) {
        V v;
        WeakReference<V> weakReference = this.mCache.get(k);
        if (weakReference != null && (v = weakReference.get()) != null) {
            return v;
        }
        V load = this.mLoader.load(k);
        set(k, load);
        return load;
    }

    public void set(K k, V v) {
        this.mCache.put(k, new WeakReference<>(v));
    }
}
