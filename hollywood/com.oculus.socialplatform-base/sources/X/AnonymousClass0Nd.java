package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@GwtCompatible
/* renamed from: X.0Nd  reason: invalid class name */
public abstract class AnonymousClass0Nd<E> extends AbstractC01540ep<E> {
    public int A00;
    public final int A01;

    public abstract E A00(int i);

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

    public AnonymousClass0Nd(int i, int i2) {
        Preconditions.checkPositionIndex(i2, i);
        this.A01 = i;
        this.A00 = i2;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
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
    public final E previous() {
        if (hasPrevious()) {
            int i = this.A00 - 1;
            this.A00 = i;
            return A00(i);
        }
        throw new NoSuchElementException();
    }
}
