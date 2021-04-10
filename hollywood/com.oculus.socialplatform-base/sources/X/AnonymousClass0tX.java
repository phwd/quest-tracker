package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0tX  reason: invalid class name */
public abstract class AnonymousClass0tX<T> implements Iterator<T> {
    public int A00;
    public int A01 = -1;
    public int A02;
    public final /* synthetic */ AbstractMapBasedMultiset A03;

    public abstract T A00(int i);

    public AnonymousClass0tX(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        this.A03 = abstractMapBasedMultiset;
        AnonymousClass0vu<E> r1 = abstractMapBasedMultiset.A01;
        this.A00 = r1.A03();
        this.A02 = r1.A00;
    }

    public final boolean hasNext() {
        if (this.A03.A01.A00 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (this.A00 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public final void remove() {
        AbstractMapBasedMultiset abstractMapBasedMultiset = this.A03;
        if (abstractMapBasedMultiset.A01.A00 == this.A02) {
            boolean z = false;
            if (this.A01 != -1) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            long j = abstractMapBasedMultiset.A00;
            AnonymousClass0vu<E> r8 = abstractMapBasedMultiset.A01;
            int i = this.A01;
            abstractMapBasedMultiset.A00 = j - ((long) AnonymousClass0vu.A01(r8, r8.A06[i], (int) (r8.A05[i] >>> 32)));
            AnonymousClass0vu<E> r2 = abstractMapBasedMultiset.A01;
            this.A00 = r2.A05(this.A00, this.A01);
            this.A01 = -1;
            this.A02 = r2.A00;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            T A002 = A00(this.A00);
            int i = this.A00;
            this.A01 = i;
            this.A00 = this.A03.A01.A04(i);
            return A002;
        }
        throw new NoSuchElementException();
    }
}
