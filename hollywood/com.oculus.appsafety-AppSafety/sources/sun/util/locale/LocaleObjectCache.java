package sun.util.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class LocaleObjectCache<K, V> {
    private ConcurrentMap<K, CacheEntry<K, V>> map;
    private ReferenceQueue<V> queue;

    /* access modifiers changed from: protected */
    public abstract V createObject(K k);

    public LocaleObjectCache() {
        this(16, 0.75f, 16);
    }

    public LocaleObjectCache(int initialCapacity, float loadFactor, int concurrencyLevel) {
        this.queue = new ReferenceQueue<>();
        this.map = new ConcurrentHashMap(initialCapacity, loadFactor, concurrencyLevel);
    }

    public V get(K key) {
        V value = null;
        cleanStaleEntries();
        CacheEntry<K, V> entry = this.map.get(key);
        if (entry != null) {
            value = (V) entry.get();
        }
        if (value != null) {
            return value;
        }
        V newVal = createObject(key);
        K key2 = normalizeKey(key);
        if (key2 == null || newVal == null) {
            return null;
        }
        CacheEntry<K, V> newEntry = new CacheEntry<>(key2, newVal, this.queue);
        CacheEntry<K, V> entry2 = this.map.putIfAbsent(key2, newEntry);
        if (entry2 == null) {
            return newVal;
        }
        V value2 = (V) entry2.get();
        if (value2 != null) {
            return value2;
        }
        this.map.put(key2, newEntry);
        return newVal;
    }

    /* access modifiers changed from: protected */
    public V put(K key, V value) {
        CacheEntry<K, V> oldEntry = this.map.put(key, new CacheEntry<>(key, value, this.queue));
        if (oldEntry == null) {
            return null;
        }
        return (V) oldEntry.get();
    }

    private void cleanStaleEntries() {
        while (true) {
            CacheEntry<K, V> entry = (CacheEntry) this.queue.poll();
            if (entry != null) {
                this.map.remove(entry.getKey());
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public K normalizeKey(K key) {
        return key;
    }

    /* access modifiers changed from: private */
    public static class CacheEntry<K, V> extends SoftReference<V> {
        private K key;

        CacheEntry(K key2, V value, ReferenceQueue<V> queue) {
            super(value, queue);
            this.key = key2;
        }

        /* access modifiers changed from: package-private */
        public K getKey() {
            return this.key;
        }
    }
}
