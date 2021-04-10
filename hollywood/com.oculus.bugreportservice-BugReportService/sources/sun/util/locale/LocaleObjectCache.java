package sun.util.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class LocaleObjectCache {
    private ConcurrentMap map;
    private ReferenceQueue queue;

    /* access modifiers changed from: protected */
    public abstract Object createObject(Object obj);

    /* access modifiers changed from: protected */
    public Object normalizeKey(Object obj) {
        return obj;
    }

    public LocaleObjectCache() {
        this(16, 0.75f, 16);
    }

    public LocaleObjectCache(int i, float f, int i2) {
        this.queue = new ReferenceQueue();
        this.map = new ConcurrentHashMap(i, f, i2);
    }

    public Object get(Object obj) {
        cleanStaleEntries();
        CacheEntry cacheEntry = (CacheEntry) this.map.get(obj);
        Object obj2 = cacheEntry != null ? cacheEntry.get() : null;
        if (obj2 != null) {
            return obj2;
        }
        Object createObject = createObject(obj);
        Object normalizeKey = normalizeKey(obj);
        if (normalizeKey == null || createObject == null) {
            return null;
        }
        CacheEntry cacheEntry2 = new CacheEntry(normalizeKey, createObject, this.queue);
        CacheEntry cacheEntry3 = (CacheEntry) this.map.putIfAbsent(normalizeKey, cacheEntry2);
        if (cacheEntry3 == null) {
            return createObject;
        }
        Object obj3 = cacheEntry3.get();
        if (obj3 != null) {
            return obj3;
        }
        this.map.put(normalizeKey, cacheEntry2);
        return createObject;
    }

    /* access modifiers changed from: protected */
    public Object put(Object obj, Object obj2) {
        CacheEntry cacheEntry = (CacheEntry) this.map.put(obj, new CacheEntry(obj, obj2, this.queue));
        if (cacheEntry == null) {
            return null;
        }
        return cacheEntry.get();
    }

    private void cleanStaleEntries() {
        while (true) {
            CacheEntry cacheEntry = (CacheEntry) this.queue.poll();
            if (cacheEntry != null) {
                this.map.remove(cacheEntry.getKey());
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class CacheEntry extends SoftReference {
        private Object key;

        CacheEntry(Object obj, Object obj2, ReferenceQueue referenceQueue) {
            super(obj2, referenceQueue);
            this.key = obj;
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this.key;
        }
    }
}
