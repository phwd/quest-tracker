package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0oj  reason: invalid class name and case insensitive filesystem */
public final class C07100oj<T> implements Iterator<T>, Iterable<T> {
    public int A00 = 0;
    public final T[] A01;

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return this;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        int i = this.A00;
        T[] tArr = this.A01;
        if (i < tArr.length) {
            this.A00 = i + 1;
            return tArr[i];
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public C07100oj(T[] tArr) {
        this.A01 = tArr;
    }
}
