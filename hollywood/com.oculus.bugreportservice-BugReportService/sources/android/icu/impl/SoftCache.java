package android.icu.impl;

import java.util.concurrent.ConcurrentHashMap;

public abstract class SoftCache extends CacheBase {
    private ConcurrentHashMap map = new ConcurrentHashMap();

    @Override // android.icu.impl.CacheBase
    public final Object getInstance(Object obj, Object obj2) {
        Object obj3;
        Object obj4 = this.map.get(obj);
        if (obj4 == null) {
            Object createInstance = createInstance(obj, obj2);
            if (createInstance == null || !CacheValue.futureInstancesWillBeStrong()) {
                obj3 = CacheValue.getInstance(createInstance);
            } else {
                obj3 = createInstance;
            }
            Object putIfAbsent = this.map.putIfAbsent(obj, obj3);
            if (putIfAbsent == null) {
                return createInstance;
            }
            if (!(putIfAbsent instanceof CacheValue)) {
                return putIfAbsent;
            }
            return ((CacheValue) putIfAbsent).resetIfCleared(createInstance);
        } else if (!(obj4 instanceof CacheValue)) {
            return obj4;
        } else {
            CacheValue cacheValue = (CacheValue) obj4;
            if (cacheValue.isNull()) {
                return null;
            }
            Object obj5 = cacheValue.get();
            if (obj5 != null) {
                return obj5;
            }
            return cacheValue.resetIfCleared(createInstance(obj, obj2));
        }
    }
}
