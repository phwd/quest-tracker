package defpackage;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

/* renamed from: yn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5821yn0 extends ConnectivityManager.NetworkCallback {

    /* renamed from: a  reason: collision with root package name */
    public LinkProperties f11698a;
    public NetworkCapabilities b;
    public final /* synthetic */ C0646Kn0 c;

    public C5821yn0(C0646Kn0 kn0, RunnableC5651xn0 xn0) {
        this.c = kn0;
    }

    public final C0463Hn0 a(Network network) {
        int i;
        int i2;
        int i3 = 1;
        int i4 = -1;
        if (!this.b.hasTransport(1) && !this.b.hasTransport(5)) {
            if (this.b.hasTransport(0)) {
                NetworkInfo d = this.c.h.d(network);
                if (d != null) {
                    i4 = d.getSubtype();
                }
                i2 = 0;
                i = i4;
                return new C0463Hn0(true, i2, i, String.valueOf(network.getNetworkHandle()), C4179p7.d(this.f11698a), C4179p7.b(this.f11698a));
            } else if (this.b.hasTransport(3)) {
                i3 = 9;
            } else if (this.b.hasTransport(2)) {
                i3 = 7;
            } else if (this.b.hasTransport(4)) {
                i3 = 17;
            } else {
                i2 = -1;
                i = -1;
                return new C0463Hn0(true, i2, i, String.valueOf(network.getNetworkHandle()), C4179p7.d(this.f11698a), C4179p7.b(this.f11698a));
            }
        }
        i2 = i3;
        i = i4;
        return new C0463Hn0(true, i2, i, String.valueOf(network.getNetworkHandle()), C4179p7.d(this.f11698a), C4179p7.b(this.f11698a));
    }

    public void onAvailable(Network network) {
        this.f11698a = null;
        this.b = null;
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        this.b = networkCapabilities;
        C0646Kn0 kn0 = this.c;
        if (kn0.k && this.f11698a != null && networkCapabilities != null) {
            kn0.c(a(network));
        }
    }

    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        this.f11698a = linkProperties;
        C0646Kn0 kn0 = this.c;
        if (kn0.k && linkProperties != null && this.b != null) {
            kn0.c(a(network));
        }
    }

    public void onLost(Network network) {
        this.f11698a = null;
        this.b = null;
        C0646Kn0 kn0 = this.c;
        if (kn0.k) {
            kn0.c(new C0463Hn0(false, -1, -1, null, false, ""));
        }
    }
}
