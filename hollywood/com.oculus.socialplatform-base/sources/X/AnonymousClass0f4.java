package X;

import com.google.j2objc.annotations.Weak;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0f4  reason: invalid class name */
public final class AnonymousClass0f4<K, V> extends AbstractC05380vb<K, Collection<V>> {
    @Weak
    public final AbstractC05440vj<K, V> A00;

    @Override // X.AbstractC05380vb
    public final Set<Map.Entry<K, Collection<V>>> A00() {
        return new AnonymousClass0Mr(this);
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean containsKey(Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map, X.AbstractC05380vb
    public final Set<K> keySet() {
        return this.A00.keySet();
    }

    public final int size() {
        return this.A00.keySet().size();
    }

    public AnonymousClass0f4(AbstractC05440vj<K, V> r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (containsKey(obj)) {
            return this.A00.A3M(obj);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (containsKey(obj)) {
            return this.A00.A96(obj);
        }
        return null;
    }
}
