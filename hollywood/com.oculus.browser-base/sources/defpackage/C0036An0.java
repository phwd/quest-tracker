package defpackage;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;

/* renamed from: An0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0036An0 extends ConnectivityManager.NetworkCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0646Kn0 f7696a;

    public C0036An0(C0646Kn0 kn0, RunnableC5651xn0 xn0) {
        this.f7696a = kn0;
    }

    public void onAvailable(Network network) {
        C0646Kn0 kn0 = this.f7696a;
        if (kn0.k) {
            kn0.b();
        }
    }

    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        onAvailable(null);
    }

    public void onLost(Network network) {
        onAvailable(null);
    }
}
