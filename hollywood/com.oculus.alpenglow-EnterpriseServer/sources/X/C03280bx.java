package X;

/* renamed from: X.0bx  reason: invalid class name and case insensitive filesystem */
public class C03280bx extends AnonymousClass0J9 {
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r2 == null) goto L_0x0045;
     */
    @Override // X.AnonymousClass0J9
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(android.content.Context r5, byte[] r6) throws java.io.IOException {
        /*
            r4 = this;
            java.io.File r0 = r4.A00
            X.AnonymousClass0Q1.A00(r0)
            boolean r0 = r0.exists()
            r3 = 1
            if (r0 != 0) goto L_0x000d
            return r3
        L_0x000d:
            android.content.res.AssetManager r2 = r5.getAssets()
            java.lang.String r1 = r4.A02
            r0 = 2
            java.io.InputStream r2 = r2.open(r1, r0)
            r0 = 2147483647(0x7fffffff, float:NaN)
            byte[] r1 = X.AnonymousClass0JA.A03(r2, r6, r0)     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0024
            r2.close()
        L_0x0024:
            java.io.File r0 = r4.A00
            X.AnonymousClass0Q1.A00(r0)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r0)
            int r0 = r1.length     // Catch:{ all -> 0x003d }
            int r0 = r0 + r3
            byte[] r0 = X.AnonymousClass0JA.A03(r2, r6, r0)     // Catch:{ all -> 0x003d }
            r2.close()
            boolean r0 = java.util.Arrays.equals(r1, r0)
            r0 = r0 ^ r3
            return r0
        L_0x003d:
            r0 = move-exception
            goto L_0x0042
        L_0x003f:
            r0 = move-exception
            if (r2 == 0) goto L_0x0045
        L_0x0042:
            r2.close()     // Catch:{ all -> 0x0045 }
        L_0x0045:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03280bx.A02(android.content.Context, byte[]):boolean");
    }

    public C03280bx(String str, String str2) {
        super(str, str2);
    }
}
