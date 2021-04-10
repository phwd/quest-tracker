package X;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0Dh  reason: invalid class name */
public abstract class AnonymousClass0Dh<T> implements Iterator<T> {
    public int A00;
    public C01090Di<K, V> A01 = null;
    public C01090Di<K, V> A02;
    public final /* synthetic */ C01100Dk A03;

    public AnonymousClass0Dh(C01100Dk r2) {
        this.A03 = r2;
        this.A02 = r2.header.A01;
        this.A00 = r2.modCount;
    }

    public final C01090Di<K, V> A00() {
        C01090Di<K, V> r2 = this.A02;
        C01100Dk r1 = this.A03;
        if (r2 == r1.header) {
            throw new NoSuchElementException();
        } else if (r1.modCount == this.A00) {
            this.A02 = r2.A01;
            this.A01 = r2;
            return r2;
        } else {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        if (this.A02 != this.A03.header) {
            return true;
        }
        return false;
    }

    public final void remove() {
        C01090Di<K, V> r2 = this.A01;
        if (r2 != null) {
            C01100Dk r1 = this.A03;
            r1.A06(r2, true);
            this.A01 = null;
            this.A00 = r1.modCount;
            return;
        }
        throw new IllegalStateException();
    }
}
