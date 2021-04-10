package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0pD  reason: invalid class name */
public abstract class AnonymousClass0pD<T> implements Iterator<T> {
    public int A00;
    public int A01;
    public int A02;
    public final /* synthetic */ CompactHashMap A03;

    public abstract T A00(int i);

    public AnonymousClass0pD(CompactHashMap compactHashMap) {
        this.A03 = compactHashMap;
        this.A02 = compactHashMap.A01;
        this.A00 = compactHashMap.isEmpty() ? -1 : 0;
        this.A01 = -1;
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
        if (compactHashMap.A01 != this.A02) {
            throw new ConcurrentModificationException();
        } else if (hasNext()) {
            int i = this.A00;
            this.A01 = i;
            T A002 = A00(i);
            int i2 = i + 1;
            if (i2 >= compactHashMap.A02) {
                i2 = -1;
            }
            this.A00 = i2;
            return A002;
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        CompactHashMap compactHashMap = this.A03;
        if (compactHashMap.A01 == this.A02) {
            boolean z = false;
            if (this.A01 >= 0) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            this.A02++;
            int i = this.A01;
            CompactHashMap.A01(compactHashMap, compactHashMap.A06[i], (int) (compactHashMap.A05[i] >>> 32));
            this.A00--;
            this.A01 = -1;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
