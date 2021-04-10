package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.81  reason: invalid class name */
public final class AnonymousClass81<T> implements Iterator<T> {
    public static final AnonymousClass81<?> A00 = new AnonymousClass81<>();

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
