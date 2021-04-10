package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0mw  reason: invalid class name and case insensitive filesystem */
public final class C06350mw<T> implements Iterator<T> {
    public static final C06350mw<?> A00 = new C06350mw<>();

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
