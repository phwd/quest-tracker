package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0rK  reason: invalid class name */
public final class AnonymousClass0rK<T> implements Iterator<T> {
    public static final AnonymousClass0rK<?> A00 = new AnonymousClass0rK<>();

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
