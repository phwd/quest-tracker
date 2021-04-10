package X;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

public abstract class E1 extends AbstractC1186un {
    public int A00;
    public final int A01;

    public Object A00(int i) {
        return ((SQ) this).A00[0 + i];
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        if (this.A00 > 0) {
            return true;
        }
        return false;
    }

    public final int previousIndex() {
        return this.A00 - 1;
    }

    public E1(int i, int i2) {
        Preconditions.checkPositionIndex(i2, i);
        this.A01 = i;
        this.A00 = i2;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i = this.A00;
            this.A00 = i + 1;
            return A00(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.A00;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i = this.A00 - 1;
            this.A00 = i;
            return A00(i);
        }
        throw new NoSuchElementException();
    }
}
