package defpackage;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: fu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2602fu0 implements AbstractC1818bH0 {
    public static C2602fu0 F;
    public byte[] G;
    public byte[] H;

    public C2602fu0() {
        a(false);
        ProfileManager.f10754a.b(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Not enough random data available"
            java.lang.String r1 = "/dev/urandom"
            r2 = 0
            r3 = 20
            if (r7 != 0) goto L_0x002d
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x0025 }
            r7.<init>(r1)     // Catch:{ all -> 0x0025 }
            byte[] r4 = new byte[r3]     // Catch:{ all -> 0x0022 }
            int r5 = r7.read(r4)     // Catch:{ all -> 0x0022 }
            if (r3 != r5) goto L_0x001c
            r7.close()     // Catch:{ IOException -> 0x0042, GeneralSecurityException -> 0x0040 }
            r6.G = r4     // Catch:{ IOException -> 0x0042, GeneralSecurityException -> 0x0040 }
            goto L_0x002d
        L_0x001c:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            r1.<init>(r0)
            throw r1
        L_0x0022:
            r0 = move-exception
            r2 = r7
            goto L_0x0027
        L_0x0025:
            r7 = move-exception
            r0 = r7
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()
        L_0x002c:
            throw r0
        L_0x002d:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x004d }
            r7.<init>(r1)     // Catch:{ all -> 0x004d }
            byte[] r1 = new byte[r3]     // Catch:{ all -> 0x004a }
            int r2 = r7.read(r1)     // Catch:{ all -> 0x004a }
            if (r3 != r2) goto L_0x0044
            r7.close()
            r6.H = r1
            return
        L_0x0040:
            r7 = move-exception
            goto L_0x0055
        L_0x0042:
            r7 = move-exception
            goto L_0x0055
        L_0x0044:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            r1.<init>(r0)
            throw r1
        L_0x004a:
            r0 = move-exception
            r2 = r7
            goto L_0x004f
        L_0x004d:
            r7 = move-exception
            r0 = r7
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            throw r0
        L_0x0055:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2602fu0.a(boolean):void");
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
        if (profile.g()) {
            a(true);
        }
    }
}
