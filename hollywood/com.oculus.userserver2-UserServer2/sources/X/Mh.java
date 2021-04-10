package X;

import com.google.common.base.Objects;
import com.google.common.collect.Multisets$ImmutableEntry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class Mh<E> {
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (com.google.common.base.Objects.equal(r4.A01, r2.A07[r3]) != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A00() {
        /*
            r5 = this;
            boolean r0 = r5 instanceof X.AnonymousClass9I
            if (r0 != 0) goto L_0x000e
            boolean r0 = r5 instanceof X.AnonymousClass9V
            if (r0 != 0) goto L_0x0022
            r0 = r5
            com.google.common.collect.Multisets$ImmutableEntry r0 = (com.google.common.collect.Multisets$ImmutableEntry) r0
            int r0 = r0.count
        L_0x000d:
            return r0
        L_0x000e:
            r2 = r5
            X.9I r2 = (X.AnonymousClass9I) r2
            X.TP r0 = r2.A00
            int r0 = r0.A01
            if (r0 != 0) goto L_0x000d
            com.google.common.collect.TreeMultiset r1 = r2.A01
            java.lang.Object r0 = r2.A01()
            int r0 = r1.A1D(r0)
            return r0
        L_0x0022:
            r4 = r5
            X.9V r4 = (X.AnonymousClass9V) r4
            int r3 = r4.A00
            r0 = -1
            if (r3 == r0) goto L_0x003c
            X.RB r2 = r4.A02
            int r0 = r2.A02
            if (r3 >= r0) goto L_0x003c
            K r1 = r4.A01
            java.lang.Object[] r0 = r2.A07
            r0 = r0[r3]
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 != 0) goto L_0x0046
        L_0x003c:
            X.RB r2 = r4.A02
            K r0 = r4.A01
            int r0 = r2.A04(r0)
            r4.A00 = r0
        L_0x0046:
            int r1 = r4.A00
            r0 = -1
            if (r1 != r0) goto L_0x004d
            r0 = 0
            return r0
        L_0x004d:
            int[] r0 = r2.A05
            r0 = r0[r1]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Mh.A00():int");
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [E, K] */
    public final E A01() {
        if (this instanceof AnonymousClass9I) {
            return ((AnonymousClass9I) this).A00.A08;
        }
        if (!(this instanceof AnonymousClass9V)) {
            return ((Multisets$ImmutableEntry) this).element;
        }
        return ((AnonymousClass9V) this).A01;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof Mh)) {
            return false;
        }
        Mh mh = (Mh) obj;
        if (A00() != mh.A00() || !Objects.equal(A01(), mh.A01())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        E A01 = A01();
        if (A01 == null) {
            hashCode = 0;
        } else {
            hashCode = A01.hashCode();
        }
        return hashCode ^ A00();
    }

    public final String toString() {
        String valueOf = String.valueOf(A01());
        int A00 = A00();
        if (A00 == 1) {
            return valueOf;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(" x ");
        sb.append(A00);
        return sb.toString();
    }
}
