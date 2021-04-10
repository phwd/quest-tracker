package defpackage;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import org.chromium.base.ContextUtils;

/* renamed from: Jn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0585Jn0 {

    /* renamed from: a  reason: collision with root package name */
    public C0646Kn0 f8312a;

    public abstract void b();

    public abstract void c(C0646Kn0 kn0);

    public final void d() {
        NetworkCapabilities c;
        C0646Kn0 kn0 = this.f8312a;
        if (kn0.k) {
            kn0.b();
            return;
        }
        if (kn0.n) {
            kn0.b();
        }
        ConnectivityManager.NetworkCallback networkCallback = kn0.g;
        if (networkCallback != null) {
            try {
                kn0.h.f11768a.registerDefaultNetworkCallback(networkCallback, kn0.c);
            } catch (RuntimeException unused) {
                kn0.g = null;
            }
        }
        if (kn0.g == null) {
            kn0.m = ContextUtils.getApplicationContext().registerReceiver(kn0, kn0.d) != null;
        }
        kn0.k = true;
        C0402Gn0 gn0 = kn0.i;
        if (gn0 != null) {
            Network[] e = C0646Kn0.e(gn0.b.h, null);
            gn0.f8110a = null;
            if (e.length == 1 && (c = gn0.b.h.c(e[0])) != null && c.hasTransport(4)) {
                gn0.f8110a = e[0];
            }
            try {
                C5991zn0 zn0 = kn0.h;
                NetworkRequest networkRequest = kn0.j;
                C0402Gn0 gn02 = kn0.i;
                Handler handler = kn0.c;
                if (Build.VERSION.SDK_INT >= 26) {
                    zn0.f11768a.registerNetworkCallback(networkRequest, gn02, handler);
                } else {
                    zn0.f11768a.registerNetworkCallback(networkRequest, gn02);
                }
            } catch (RuntimeException unused2) {
                kn0.o = true;
                kn0.i = null;
            }
            if (!kn0.o && kn0.n) {
                Network[] e2 = C0646Kn0.e(kn0.h, null);
                long[] jArr = new long[e2.length];
                for (int i = 0; i < e2.length; i++) {
                    jArr[i] = e2[i].getNetworkHandle();
                }
                kn0.e.d(jArr);
            }
        }
    }
}
