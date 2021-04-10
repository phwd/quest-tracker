package defpackage;

import android.os.SystemClock;

/* renamed from: uc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5109uc1 {

    /* renamed from: a  reason: collision with root package name */
    public static long f11423a;
    public static int b;
    public static boolean c;
    public static boolean d;

    public static void a(boolean z) {
        String str;
        if (f11423a > 0) {
            long uptimeMillis = SystemClock.uptimeMillis() - f11423a;
            int i = b;
            if (i == 0) {
                str = "Tabs.SwitchFromCloseLatency";
            } else if (i == 1) {
                str = "Tabs.SwitchFromExitLatency";
            } else if (i == 2) {
                str = "Tabs.SwitchFromNewLatency";
            } else if (i == 3) {
                str = "Tabs.SwitchFromUserLatency";
            } else {
                return;
            }
            String str2 = z ? "_Perceived" : "_Actual";
            AbstractC3364kK0.k(str + str2, uptimeMillis);
        }
    }

    public static void b() {
        if (f11423a > 0 && !d) {
            a(true);
            d = true;
        }
    }
}
