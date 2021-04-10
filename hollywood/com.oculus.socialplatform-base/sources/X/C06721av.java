package X;

import java.io.InputStream;

/* renamed from: X.1av  reason: invalid class name and case insensitive filesystem */
public final class C06721av implements AbstractC06701at<InputStream> {
    public final AnonymousClass1hX A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.io.File, X.1cO] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0029 */
    @Override // X.AbstractC06701at
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A2m(@androidx.annotation.NonNull java.io.InputStream r7, @androidx.annotation.NonNull java.io.File r8, @androidx.annotation.NonNull X.AnonymousClass1cO r9) {
        /*
            r6 = this;
            java.io.InputStream r7 = (java.io.InputStream) r7
            X.1hX r5 = r6.A00
            java.lang.Class<byte[]> r1 = byte[].class
            r0 = 65536(0x10000, float:9.18355E-41)
            java.lang.Object r4 = r5.A04(r0, r1)
            byte[] r4 = (byte[]) r4
            r3 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0030 }
            r2.<init>(r8)     // Catch:{ all -> 0x0030 }
        L_0x0014:
            int r1 = r7.read(r4)     // Catch:{ IOException -> 0x0029, all -> 0x0024 }
            r0 = -1
            if (r1 == r0) goto L_0x001f
            r2.write(r4, r3, r1)     // Catch:{ IOException -> 0x0029, all -> 0x0024 }
            goto L_0x0014
        L_0x001f:
            r2.close()     // Catch:{ IOException -> 0x0029, all -> 0x0024 }
            r3 = 1
            goto L_0x0029
        L_0x0024:
            r0 = move-exception
            r2.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0031
        L_0x0029:
            r2.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            r5.A05(r4)
            return r3
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            r5.A05(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06721av.A2m(java.lang.Object, java.io.File, X.1cO):boolean");
    }

    public C06721av(AnonymousClass1hX r1) {
        this.A00 = r1;
    }
}
