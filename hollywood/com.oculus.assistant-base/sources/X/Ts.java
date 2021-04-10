package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Ts implements Iterator {
    public int A00;
    public int A01;
    public int A02;
    public final /* synthetic */ CompactHashSet A03;

    public Ts(CompactHashSet compactHashSet) {
        this.A03 = compactHashSet;
        this.A02 = compactHashSet.A01;
        this.A00 = compactHashSet.isEmpty() ? -1 : 0;
        this.A01 = -1;
    }

    public final boolean hasNext() {
        if (this.A00 >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        CompactHashSet compactHashSet = this.A03;
        if (compactHashSet.A01 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (hasNext()) {
            int i = this.A00;
            this.A01 = i;
            Object obj = compactHashSet.A06[i];
            int i2 = i + 1;
            if (i2 >= compactHashSet.A02) {
                i2 = -1;
            }
            this.A00 = i2;
            return obj;
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        CompactHashSet compactHashSet = this.A03;
        if (compactHashSet.A01 == this.A02) {
            boolean z = false;
            if (this.A01 >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            this.A02++;
            Object[] objArr = compactHashSet.A06;
            int i = this.A01;
            CompactHashSet.A01(compactHashSet, objArr[i], (int) (compactHashSet.A05[i] >>> 32));
            this.A00--;
            this.A01 = -1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
