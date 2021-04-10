package X;

import java.util.Collection;

/* renamed from: X.ub  reason: case insensitive filesystem */
public abstract class AbstractC1180ub<E> extends UV<E> {
    private final UM A00() {
        if (!(this instanceof C0117Bk)) {
            return ((C0145Dk) this).A00;
        }
        return ((C0117Bk) this).A00;
    }

    public final void clear() {
        A00().clear();
    }

    public final boolean contains(Object obj) {
        return A00().contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return A00().containsAll(collection);
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    public final boolean remove(Object obj) {
        if (A00().A4m(obj, Integer.MAX_VALUE) > 0) {
            return true;
        }
        return false;
    }

    public final int size() {
        return A00().entrySet().size();
    }
}
