package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.feedback.ConnectivityChecker;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: dy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2271dy {

    /* renamed from: a  reason: collision with root package name */
    public final Map f9821a = new HashMap();
    public final int b;
    public final C1579Zx c;
    public final long d;

    public C2271dy(Profile profile, int i, C1579Zx zx) {
        this.b = i;
        this.c = zx;
        this.d = SystemClock.elapsedRealtime();
        for (int i2 = 0; i2 < 4; i2++) {
            C2101cy cyVar = new C2101cy(this, i2);
            if (i2 == 0) {
                Object obj = ThreadUtils.f10596a;
                N.MvuVYy8Q(profile, "http://clients4.google.com/generate_204", (long) i, cyVar);
            } else if (i2 == 1) {
                Object obj2 = ThreadUtils.f10596a;
                N.MvuVYy8Q(profile, "https://clients4.google.com/generate_204", (long) i, cyVar);
            } else if (i2 == 2) {
                ConnectivityChecker.a(false, i, cyVar);
            } else if (i2 != 3) {
                AbstractC1220Ua0.a("feedback", AbstractC2531fV.w("Failed to recognize type ", i2), new Object[0]);
            } else {
                ConnectivityChecker.a(true, i, cyVar);
            }
        }
    }

    public static String a(int i) {
        if (i == 0) {
            return "HTTP connection check (Chrome network stack)";
        }
        if (i == 1) {
            return "HTTPS connection check (Chrome network stack)";
        }
        if (i == 2) {
            return "HTTP connection check (Android network stack)";
        }
        if (i == 3) {
            return "HTTPS connection check (Android network stack)";
        }
        throw new IllegalArgumentException(AbstractC2531fV.w("Unknown connection type: ", i));
    }

    public static String c(int i) {
        if (i == 0) {
            return "UNKNOWN";
        }
        if (i == 1) {
            return "CONNECTED";
        }
        if (i == 2) {
            return "NOT_CONNECTED";
        }
        if (i == 3) {
            return "TIMEOUT";
        }
        if (i == 4) {
            return "ERROR";
        }
        throw new IllegalArgumentException(AbstractC2531fV.w("Unknown result value: ", i));
    }

    public C1759ay b() {
        Object obj = ThreadUtils.f10596a;
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 4; i++) {
            if (this.f9821a.containsKey(Integer.valueOf(i))) {
                hashMap.put(Integer.valueOf(i), (Integer) this.f9821a.get(Integer.valueOf(i)));
            } else {
                hashMap.put(Integer.valueOf(i), 0);
            }
        }
        return new C1759ay(hashMap, this.b, SystemClock.elapsedRealtime() - this.d, NetworkChangeNotifier.f11004a.getCurrentConnectionType());
    }
}
