package X;

import java.util.NoSuchElementException;

/* renamed from: X.0fG  reason: invalid class name */
public class AnonymousClass0fG extends AbstractC05710wh<T> {
    public boolean A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass0fG(Object obj) {
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
