package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class MC<T> implements Iterator<T> {
    public static final MC<?> A00 = new MC<>();

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
