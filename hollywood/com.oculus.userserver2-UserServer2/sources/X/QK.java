package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class QK<T> implements Iterator<T> {
    public int A00;
    public int A01 = -1;
    public int A02;
    public final /* synthetic */ AbstractMapBasedMultiset A03;

    public QK(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        this.A03 = abstractMapBasedMultiset;
        RB<E> rb = abstractMapBasedMultiset.A01;
        this.A00 = rb.A02();
        this.A02 = rb.A01;
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
            RB<E> rb = abstractMapBasedMultiset.A01;
            int i = this.A01;
            abstractMapBasedMultiset.A00 = j - ((long) RB.A01(rb, rb.A07[i], (int) (rb.A06[i] >>> 32)));
            RB<E> rb2 = abstractMapBasedMultiset.A01;
            int i2 = this.A00;
            int i3 = this.A01;
            if (!(rb2 instanceof C0103Ma)) {
                i2--;
            } else if (i2 == rb2.A02) {
                i2 = i3;
            }
            this.A00 = i2;
            this.A01 = -1;
            this.A02 = rb2.A01;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final T next() {
        T t;
        if (hasNext()) {
            int i = this.A00;
            if (!(this instanceof Nd)) {
                RB<E> rb = ((C0109Ne) this).A00.A01;
                Preconditions.checkElementIndex(i, rb.A02);
                t = (T) rb.A07[i];
            } else {
                RB<E> rb2 = ((Nd) this).A00.A01;
                Preconditions.checkElementIndex(i, rb2.A02);
                t = (T) new AnonymousClass9V(rb2, i);
            }
            int i2 = this.A00;
            this.A01 = i2;
            this.A00 = this.A03.A01.A03(i2);
            return t;
        }
        throw new NoSuchElementException();
    }
}
