package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* renamed from: X.3L  reason: invalid class name */
public final class AnonymousClass3L<E> implements Iterator<E> {
    public int A00;
    public int A01;
    @MonotonicNonNullDecl
    public Multiset.Entry<E> A02;
    public boolean A03;
    public final AnonymousClass34<E> A04;
    public final Iterator<Multiset.Entry<E>> A05;

    public final boolean hasNext() {
        if (this.A00 > 0 || this.A05.hasNext()) {
            return true;
        }
        return false;
    }

    public final void remove() {
        Preconditions.checkState(this.A03, "no calls to next() since the last call to remove()");
        if (this.A01 == 1) {
            this.A05.remove();
        } else {
            this.A04.remove(this.A02.A01());
        }
        this.A01--;
        this.A03 = false;
    }

    public AnonymousClass3L(AnonymousClass34<E> r1, Iterator<Multiset.Entry<E>> it) {
        this.A04 = r1;
        this.A05 = it;
    }

    @Override // java.util.Iterator
    public final E next() {
        if (hasNext()) {
            int i = this.A00;
            if (i == 0) {
                AbstractC0181Ug next = this.A05.next();
                this.A02 = next;
                i = next.A00();
                this.A00 = i;
                this.A01 = i;
            }
            this.A00 = i - 1;
            this.A03 = true;
            return (E) this.A02.A01();
        }
        throw new NoSuchElementException();
    }
}
