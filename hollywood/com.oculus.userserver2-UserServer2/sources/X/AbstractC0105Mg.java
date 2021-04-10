package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.Mg  reason: case insensitive filesystem */
public abstract class AbstractC0105Mg<E> extends Rj<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public abstract Iterator<E> iterator();

    private final AbstractC0120Qz<E> A00() {
        if (!(this instanceof AnonymousClass9J)) {
            return ((AnonymousClass9l) this).A00;
        }
        return ((AnonymousClass9J) this).A00;
    }

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
        if (A00().A3F(obj, Integer.MAX_VALUE) > 0) {
            return true;
        }
        return false;
    }

    public final int size() {
        return A00().entrySet().size();
    }
}
