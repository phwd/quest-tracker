package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.0qq  reason: invalid class name and case insensitive filesystem */
public final class C07030qq extends AbstractCollection<V> {
    public final /* synthetic */ MapMakerInternalMap A00;

    public C07030qq(MapMakerInternalMap mapMakerInternalMap) {
        this.A00 = mapMakerInternalMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsValue(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<V> iterator() {
        return new C03560db(this.A00);
    }

    public final int size() {
        return this.A00.size();
    }

    public final Object[] toArray() {
        ArrayList arrayList = new ArrayList(size());
        AnonymousClass0qL.A01(arrayList, iterator());
        return arrayList.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        ArrayList arrayList = new ArrayList(size());
        AnonymousClass0qL.A01(arrayList, iterator());
        return (T[]) arrayList.toArray(tArr);
    }
}
