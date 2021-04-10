package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.2i  reason: invalid class name */
public final class AnonymousClass2i<T> implements Iterator<T> {
    public int A00;
    public int A01;
    public boolean A02 = false;
    public final int A03;
    public final /* synthetic */ AnonymousClass2n A04;

    public AnonymousClass2i(AnonymousClass2n r2, int i) {
        this.A04 = r2;
        this.A03 = i;
        this.A01 = r2.A00();
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.A02) {
            int i = this.A00 - 1;
            this.A00 = i;
            this.A01--;
            this.A02 = false;
            this.A04.A07(i);
            return;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            AnonymousClass2n r1 = this.A04;
            int i = this.A00;
            T t = (T) r1.A03(i, this.A03);
            this.A00 = i + 1;
            this.A02 = true;
            return t;
        }
        throw new NoSuchElementException();
    }
}
