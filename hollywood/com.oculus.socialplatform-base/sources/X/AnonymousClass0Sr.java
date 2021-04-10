package X;

import java.io.IOException;

/* renamed from: X.0Sr  reason: invalid class name */
public class AnonymousClass0Sr extends C02250iL {
    public static final char[] A00;
    public static final AnonymousClass0Sr A01 = new AnonymousClass0Sr();
    public static final String A02;

    @Override // X.C02250iL, X.AbstractC03950os
    public final boolean A61() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0 == null) goto L_0x000f;
     */
    static {
        /*
            X.0Sr r0 = new X.0Sr
            r0.<init>()
            X.AnonymousClass0Sr.A01 = r0
            java.lang.String r0 = "line.separator"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0011
        L_0x000f:
            java.lang.String r0 = "\n"
        L_0x0011:
            X.AnonymousClass0Sr.A02 = r0
            r0 = 64
            char[] r1 = new char[r0]
            X.AnonymousClass0Sr.A00 = r1
            r0 = 32
            java.util.Arrays.fill(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Sr.<clinit>():void");
    }

    @Override // X.C02250iL, X.AbstractC03950os
    public final void ABG(AbstractC02300iS r4, int i) throws IOException, C02310iT {
        r4.A0T(A02);
        if (i > 0) {
            int i2 = i + i;
            while (i2 > 64) {
                char[] cArr = A00;
                r4.A0Z(cArr, 0, 64);
                i2 -= cArr.length;
            }
            r4.A0Z(A00, 0, i2);
        }
    }
}
