package X;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

@GwtCompatible
/* renamed from: X.0es  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01550es<F, T> extends AnonymousClass0wW<F, T> implements ListIterator<T> {
    @Override // java.util.ListIterator
    public final void add(T t) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasPrevious() {
        return ((ListIterator) this.A00).hasPrevious();
    }

    public final int nextIndex() {
        return ((ListIterator) this.A00).nextIndex();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.0es<F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ListIterator
    public final T previous() {
        return (T) A00(((ListIterator) this.A00).previous());
    }

    public final int previousIndex() {
        return ((ListIterator) this.A00).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(T t) {
        throw new UnsupportedOperationException();
    }

    public AbstractC01550es(ListIterator<? extends F> listIterator) {
        super(listIterator);
    }
}
