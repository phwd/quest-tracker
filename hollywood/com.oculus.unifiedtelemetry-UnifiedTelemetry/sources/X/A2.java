package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class A2<T> implements Iterator<T> {
    public int A00;
    public int A01 = -1;
    public int A02;
    public final /* synthetic */ AbstractMapBasedMultiset A03;

    public abstract T A00(int i);

    public A2(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        this.A03 = abstractMapBasedMultiset;
        AnonymousClass3s<E> r1 = abstractMapBasedMultiset.A01;
        this.A00 = r1.A04();
        this.A02 = r1.A01;
    }

    public final boolean hasNext() {
        if (this.A03.A01.A01 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (this.A00 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public final void remove() {
        AbstractMapBasedMultiset abstractMapBasedMultiset = this.A03;
        if (abstractMapBasedMultiset.A01.A01 == this.A02) {
            boolean z = false;
            if (this.A01 != -1) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            long j = abstractMapBasedMultiset.A00;
            AnonymousClass3s<E> r8 = abstractMapBasedMultiset.A01;
            int i = this.A01;
            abstractMapBasedMultiset.A00 = j - ((long) AnonymousClass3s.A00(r8, r8.A07[i], (int) (r8.A06[i] >>> 32)));
            AnonymousClass3s<E> r2 = abstractMapBasedMultiset.A01;
            this.A00 = r2.A06(this.A00, this.A01);
            this.A01 = -1;
            this.A02 = r2.A01;
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
            this.A00 = this.A03.A01.A05(i);
            return A002;
        }
        throw new NoSuchElementException();
    }
}
