package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.ac  reason: case insensitive filesystem */
public abstract class AbstractC0492ac implements Iterator {
    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Fb fb = (Fb) this;
        int i = fb.A00;
        long[] jArr = fb.A01;
        if (i < jArr.length) {
            fb.A00 = i + 1;
            return new C0475aK(jArr[i]);
        }
        throw new NoSuchElementException(String.valueOf(i));
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
