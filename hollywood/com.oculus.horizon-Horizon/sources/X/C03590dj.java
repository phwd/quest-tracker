package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.Iterator;

/* renamed from: X.0dj  reason: invalid class name and case insensitive filesystem */
public final class C03590dj extends AbstractC07020qn<K> {
    public final /* synthetic */ MapMakerInternalMap A00;

    public C03590dj(MapMakerInternalMap mapMakerInternalMap) {
        this.A00 = mapMakerInternalMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new C03600dl(this.A00);
    }

    public final boolean remove(Object obj) {
        if (this.A00.remove(obj) != null) {
            return true;
        }
        return false;
    }

    public final int size() {
        return this.A00.size();
    }
}
