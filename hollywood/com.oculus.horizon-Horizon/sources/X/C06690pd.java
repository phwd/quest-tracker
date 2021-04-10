package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0pd  reason: invalid class name and case insensitive filesystem */
public class C06690pd implements Iterator<T> {
    public int A00;
    public int A01;
    public int A02 = -1;
    public int A03;
    public final /* synthetic */ AbstractC06700pe A04;

    public C06690pd(AbstractC06700pe r3) {
        this.A04 = r3;
        HashBiMap<K, V> hashBiMap = r3.A00;
        this.A01 = hashBiMap.A01;
        this.A00 = hashBiMap.A02;
        this.A03 = hashBiMap.A00;
    }

    public final boolean hasNext() {
        if (this.A04.A00.A02 != this.A00) {
            throw new ConcurrentModificationException();
        } else if (this.A01 == -2 || this.A03 <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public final void remove() {
        HashBiMap<K, V> hashBiMap = this.A04.A00;
        if (hashBiMap.A02 == this.A00) {
            boolean z = false;
            if (this.A02 != -1) {
                z = true;
            }
            Preconditions.checkState(z, "no calls to next() since the last call to remove()");
            int i = this.A02;
            hashBiMap.A0E(i, C06710pf.A02(hashBiMap.A0B[i]));
            if (this.A01 == hashBiMap.A00) {
                this.A01 = this.A02;
            }
            this.A02 = -1;
            this.A00 = hashBiMap.A02;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            AbstractC06700pe r0 = this.A04;
            int i = this.A01;
            T t = (T) r0.A00(i);
            this.A02 = i;
            this.A01 = r0.A00.A09[i];
            this.A03--;
            return t;
        }
        throw new NoSuchElementException();
    }
}
