package X;

import java.util.NoSuchElementException;

/* renamed from: X.Uk  reason: case insensitive filesystem */
public class C0185Uk extends AnonymousClass8f<T> {
    public boolean A00;
    public final /* synthetic */ Object A01;

    public C0185Uk(Object obj) {
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
