package defpackage;

/* renamed from: Nk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0821Nk {
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(int r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x001f }
            java.lang.String r2 = "/dev/urandom"
            r1.<init>(r2)     // Catch:{ all -> 0x001f }
            byte[] r0 = new byte[r4]     // Catch:{ all -> 0x001c }
            int r2 = r1.read(r0)     // Catch:{ all -> 0x001c }
            if (r4 != r2) goto L_0x0014
            r1.close()
            return r0
        L_0x0014:
            java.security.GeneralSecurityException r4 = new java.security.GeneralSecurityException
            java.lang.String r0 = "Not enough random data available"
            r4.<init>(r0)
            throw r4
        L_0x001c:
            r4 = move-exception
            r0 = r1
            goto L_0x0020
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            if (r0 == 0) goto L_0x0025
            r0.close()
        L_0x0025:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0821Nk.a(int):byte[]");
    }
}
