package X;

import com.google.common.collect.HashBiMap;
import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: X.0uH  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05140uH<K, V, T> extends AbstractSet<T> {
    public final HashBiMap<K, V> A00;

    public abstract T A00(int i);

    public final void clear() {
        this.A00.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<T> iterator() {
        return new C05130uG(this);
    }

    public final int size() {
        return this.A00.A03;
    }

    public AbstractC05140uH(HashBiMap<K, V> hashBiMap) {
        this.A00 = hashBiMap;
    }
}
