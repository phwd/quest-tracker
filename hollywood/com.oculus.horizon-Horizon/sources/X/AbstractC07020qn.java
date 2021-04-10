package X;

import java.util.AbstractSet;
import java.util.ArrayList;

/* renamed from: X.0qn  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07020qn<E> extends AbstractSet<E> {
    public final Object[] toArray() {
        ArrayList arrayList = new ArrayList(size());
        AnonymousClass0qL.A01(arrayList, iterator());
        return arrayList.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        ArrayList arrayList = new ArrayList(size());
        AnonymousClass0qL.A01(arrayList, iterator());
        return (T[]) arrayList.toArray(tArr);
    }
}
