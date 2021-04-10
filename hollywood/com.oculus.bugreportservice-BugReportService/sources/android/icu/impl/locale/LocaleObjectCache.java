package android.icu.impl.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LocaleObjectCache {
    private ConcurrentHashMap _map;
    private ReferenceQueue _queue;

    /* access modifiers changed from: protected */
    public abstract Object createObject(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object normalizeKey(Object obj);

    public LocaleObjectCache() {
        this(16, 0.75f, 16);
    }

    public LocaleObjectCache(int i, float f, int i2) {
        this._queue = new ReferenceQueue();
        this._map = new ConcurrentHashMap(i, f, i2);
    }

    public Object get(Object obj) {
        cleanStaleEntries();
        CacheEntry cacheEntry = (CacheEntry) this._map.get(obj);
        Object obj2 = cacheEntry != null ? cacheEntry.get() : null;
        if (obj2 != null) {
            return obj2;
        }
        Object normalizeKey = normalizeKey(obj);
        Object createObject = createObject(normalizeKey);
        if (normalizeKey == null || createObject == null) {
            return null;
        }
        CacheEntry cacheEntry2 = new CacheEntry(normalizeKey, createObject, this._queue);
        while (obj2 == null) {
            cleanStaleEntries();
            CacheEntry cacheEntry3 = (CacheEntry) this._map.putIfAbsent(normalizeKey, cacheEntry2);
            if (cacheEntry3 == null) {
                return createObject;
            }
            obj2 = cacheEntry3.get();
        }
        return obj2;
    }

    private void cleanStaleEntries() {
        while (true) {
            CacheEntry cacheEntry = (CacheEntry) this._queue.poll();
            if (cacheEntry != null) {
                this._map.remove(cacheEntry.getKey());
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class CacheEntry extends SoftReference {
        private Object _key;

        CacheEntry(Object obj, Object obj2, ReferenceQueue referenceQueue) {
            super(obj2, referenceQueue);
            this._key = obj;
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this._key;
        }
    }
}
