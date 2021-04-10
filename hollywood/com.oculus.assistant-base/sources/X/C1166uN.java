package X;

import java.util.NoSuchElementException;

/* renamed from: X.uN  reason: case insensitive filesystem */
public final class C1166uN extends AbstractC0370Ug {
    public boolean A00;
    public final /* synthetic */ Object A01;

    public C1166uN(Object obj) {
        this.A01 = obj;
    }

    public final boolean hasNext() {
        return !this.A00;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.A00) {
            this.A00 = true;
            return this.A01;
        }
        throw new NoSuchElementException();
    }
}
