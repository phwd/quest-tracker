package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class QQ<T> implements Iterator<T> {
    public int A00;
    public int A01;
    public int A02;
    public final /* synthetic */ CompactHashMap A03;

    public QQ(CompactHashMap compactHashMap) {
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

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    @Override // java.util.Iterator
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T next() {
        /*
            r5 = this;
            com.google.common.collect.CompactHashMap r4 = r5.A03
            int r1 = r4.A01
            int r0 = r5.A02
            if (r1 != r0) goto L_0x0044
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x003e
            int r3 = r5.A00
            r5.A01 = r3
            r1 = r5
            boolean r0 = r5 instanceof X.NH
            if (r0 != 0) goto L_0x0037
            boolean r0 = r5 instanceof X.NK
            if (r0 != 0) goto L_0x002d
            X.NL r1 = (X.NL) r1
            com.google.common.collect.CompactHashMap r0 = r1.A00
            java.lang.Object[] r0 = r0.A06
        L_0x0021:
            r2 = r0[r3]
        L_0x0023:
            int r1 = r3 + 1
            int r0 = r4.A02
            if (r1 < r0) goto L_0x002a
            r1 = -1
        L_0x002a:
            r5.A00 = r1
            return r2
        L_0x002d:
            X.NK r1 = (X.NK) r1
            com.google.common.collect.CompactHashMap r0 = r1.A00
            X.NF r2 = new X.NF
            r2.<init>(r0, r3)
            goto L_0x0023
        L_0x0037:
            X.NH r1 = (X.NH) r1
            com.google.common.collect.CompactHashMap r0 = r1.A00
            java.lang.Object[] r0 = r0.A07
            goto L_0x0021
        L_0x003e:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r0.<init>()
            throw r0
        L_0x0044:
            java.util.ConcurrentModificationException r0 = new java.util.ConcurrentModificationException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.QQ.next():java.lang.Object");
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
