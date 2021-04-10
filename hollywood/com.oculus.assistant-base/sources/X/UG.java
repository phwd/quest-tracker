package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class UG implements ListIterator {
    public int A00;
    public int A01;
    public C1168uP A02;
    public C1168uP A03;
    public C1168uP A04;
    public final /* synthetic */ LinkedListMultimap A05;

    public UG(LinkedListMultimap linkedListMultimap, int i) {
        this.A05 = linkedListMultimap;
        this.A00 = linkedListMultimap.A00;
        int size = linkedListMultimap.size();
        Preconditions.checkPositionIndex(i, size);
        if (i < (size >> 1)) {
            this.A03 = linkedListMultimap.A02;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                A00();
                C1168uP uPVar = this.A03;
                if (uPVar != null) {
                    this.A02 = uPVar;
                    this.A04 = uPVar;
                    this.A03 = uPVar.A02;
                    this.A01++;
                    i = i2;
                } else {
                    throw new NoSuchElementException();
                }
            }
        } else {
            this.A04 = linkedListMultimap.A03;
            this.A01 = size;
            while (true) {
                int i3 = i + 1;
                if (i >= size) {
                    break;
                }
                A00();
                C1168uP uPVar2 = this.A04;
                if (uPVar2 != null) {
                    this.A02 = uPVar2;
                    this.A03 = uPVar2;
                    this.A04 = uPVar2.A03;
                    this.A01--;
                    i = i3;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        this.A02 = null;
    }

    private void A00() {
        if (this.A05.A00 != this.A00) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final int previousIndex() {
        return this.A01 - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        A00();
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        A00();
        if (this.A04 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object next() {
        A00();
        C1168uP uPVar = this.A03;
        if (uPVar != null) {
            this.A02 = uPVar;
            this.A04 = uPVar;
            this.A03 = uPVar.A02;
            this.A01++;
            return uPVar;
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.A01;
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object previous() {
        A00();
        C1168uP uPVar = this.A04;
        if (uPVar != null) {
            this.A02 = uPVar;
            this.A03 = uPVar;
            this.A04 = uPVar.A03;
            this.A01--;
            return uPVar;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        A00();
        boolean z = false;
        if (this.A02 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        C1168uP uPVar = this.A02;
        if (uPVar != this.A03) {
            this.A04 = uPVar.A03;
            this.A01--;
        } else {
            this.A03 = uPVar.A02;
        }
        LinkedListMultimap linkedListMultimap = this.A05;
        LinkedListMultimap.A01(linkedListMultimap, uPVar);
        this.A02 = null;
        this.A00 = linkedListMultimap.A00;
    }
}
