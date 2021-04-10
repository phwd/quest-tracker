package org.chromium.net;

import J.N;
import android.net.ConnectivityManager;
import android.net.Network;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetworkChangeNotifier {

    /* renamed from: a  reason: collision with root package name */
    public static NetworkChangeNotifier f11004a;
    public final ArrayList b = new ArrayList();
    public final C1322Vq0 c = new C1322Vq0();
    public final ConnectivityManager d = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity"));
    public C0646Kn0 e;
    public int f = 0;

    public static void a(AbstractC5481wn0 wn0) {
        f11004a.c.b(wn0);
    }

    public static boolean b() {
        return f11004a != null;
    }

    public static boolean c() {
        return f11004a.getCurrentConnectionType() != 6;
    }

    public static void fakeConnectionSubtypeChanged(int i) {
        k(false);
        f11004a.d(i);
    }

    public static void fakeDefaultNetwork(long j, int i) {
        k(false);
        f11004a.e(i, j);
    }

    public static void fakeNetworkConnected(long j, int i) {
        k(false);
        f11004a.f(j, i);
    }

    public static void fakeNetworkDisconnected(long j) {
        k(false);
        f11004a.g(j);
    }

    public static void fakeNetworkSoonToBeDisconnected(long j) {
        k(false);
        f11004a.h(j);
    }

    public static void fakePurgeActiveNetworkList(long[] jArr) {
        k(false);
        f11004a.i(jArr);
    }

    public static void forceConnectivityState(boolean z) {
        int i = 0;
        k(false);
        NetworkChangeNotifier networkChangeNotifier = f11004a;
        if ((networkChangeNotifier.f != 6) != z) {
            if (!z) {
                i = 6;
            }
            networkChangeNotifier.l(i);
            networkChangeNotifier.d(!z);
        }
    }

    public static NetworkChangeNotifier init() {
        if (f11004a == null) {
            f11004a = new NetworkChangeNotifier();
        }
        return f11004a;
    }

    public static boolean isProcessBoundToNetwork() {
        return f11004a.d.getBoundNetworkForProcess() != null;
    }

    public static void j(AbstractC5481wn0 wn0) {
        f11004a.c.c(wn0);
    }

    public static void k(boolean z) {
        NetworkChangeNotifier networkChangeNotifier = f11004a;
        C5752yL0 yl0 = new C5752yL0();
        if (!z) {
            C0646Kn0 kn0 = networkChangeNotifier.e;
            if (kn0 != null) {
                kn0.d();
                networkChangeNotifier.e = null;
            }
        } else if (networkChangeNotifier.e == null) {
            C0646Kn0 kn02 = new C0646Kn0(new C5311vn0(networkChangeNotifier), yl0);
            networkChangeNotifier.e = kn02;
            C0463Hn0 f2 = kn02.f();
            networkChangeNotifier.l(f2.b());
            networkChangeNotifier.d(f2.a());
        }
    }

    public void addNativeObserver(long j) {
        this.b.add(Long.valueOf(j));
    }

    public void d(int i) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.Mt26m31j(((Long) it.next()).longValue(), this, i);
        }
    }

    public final void e(int i, long j) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MbPIImnU(((Long) it.next()).longValue(), this, i, j);
        }
        Iterator it2 = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it2;
            if (uq0.hasNext()) {
                ((AbstractC5481wn0) uq0.next()).a(i);
            } else {
                return;
            }
        }
    }

    public void f(long j, int i) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MBT1i5cd(((Long) it.next()).longValue(), this, j, i);
        }
    }

    public void g(long j) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MDpuHJTB(((Long) it.next()).longValue(), this, j);
        }
    }

    public int getCurrentConnectionSubtype() {
        C0646Kn0 kn0 = this.e;
        if (kn0 == null) {
            return 0;
        }
        return kn0.f().a();
    }

    public int getCurrentConnectionType() {
        return this.f;
    }

    public long getCurrentDefaultNetId() {
        Network b2;
        C0646Kn0 kn0 = this.e;
        if (kn0 == null || (b2 = kn0.h.b()) == null) {
            return -1;
        }
        return b2.getNetworkHandle();
    }

    public long[] getCurrentNetworksAndTypes() {
        C0646Kn0 kn0 = this.e;
        if (kn0 == null) {
            return new long[0];
        }
        Network[] e2 = C0646Kn0.e(kn0.h, null);
        long[] jArr = new long[(e2.length * 2)];
        int i = 0;
        for (Network network : e2) {
            int i2 = i + 1;
            jArr[i] = network.getNetworkHandle();
            i = i2 + 1;
            jArr[i2] = (long) kn0.h.a(network);
        }
        return jArr;
    }

    public void h(long j) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MiJIMrTb(((Long) it.next()).longValue(), this, j);
        }
    }

    public void i(long[] jArr) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            N.MpF$179U(((Long) it.next()).longValue(), this, jArr);
        }
    }

    public final void l(int i) {
        this.f = i;
        e(i, getCurrentDefaultNetId());
    }

    public boolean registerNetworkCallbackFailed() {
        C0646Kn0 kn0 = this.e;
        if (kn0 == null) {
            return false;
        }
        return kn0.o;
    }

    public void removeNativeObserver(long j) {
        this.b.remove(Long.valueOf(j));
    }
}
