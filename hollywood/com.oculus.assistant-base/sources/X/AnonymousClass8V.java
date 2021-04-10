package X;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* renamed from: X.8V  reason: invalid class name */
public final class AnonymousClass8V extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ AnonymousClass8X A00;

    public AnonymousClass8V(AnonymousClass8X r1) {
        this.A00 = r1;
    }

    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        C0139Dd.A0F("OacrNetworkUtil", "onCapabilitiesChanged %s", networkCapabilities.toString());
        AnonymousClass8X.A01(this.A00, AnonymousClass8X.A00(networkCapabilities), null);
    }

    public final void onLost(Network network) {
        C0139Dd.A0F("OacrNetworkUtil", "onLost %s", network.toString());
        AnonymousClass8X.A01(this.A00, 0, null);
    }
}
