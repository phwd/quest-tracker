package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0tn  reason: invalid class name */
public abstract class AnonymousClass0tn<T> implements Iterator<T> {
    public int A00;
    public int A01 = -1;
    public int A02;
    public final /* synthetic */ CompactHashMap A03;

    public abstract T A00(int i);

    public AnonymousClass0tn(CompactHashMap compactHashMap) {
        this.A03 = compactHashMap;
        this.A02 = compactHashMap.A02;
        this.A00 = compactHashMap.A03();
    }

    public final boolean hasNext() {
        if (this.A00 >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        CompactHashMap compactHashMap = this.A03;
        if (compactHashMap.A02 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (hasNext()) {
            int i = this.A00;
            this.A01 = i;
            T A002 = A00(i);
            this.A00 = compactHashMap.A04(i);
            return A002;
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        CompactHashMap compactHashMap = this.A03;
        if (compactHashMap.A02 == this.A02) {
            boolean z = false;
            if (this.A01 >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            this.A02++;
            int i = this.A01;
            CompactHashMap.A02(compactHashMap, compactHashMap.A07[i], (int) (compactHashMap.A06[i] >>> 32));
            this.A00 = compactHashMap.A05(this.A00, this.A01);
            this.A01 = -1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
