package org.chromium.net;

import J.N;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetworkActivationRequest extends ConnectivityManager.NetworkCallback {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectivityManager f11003a;
    public final Object b = new Object();
    public long c;

    public NetworkActivationRequest(long j, int i) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        this.f11003a = connectivityManager;
        if (connectivityManager != null) {
            try {
                connectivityManager.requestNetwork(new NetworkRequest.Builder().addTransportType(i).addCapability(12).build(), this);
                this.c = j;
            } catch (SecurityException unused) {
            }
        }
    }

    public static NetworkActivationRequest createMobileNetworkRequest(long j) {
        return new NetworkActivationRequest(j, 0);
    }

    public void onAvailable(Network network) {
        synchronized (this.b) {
            long j = this.c;
            if (j != 0) {
                int i = C0646Kn0.f8387a;
                N.MJRUHS0T(j, network.getNetworkHandle());
            }
        }
    }

    public final void unregister() {
        boolean z;
        synchronized (this.b) {
            z = this.c != 0;
            this.c = 0;
        }
        if (z) {
            this.f11003a.unregisterNetworkCallback(this);
        }
    }
}
