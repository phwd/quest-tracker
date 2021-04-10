package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.ad  reason: case insensitive filesystem */
public abstract class AbstractC0493ad implements Iterator {
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Dc dc = (Dc) this;
        int i = dc.A00;
        short[] sArr = dc.A01;
        if (i < sArr.length) {
            dc.A00 = i + 1;
            return new C0479aO(sArr[i]);
        }
        throw new NoSuchElementException(String.valueOf(i));
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
