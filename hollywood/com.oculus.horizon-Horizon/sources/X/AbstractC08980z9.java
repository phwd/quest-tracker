package X;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0z9  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08980z9<T> implements Iterator<T> {
    public int A00;
    public C08990zA<K, V> A01 = null;
    public C08990zA<K, V> A02;
    public final /* synthetic */ C09000zB A03;

    public AbstractC08980z9(C09000zB r2) {
        this.A03 = r2;
        this.A02 = r2.header.A01;
        this.A00 = r2.modCount;
    }

    public final C08990zA<K, V> A00() {
        C08990zA<K, V> r2 = this.A02;
        C09000zB r1 = this.A03;
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
        C08990zA<K, V> r2 = this.A01;
        if (r2 != null) {
            C09000zB r1 = this.A03;
            r1.A06(r2, true);
            this.A01 = null;
            this.A00 = r1.modCount;
            return;
        }
        throw new IllegalStateException();
    }
}
