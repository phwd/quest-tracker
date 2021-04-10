package X;

import java.util.Collection;
import java.util.Iterator;

public abstract class u6<E> extends Tu implements Collection<E> {
    public final Collection A00() {
        return ((AnonymousClass24) ((YG) ((AbstractC0140De) this))).A00;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return A00().add(obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        return A00().addAll(collection);
    }

    public final void clear() {
        A00().clear();
    }

    public final boolean contains(Object obj) {
        return A00().contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return A00().containsAll(collection);
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return A00().iterator();
    }

    public final boolean remove(Object obj) {
        return A00().remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return A00().removeAll(collection);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return A00().retainAll(collection);
    }

    public final int size() {
        return A00().size();
    }

    public Object[] toArray() {
        return A00().toArray();
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return A00().toArray(objArr);
    }
}
