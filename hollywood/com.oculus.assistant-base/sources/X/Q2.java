package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Q2 implements Iterator, Iterable {
    public int A00 = 0;
    public final Object[] A01;

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.A00;
        Object[] objArr = this.A01;
        if (i < objArr.length) {
            this.A00 = i + 1;
            return objArr[i];
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public Q2(Object[] objArr) {
        this.A01 = objArr;
    }
}
