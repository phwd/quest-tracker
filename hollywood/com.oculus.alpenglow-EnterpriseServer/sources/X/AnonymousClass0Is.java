package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0Is  reason: invalid class name */
public class AnonymousClass0Is extends AnonymousClass0Y0<K> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ AnonymousClass0tI A02;

    public AnonymousClass0Is(AnonymousClass0tI r2, int i) {
        this.A02 = r2;
        this.A01 = (K) r2.A07[i];
        this.A00 = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (com.google.common.base.Objects.equal(r4.A01, r2.A07[r3]) != false) goto L_0x0021;
     */
    @Override // X.AnonymousClass0Y0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A00() {
        /*
            r4 = this;
            int r3 = r4.A00
            r0 = -1
            if (r3 == r0) goto L_0x0017
            X.0tI r2 = r4.A02
            int r0 = r2.A02
            if (r3 >= r0) goto L_0x0017
            K r1 = r4.A01
            java.lang.Object[] r0 = r2.A07
            r0 = r0[r3]
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 != 0) goto L_0x0021
        L_0x0017:
            X.0tI r2 = r4.A02
            K r0 = r4.A01
            int r0 = r2.A05(r0)
            r4.A00 = r0
        L_0x0021:
            int r1 = r4.A00
            r0 = -1
            if (r1 != r0) goto L_0x0028
            r0 = 0
            return r0
        L_0x0028:
            int[] r0 = r2.A05
            r0 = r0[r1]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Is.A00():int");
    }

    @Override // X.AnonymousClass0Y0
    public final K A01() {
        return this.A01;
    }
}
