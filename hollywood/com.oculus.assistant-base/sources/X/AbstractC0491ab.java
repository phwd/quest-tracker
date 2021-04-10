package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.ab  reason: case insensitive filesystem */
public abstract class AbstractC0491ab implements Iterator {
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        C0220Kr kr = (C0220Kr) this;
        int i = kr.A00;
        int[] iArr = kr.A01;
        if (i < iArr.length) {
            kr.A00 = i + 1;
            return new C0472aH(iArr[i]);
        }
        throw new NoSuchElementException(String.valueOf(i));
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
