package X;

import com.google.common.base.Objects;
import com.google.common.collect.Multisets$ImmutableEntry;

/* renamed from: X.ua  reason: case insensitive filesystem */
public abstract class AbstractC1179ua {
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (com.google.common.base.Objects.equal(r4.A01, r2.A07[r3]) != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A00() {
        /*
            r5 = this;
            boolean r0 = r5 instanceof X.Bg
            if (r0 != 0) goto L_0x000e
            boolean r0 = r5 instanceof X.C0131Cj
            if (r0 != 0) goto L_0x0022
            r0 = r5
            com.google.common.collect.Multisets$ImmutableEntry r0 = (com.google.common.collect.Multisets$ImmutableEntry) r0
            int r0 = r0.count
        L_0x000d:
            return r0
        L_0x000e:
            r2 = r5
            X.Bg r2 = (X.Bg) r2
            X.Ue r0 = r2.A00
            int r0 = r0.A01
            if (r0 != 0) goto L_0x000d
            com.google.common.collect.TreeMultiset r1 = r2.A01
            java.lang.Object r0 = r2.A01()
            int r0 = r1.A1V(r0)
            return r0
        L_0x0022:
            r4 = r5
            X.Cj r4 = (X.C0131Cj) r4
            int r3 = r4.A00
            r0 = -1
            if (r3 == r0) goto L_0x003c
            X.UQ r2 = r4.A02
            int r0 = r2.A02
            if (r3 >= r0) goto L_0x003c
            java.lang.Object r1 = r4.A01
            java.lang.Object[] r0 = r2.A07
            r0 = r0[r3]
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 != 0) goto L_0x0046
        L_0x003c:
            X.UQ r2 = r4.A02
            java.lang.Object r0 = r4.A01
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
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1179ua.A00():int");
    }

    public final Object A01() {
        if (this instanceof Bg) {
            return ((Bg) this).A00.A08;
        }
        if (!(this instanceof C0131Cj)) {
            return ((Multisets$ImmutableEntry) this).element;
        }
        return ((C0131Cj) this).A01;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AbstractC1179ua)) {
            return false;
        }
        AbstractC1179ua uaVar = (AbstractC1179ua) obj;
        if (A00() != uaVar.A00() || !Objects.equal(A01(), uaVar.A01())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        Object A01 = A01();
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
