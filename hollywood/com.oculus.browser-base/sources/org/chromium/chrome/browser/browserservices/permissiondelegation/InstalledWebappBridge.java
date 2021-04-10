package org.chromium.chrome.browser.browserservices.permissiondelegation;

import J.N;
import android.net.Uri;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstalledWebappBridge {

    /* renamed from: a  reason: collision with root package name */
    public static long f10621a;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Permission {

        /* renamed from: a  reason: collision with root package name */
        public final C4649rt0 f10622a;
        public final int b;

        public Permission(C4649rt0 rt0, int i) {
            this.f10622a = rt0;
            this.b = i;
        }
    }

    public static void a(long j, boolean z) {
        if (j != 0) {
            N.MGDg049K(j, z);
        }
    }

    public static void decidePermission(String str, long j) {
        Object obj;
        C4649rt0 a2 = C4649rt0.a(Uri.parse(str));
        if (a2 == null) {
            a(j, false);
            return;
        }
        BC g = AbstractApplicationC3785mq.g();
        Object obj2 = g.k;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = g.k;
                if (obj instanceof C2566fi0) {
                    obj = new VB0(g.g(), g.b(), g.a());
                    KG.b(g.k, obj);
                    g.k = obj;
                }
            }
            obj2 = obj;
        }
        C0672La0 la0 = ((VB0) obj2).f9068a;
        C2414eo1 eo1 = la0.b;
        C0611Ka0 ka0 = new C0611Ka0(la0, a2, j);
        Objects.requireNonNull(eo1);
        eo1.b(a2.f11230a, new Zn1(eo1, ka0));
    }

    public static String getOriginFromPermission(Permission permission) {
        return permission.f10622a.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        if (r8.booleanValue() != false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0096, code lost:
        if (r9.booleanValue() != false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0098, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0022 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge.Permission[] getPermissions(int r13) {
        /*
        // Method dump skipped, instructions count: 178
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge.getPermissions(int):org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge$Permission[]");
    }

    public static int getSettingFromPermission(Permission permission) {
        return permission.b;
    }

    public static void setInstalledWebappProvider(long j) {
        f10621a = j;
    }
}
