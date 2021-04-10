package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class UH implements ListIterator {
    public int A00;
    public C1168uP A01;
    public C1168uP A02;
    public C1168uP A03;
    public final Object A04;
    public final /* synthetic */ LinkedListMultimap A05;

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        this.A03 = LinkedListMultimap.A00(this.A05, this.A04, obj, this.A02);
        this.A00++;
        this.A01 = null;
    }

    public final boolean hasNext() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        C1168uP uPVar = this.A02;
        if (uPVar != null) {
            this.A01 = uPVar;
            this.A03 = uPVar;
            this.A02 = uPVar.A00;
            this.A00++;
            return uPVar.A04;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        C1168uP uPVar = this.A03;
        if (uPVar != null) {
            this.A01 = uPVar;
            this.A02 = uPVar;
            this.A03 = uPVar.A01;
            this.A00--;
            return uPVar.A04;
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.A00 - 1;
    }

    public final void remove() {
        boolean z = false;
        if (this.A01 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        C1168uP uPVar = this.A01;
        if (uPVar != this.A02) {
            this.A03 = uPVar.A01;
            this.A00--;
        } else {
            this.A02 = uPVar.A00;
        }
        LinkedListMultimap.A01(this.A05, uPVar);
        this.A01 = null;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        C1168uP uPVar = this.A01;
        boolean z = false;
        if (uPVar != null) {
            z = true;
        }
        Preconditions.checkState(z);
        uPVar.A04 = obj;
    }

    public final int nextIndex() {
        return this.A00;
    }

    public UH(LinkedListMultimap linkedListMultimap, Object obj) {
        C1168uP uPVar;
        this.A05 = linkedListMultimap;
        this.A04 = obj;
        UF uf = (UF) linkedListMultimap.A04.get(obj);
        if (uf == null) {
            uPVar = null;
        } else {
            uPVar = uf.A01;
        }
        this.A02 = uPVar;
    }

    public UH(LinkedListMultimap linkedListMultimap, Object obj, int i) {
        int i2;
        C1168uP uPVar;
        C1168uP uPVar2;
        this.A05 = linkedListMultimap;
        UF uf = (UF) linkedListMultimap.A04.get(obj);
        if (uf == null) {
            i2 = 0;
        } else {
            i2 = uf.A00;
        }
        Preconditions.checkPositionIndex(i, i2);
        if (i < (i2 >> 1)) {
            if (uf == null) {
                uPVar = null;
            } else {
                uPVar = uf.A01;
            }
            this.A02 = uPVar;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                next();
                i = i3;
            }
        } else {
            if (uf == null) {
                uPVar2 = null;
            } else {
                uPVar2 = uf.A02;
            }
            this.A03 = uPVar2;
            this.A00 = i2;
            while (true) {
                int i4 = i + 1;
                if (i >= i2) {
                    break;
                }
                previous();
                i = i4;
            }
        }
        this.A04 = obj;
        this.A01 = null;
    }
}
