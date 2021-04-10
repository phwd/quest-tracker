package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.oculus.os.Version;
import java.util.Arrays;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.net.AndroidNetworkLibrary;
import org.chromium.net.DnsStatus;
import org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter;

/* renamed from: Kn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0646Kn0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f8387a = 0;
    public final Looper b;
    public final Handler c;
    public final NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter d;
    public final AbstractC0524In0 e;
    public final AbstractC0585Jn0 f;
    public ConnectivityManager.NetworkCallback g;
    public C5991zn0 h = new C5991zn0(ContextUtils.getApplicationContext());
    public C0402Gn0 i;
    public NetworkRequest j;
    public boolean k;
    public C0463Hn0 l;
    public boolean m;
    public boolean n;
    public boolean o;

    public C0646Kn0(AbstractC0524In0 in0, AbstractC0585Jn0 jn0) {
        Looper myLooper = Looper.myLooper();
        this.b = myLooper;
        this.c = new Handler(myLooper);
        this.e = in0;
        int i2 = Build.VERSION.SDK_INT;
        C0036An0 an0 = null;
        this.i = new C0402Gn0(this, null);
        this.j = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        if (i2 >= 30) {
            this.g = new C5821yn0(this, null);
        } else {
            this.g = i2 >= 28 ? new C0036An0(this, null) : an0;
        }
        this.l = f();
        this.d = new NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter();
        this.m = false;
        this.n = false;
        this.f = jn0;
        jn0.c(this);
        this.n = true;
    }

    public static int a(int i2, int i3) {
        if (i2 != 0) {
            if (i2 == 1) {
                return 2;
            }
            if (!(i2 == 4 || i2 == 5)) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        return i2 != 9 ? 0 : 1;
                    }
                    return 7;
                }
                return 5;
            }
        }
        if (i3 == 20) {
            return 8;
        }
        switch (i3) {
            case 1:
            case 2:
            case 4:
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                return 3;
            case 3:
            case 5:
            case 6:
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return 4;
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                break;
            default:
                return 0;
        }
        return 5;
    }

    public static Network[] e(C5991zn0 zn0, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworks = zn0.f11768a.getAllNetworks();
        if (allNetworks == null) {
            allNetworks = new Network[0];
        }
        int i2 = 0;
        for (Network network2 : allNetworks) {
            if (!network2.equals(network) && (networkCapabilities = zn0.f11768a.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (!networkCapabilities.hasTransport(4)) {
                    allNetworks[i2] = network2;
                    i2++;
                } else if (zn0.e(network2)) {
                    return new Network[]{network2};
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworks, i2);
    }

    public final void b() {
        c(f());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        if (r4.f.equals(r1.f) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(defpackage.C0463Hn0 r4) {
        /*
            r3 = this;
            int r0 = r4.b()
            Hn0 r1 = r3.l
            int r1 = r1.b()
            if (r0 != r1) goto L_0x002a
            java.lang.String r0 = r4.d
            Hn0 r1 = r3.l
            java.lang.String r1 = r1.d
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002a
            boolean r0 = r4.e
            Hn0 r1 = r3.l
            boolean r2 = r1.e
            if (r0 != r2) goto L_0x002a
            java.lang.String r0 = r4.f
            java.lang.String r1 = r1.f
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0033
        L_0x002a:
            In0 r0 = r3.e
            int r1 = r4.b()
            r0.a(r1)
        L_0x0033:
            int r0 = r4.b()
            Hn0 r1 = r3.l
            int r1 = r1.b()
            if (r0 != r1) goto L_0x004b
            int r0 = r4.a()
            Hn0 r1 = r3.l
            int r1 = r1.a()
            if (r0 == r1) goto L_0x0054
        L_0x004b:
            In0 r0 = r3.e
            int r1 = r4.a()
            r0.c(r1)
        L_0x0054:
            r3.l = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0646Kn0.c(Hn0):void");
    }

    public void d() {
        this.f.b();
        h();
    }

    public C0463Hn0 f() {
        C0463Hn0 hn0;
        C5991zn0 zn0 = this.h;
        Network b2 = zn0.b();
        NetworkInfo networkInfo = zn0.f11768a.getNetworkInfo(b2);
        if (networkInfo == null || (!networkInfo.isConnected() && !(networkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED && ApplicationStatus.getStateForApplication() == 1))) {
            networkInfo = null;
        }
        if (networkInfo == null) {
            return new C0463Hn0(false, -1, -1, null, false, "");
        }
        if (b2 != null) {
            DnsStatus dnsStatus = AndroidNetworkLibrary.getDnsStatus(b2);
            if (dnsStatus == null) {
                return new C0463Hn0(true, networkInfo.getType(), networkInfo.getSubtype(), String.valueOf(b2.getNetworkHandle()), false, "");
            }
            return new C0463Hn0(true, networkInfo.getType(), networkInfo.getSubtype(), String.valueOf(b2.getNetworkHandle()), dnsStatus.getPrivateDnsActive(), dnsStatus.getPrivateDnsServerName());
        }
        if (networkInfo.getType() != 1) {
            hn0 = new C0463Hn0(true, networkInfo.getType(), networkInfo.getSubtype(), null, false, "");
        } else if (networkInfo.getExtraInfo() == null || "".equals(networkInfo.getExtraInfo())) {
            networkInfo.getType();
            networkInfo.getSubtype();
            throw null;
        } else {
            hn0 = new C0463Hn0(true, networkInfo.getType(), networkInfo.getSubtype(), networkInfo.getExtraInfo(), false, "");
        }
        return hn0;
    }

    public final void g(Runnable runnable) {
        if (this.b == Looper.myLooper()) {
            runnable.run();
        } else {
            this.c.post(runnable);
        }
    }

    public void h() {
        if (this.k) {
            this.k = false;
            C0402Gn0 gn0 = this.i;
            if (gn0 != null) {
                this.h.f11768a.unregisterNetworkCallback(gn0);
            }
            ConnectivityManager.NetworkCallback networkCallback = this.g;
            if (networkCallback != null) {
                this.h.f11768a.unregisterNetworkCallback(networkCallback);
            } else {
                ContextUtils.getApplicationContext().unregisterReceiver(this);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        g(new RunnableC5651xn0(this));
    }
}
