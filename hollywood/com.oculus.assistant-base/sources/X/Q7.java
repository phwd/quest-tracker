package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Q7 implements Iterator {
    public static final Q7 A00 = new Q7();

    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
