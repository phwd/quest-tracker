package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.aY  reason: case insensitive filesystem */
public abstract class AbstractC0488aY implements Iterator {
    @Override // java.util.Iterator
    public final Object next() {
        C1200v1 v1Var = (C1200v1) this;
        int i = v1Var.A00;
        if (i != v1Var.A02) {
            v1Var.A00 = v1Var.A03 + i;
        } else if (v1Var.A01) {
            v1Var.A01 = false;
        } else {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(i);
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
