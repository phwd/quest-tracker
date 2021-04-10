package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.0Xz  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02340Xz<E> extends AnonymousClass0tZ<E> {
    public abstract AnonymousClass0tC<E> A00();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public abstract Iterator<E> iterator();

    public final void clear() {
        A00().clear();
    }

    public final boolean contains(Object obj) {
        return A00().contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        return A00().containsAll(collection);
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    public final boolean remove(Object obj) {
        if (A00().A7L(obj, Integer.MAX_VALUE) > 0) {
            return true;
        }
        return false;
    }

    public final int size() {
        return A00().entrySet().size();
    }
}
