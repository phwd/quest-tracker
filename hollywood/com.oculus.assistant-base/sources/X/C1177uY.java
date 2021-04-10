package X;

import java.util.Collection;
import java.util.Set;

/* renamed from: X.uY  reason: case insensitive filesystem */
public final class C1177uY<K, V> extends UJ<K, Collection<V>> {
    public final UK A00;

    public final void clear() {
        this.A00.clear();
    }

    public final boolean containsKey(Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractMap, X.UJ, java.util.Map
    public final Set keySet() {
        return this.A00.keySet();
    }

    public final int size() {
        return this.A00.keySet().size();
    }

    public C1177uY(UK uk) {
        this.A00 = uk;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return this.A00.A2E(obj);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (containsKey(obj)) {
            return this.A00.A4n(obj);
        }
        return null;
    }
}
