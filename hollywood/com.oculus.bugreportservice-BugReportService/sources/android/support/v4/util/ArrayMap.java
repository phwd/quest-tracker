package android.support.v4.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ArrayMap extends SimpleArrayMap implements Map {
    MapCollections mCollections;

    private MapCollections getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections() {
                /* class android.support.v4.util.ArrayMap.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public Object colGetEntry(int i, int i2) {
                    return ArrayMap.this.mArray[(i << 1) + i2];
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public int colIndexOfKey(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public int colIndexOfValue(Object obj) {
                    return ArrayMap.this.indexOfValue(obj);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public Map colGetMap() {
                    return ArrayMap.this;
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public void colPut(Object obj, Object obj2) {
                    ArrayMap.this.put(obj, obj2);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public void colRemoveAt(int i) {
                    ArrayMap.this.removeAt(i);
                }

                /* access modifiers changed from: protected */
                @Override // android.support.v4.util.MapCollections
                public void colClear() {
                    ArrayMap.this.clear();
                }
            };
        }
        return this.mCollections;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        ensureCapacity(this.mSize + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Set entrySet() {
        return getCollection().getEntrySet();
    }

    @Override // java.util.Map
    public Set keySet() {
        return getCollection().getKeySet();
    }

    @Override // java.util.Map
    public Collection values() {
        return getCollection().getValues();
    }
}
