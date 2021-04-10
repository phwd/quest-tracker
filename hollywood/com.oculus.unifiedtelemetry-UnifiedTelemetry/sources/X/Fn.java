package X;

import java.util.Iterator;

public class Fn<T> implements Iterator<T> {
    public int A00;
    public int A01;
    public final T[] A02;

    public final boolean hasNext() {
        if (this.A01 < this.A00) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        T[] tArr = this.A02;
        int i = this.A01;
        this.A01 = i + 1;
        return tArr[i];
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public Fn(T[] tArr) {
        this.A02 = tArr;
        this.A00 = tArr.length;
    }
}
