package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.8Q  reason: invalid class name */
public class AnonymousClass8Q implements Iterator<Multiset.Entry<E>> {
    public Multiset.Entry<E> A00;
    public AnonymousClass8V<E> A01;
    public final /* synthetic */ TreeMultiset A02;

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (r5.A03(r2.A08) != false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass8Q(com.google.common.collect.TreeMultiset r7) {
        /*
            r6 = this;
            r6.A02 = r7
            r6.<init>()
            X.8Y<X.8V<E>> r0 = r7.A02
            T r1 = r0.A00
            r4 = 0
            if (r1 == 0) goto L_0x0040
            com.google.common.collect.GeneralRange<E> r5 = r7.A00
            boolean r0 = r5.hasUpperBound
            if (r0 == 0) goto L_0x0046
            T r3 = r5.upperEndpoint
            X.8V r1 = (X.AnonymousClass8V) r1
            java.util.Comparator r0 = r7.comparator()
            X.8V r2 = X.AnonymousClass8V.A06(r1, r0, r3)
            if (r2 == 0) goto L_0x0040
            com.google.common.collect.BoundType r1 = r5.upperBoundType
            com.google.common.collect.BoundType r0 = com.google.common.collect.BoundType.OPEN
            if (r1 != r0) goto L_0x0034
            java.util.Comparator r1 = r7.comparator()
            E r0 = r2.A08
            int r0 = r1.compare(r3, r0)
            if (r0 != 0) goto L_0x0034
            X.8V<E> r2 = r2.A05
        L_0x0034:
            X.8V<E> r0 = r7.A01
            if (r2 == r0) goto L_0x0040
            E r0 = r2.A08
            boolean r0 = r5.A03(r0)
            if (r0 != 0) goto L_0x0041
        L_0x0040:
            r2 = r4
        L_0x0041:
            r6.A01 = r2
            r6.A00 = r4
            return
        L_0x0046:
            X.8V<E> r0 = r7.A01
            X.8V<E> r2 = r0.A05
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8Q.<init>(com.google.common.collect.TreeMultiset):void");
    }

    public final boolean hasNext() {
        AnonymousClass8V<E> r3 = this.A01;
        if (r3 != null) {
            if (!this.A02.A00.A02(r3.A08)) {
                return true;
            }
            this.A01 = null;
        }
        return false;
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A02.A4x(this.A00.A01(), 0);
        this.A00 = null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            TreeMultiset treeMultiset = this.A02;
            AnonymousClass8V<E> r0 = this.A01;
            C0061Az az = new C0061Az(treeMultiset, r0);
            this.A00 = az;
            AnonymousClass8V<E> r1 = r0.A05;
            if (r1 == treeMultiset.A01) {
                r1 = null;
            }
            this.A01 = r1;
            return az;
        }
        throw new NoSuchElementException();
    }
}
