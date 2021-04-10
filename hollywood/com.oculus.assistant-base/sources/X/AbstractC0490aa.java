package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.aa  reason: case insensitive filesystem */
public abstract class AbstractC0490aa implements Iterator {
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        T0 t0 = (T0) this;
        int i = t0.A00;
        byte[] bArr = t0.A01;
        if (i < bArr.length) {
            t0.A00 = i + 1;
            return new C0469aE(bArr[i]);
        }
        throw new NoSuchElementException(String.valueOf(i));
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
