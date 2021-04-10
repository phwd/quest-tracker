package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;

@GwtCompatible
public abstract class NA<E> extends QZ implements Collection<E> {
    public final Collection<E> A00() {
        return ((C00050s) ((AnonymousClass6q) ((AbstractC00189h) this))).A00;
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean add(E e) {
        return A00().add(e);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        return A00().addAll(collection);
    }

    public final void clear() {
        A00().clear();
    }

    public final boolean contains(Object obj) {
        return A00().contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection<?> collection) {
        return A00().containsAll(collection);
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return A00().iterator();
    }

    @CanIgnoreReturnValue
    public final boolean remove(Object obj) {
        return A00().remove(obj);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        return A00().removeAll(collection);
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        return A00().retainAll(collection);
    }

    public final int size() {
        return A00().size();
    }

    @Override // java.util.Collection
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        return (T[]) A00().toArray(tArr);
    }

    public Object[] toArray() {
        return A00().toArray();
    }
}
