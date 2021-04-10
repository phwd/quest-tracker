package X;

import java.util.NoSuchElementException;

/* renamed from: X.0ds  reason: invalid class name and case insensitive filesystem */
public class C03650ds extends AbstractC07380s1<T> {
    public boolean A00;
    public final /* synthetic */ Object A01;

    public C03650ds(Object obj) {
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
