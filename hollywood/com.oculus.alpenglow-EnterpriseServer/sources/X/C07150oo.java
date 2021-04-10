package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0oo  reason: invalid class name and case insensitive filesystem */
public final class C07150oo<T> implements Iterator<T> {
    public static final C07150oo<?> A00 = new C07150oo<>();

    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
