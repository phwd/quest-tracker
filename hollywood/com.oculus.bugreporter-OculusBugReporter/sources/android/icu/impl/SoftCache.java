package android.icu.impl;

import java.util.concurrent.ConcurrentHashMap;

public abstract class SoftCache<K, V, D> extends CacheBase<K, V, D> {
    private ConcurrentHashMap<K, Object> map = new ConcurrentHashMap<>();

    @Override // android.icu.impl.CacheBase
    public final V getInstance(K key, D data) {
        V v = (V) this.map.get(key);
        if (v == null) {
            V value = createInstance(key, data);
            V cv = (V) this.map.putIfAbsent(key, (value == null || !CacheValue.futureInstancesWillBeStrong()) ? CacheValue.getInstance(value) : value);
            if (cv == null) {
                return value;
            }
            if (!(cv instanceof CacheValue)) {
                return cv;
            }
            return cv.resetIfCleared(value);
        } else if (!(v instanceof CacheValue)) {
            return v;
        } else {
            V cv2 = v;
            if (cv2.isNull()) {
                return null;
            }
            V value2 = cv2.get();
            if (value2 != null) {
                return value2;
            }
            return cv2.resetIfCleared(createInstance(key, data));
        }
    }
}
