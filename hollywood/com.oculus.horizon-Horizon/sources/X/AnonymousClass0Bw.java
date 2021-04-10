package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0Bw  reason: invalid class name */
public class AnonymousClass0Bw extends AnonymousClass0dN<K> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ AnonymousClass0rF A02;

    public AnonymousClass0Bw(AnonymousClass0rF r2, int i) {
        this.A02 = r2;
        this.A01 = (K) r2.A06[i];
        this.A00 = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (com.google.common.base.Objects.equal(r4.A01, r2.A06[r3]) != false) goto L_0x0021;
     */
    @Override // X.AnonymousClass0dN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A00() {
        /*
            r4 = this;
            int r3 = r4.A00
            r0 = -1
            if (r3 == r0) goto L_0x0017
            X.0rF r2 = r4.A02
            int r0 = r2.A01
            if (r3 >= r0) goto L_0x0017
            K r1 = r4.A01
            java.lang.Object[] r0 = r2.A06
            r0 = r0[r3]
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 != 0) goto L_0x0021
        L_0x0017:
            X.0rF r2 = r4.A02
            K r0 = r4.A01
            int r0 = r2.A06(r0)
            r4.A00 = r0
        L_0x0021:
            int r1 = r4.A00
            r0 = -1
            if (r1 != r0) goto L_0x0028
            r0 = 0
            return r0
        L_0x0028:
            int[] r0 = r2.A04
            r0 = r0[r1]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Bw.A00():int");
    }

    @Override // X.AnonymousClass0dN
    public final K A01() {
        return this.A01;
    }
}
