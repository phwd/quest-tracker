package X;

import java.util.NoSuchElementException;

public class N0 extends TW<T> {
    public boolean A00;
    public final /* synthetic */ Object A01;

    public N0(Object obj) {
        this.A01 = obj;
    }

    public final boolean hasNext() {
        return !this.A00;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!this.A00) {
            this.A00 = true;
            return (T) this.A01;
        }
        throw new NoSuchElementException();
    }
}
