package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.Tj  reason: case insensitive filesystem */
public abstract class AbstractC0359Tj implements Iterator {
    public int A00;
    public int A01 = -1;
    public int A02;
    public final /* synthetic */ AbstractMapBasedMultiset A03;

    public AbstractC0359Tj(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        this.A03 = abstractMapBasedMultiset;
        UQ uq = abstractMapBasedMultiset.A01;
        this.A00 = uq.A02();
        this.A02 = uq.A01;
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
            UQ uq = abstractMapBasedMultiset.A01;
            int i = this.A01;
            abstractMapBasedMultiset.A00 = j - ((long) UQ.A00(uq, uq.A07[i], (int) (uq.A06[i] >>> 32)));
            UQ uq2 = abstractMapBasedMultiset.A01;
            int i2 = this.A00;
            int i3 = this.A01;
            if (!(uq2 instanceof C1182ue)) {
                i2--;
            } else if (i2 == uq2.A02) {
                i2 = i3;
            }
            this.A00 = i2;
            this.A01 = -1;
            this.A02 = uq2.A01;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object cj;
        if (hasNext()) {
            int i = this.A00;
            if (!(this instanceof C1154tu)) {
                UQ uq = ((C1153tt) this).A00.A01;
                Preconditions.checkElementIndex(i, uq.A02);
                cj = uq.A07[i];
            } else {
                UQ uq2 = ((C1154tu) this).A00.A01;
                Preconditions.checkElementIndex(i, uq2.A02);
                cj = new C0131Cj(uq2, i);
            }
            int i2 = this.A00;
            this.A01 = i2;
            this.A00 = this.A03.A01.A03(i2);
            return cj;
        }
        throw new NoSuchElementException();
    }
}
