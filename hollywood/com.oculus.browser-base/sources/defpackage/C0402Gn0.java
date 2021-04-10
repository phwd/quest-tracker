package defpackage;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* renamed from: Gn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0402Gn0 extends ConnectivityManager.NetworkCallback {

    /* renamed from: a  reason: collision with root package name */
    public Network f8110a;
    public final /* synthetic */ C0646Kn0 b;

    public C0402Gn0(C0646Kn0 kn0, RunnableC5651xn0 xn0) {
        this.b = kn0;
    }

    public final boolean a(Network network, NetworkCapabilities networkCapabilities) {
        Network network2 = this.f8110a;
        if (network2 != null && !network2.equals(network)) {
            return true;
        }
        if (networkCapabilities == null) {
            networkCapabilities = this.b.h.f11768a.getNetworkCapabilities(network);
        }
        return networkCapabilities == null || (networkCapabilities.hasTransport(4) && !this.b.h.e(network));
    }

    public void onAvailable(Network network) {
        Network network2;
        NetworkCapabilities networkCapabilities = this.b.h.f11768a.getNetworkCapabilities(network);
        if (!a(network, networkCapabilities)) {
            boolean z = networkCapabilities.hasTransport(4) && ((network2 = this.f8110a) == null || !network.equals(network2));
            if (z) {
                this.f8110a = network;
            }
            this.b.g(new RunnableC0097Bn0(this, network.getNetworkHandle(), this.b.h.a(network), z));
        }
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        if (!a(network, networkCapabilities)) {
            this.b.g(new RunnableC0158Cn0(this, network.getNetworkHandle(), this.b.h.a(network)));
        }
    }

    public void onLosing(Network network, int i) {
        if (!a(network, null)) {
            this.b.g(new RunnableC0219Dn0(this, network.getNetworkHandle()));
        }
    }

    public void onLost(Network network) {
        Network network2 = this.f8110a;
        if (!(network2 != null && !network2.equals(network))) {
            this.b.g(new RunnableC0280En0(this, network));
            if (this.f8110a != null) {
                this.f8110a = null;
                for (Network network3 : C0646Kn0.e(this.b.h, network)) {
                    onAvailable(network3);
                }
                this.b.g(new RunnableC0341Fn0(this, this.b.f().b()));
            }
        }
    }
}
