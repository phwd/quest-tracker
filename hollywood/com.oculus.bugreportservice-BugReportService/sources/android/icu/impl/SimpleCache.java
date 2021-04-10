package android.icu.impl;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleCache implements ICUCache {
    private volatile Reference cacheRef = null;
    private int capacity = 16;
    private int type = 0;

    @Override // android.icu.impl.ICUCache
    public Object get(Object obj) {
        Map map;
        Reference reference = this.cacheRef;
        if (reference == null || (map = (Map) reference.get()) == null) {
            return null;
        }
        return map.get(obj);
    }

    @Override // android.icu.impl.ICUCache
    public void put(Object obj, Object obj2) {
        Reference reference;
        Reference reference2 = this.cacheRef;
        Map map = reference2 != null ? (Map) reference2.get() : null;
        if (map == null) {
            map = Collections.synchronizedMap(new HashMap(this.capacity));
            if (this.type == 1) {
                reference = new WeakReference(map);
            } else {
                reference = new SoftReference(map);
            }
            this.cacheRef = reference;
        }
        map.put(obj, obj2);
    }
}
