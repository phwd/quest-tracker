package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public final class UE implements Iterator {
    public int A00;
    public C1168uP A01;
    public C1168uP A02;
    public final Set A03;
    public final /* synthetic */ LinkedListMultimap A04;

    public UE(LinkedListMultimap linkedListMultimap) {
        this.A04 = linkedListMultimap;
        this.A03 = UX.A01(linkedListMultimap.keySet().size());
        LinkedListMultimap linkedListMultimap2 = this.A04;
        this.A02 = linkedListMultimap2.A02;
        this.A00 = linkedListMultimap2.A00;
    }

    private void A00() {
        if (this.A04.A00 != this.A00) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        A00();
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C1168uP uPVar;
        A00();
        C1168uP uPVar2 = this.A02;
        if (uPVar2 != null) {
            this.A01 = uPVar2;
            Set set = this.A03;
            set.add(uPVar2.A05);
            do {
                uPVar = this.A02.A02;
                this.A02 = uPVar;
                if (uPVar == null) {
                    break;
                }
            } while (!set.add(uPVar.A05));
            return this.A01.A05;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        A00();
        boolean z = false;
        if (this.A01 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        LinkedListMultimap linkedListMultimap = this.A04;
        UB.A00(new UH(linkedListMultimap, this.A01.A05));
        this.A01 = null;
        this.A00 = linkedListMultimap.A00;
    }
}
