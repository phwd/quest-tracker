package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dm  reason: invalid class name and case insensitive filesystem */
public final class C03610dm extends AbstractC07020qn<Map.Entry<K, V>> {
    public final /* synthetic */ MapMakerInternalMap A00;

    public C03610dm(MapMakerInternalMap mapMakerInternalMap) {
        this.A00 = mapMakerInternalMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        Map.Entry entry;
        Object key;
        MapMakerInternalMap mapMakerInternalMap;
        Object obj2;
        if (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || (obj2 = (mapMakerInternalMap = this.A00).get(key)) == null || !mapMakerInternalMap.A06.A9k().defaultEquivalence().equivalent(entry.getValue(), obj2)) {
            return false;
        }
        return true;
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new C03620dn(this.A00);
    }

    public final boolean remove(Object obj) {
        Map.Entry entry;
        Object key;
        if (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || !this.A00.remove(key, entry.getValue())) {
            return false;
        }
        return true;
    }

    public final int size() {
        return this.A00.size();
    }
}
