package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import java.io.IOException;
import java.net.Socket;

/* renamed from: zn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5991zn0 {

    /* renamed from: a  reason: collision with root package name */
    public final ConnectivityManager f11768a;

    public C5991zn0(Context context) {
        this.f11768a = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public int a(Network network) {
        NetworkInfo d = d(network);
        if (d != null && d.getType() == 17) {
            d = this.f11768a.getActiveNetworkInfo();
        }
        if (d == null || !d.isConnected()) {
            return 6;
        }
        return C0646Kn0.a(d.getType(), d.getSubtype());
    }

    public Network b() {
        Network activeNetwork = this.f11768a.getActiveNetwork();
        if (activeNetwork != null) {
            return activeNetwork;
        }
        NetworkInfo activeNetworkInfo = this.f11768a.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        Network[] e = C0646Kn0.e(this, null);
        for (Network network : e) {
            NetworkInfo d = d(network);
            if (d != null && (d.getType() == activeNetworkInfo.getType() || d.getType() == 17)) {
                activeNetwork = network;
            }
        }
        return activeNetwork;
    }

    public NetworkCapabilities c(Network network) {
        return this.f11768a.getNetworkCapabilities(network);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:0:0x0000 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.net.Network */
    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: android.net.Network */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.net.NetworkInfo] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        return r1.f11768a.getNetworkInfo(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.NetworkInfo d(android.net.Network r2) {
        /*
            r1 = this;
            android.net.ConnectivityManager r0 = r1.f11768a     // Catch:{ NullPointerException -> 0x0007 }
            android.net.NetworkInfo r2 = r0.getNetworkInfo(r2)     // Catch:{ NullPointerException -> 0x0007 }
            return r2
        L_0x0007:
            android.net.ConnectivityManager r0 = r1.f11768a     // Catch:{ NullPointerException -> 0x000e }
            android.net.NetworkInfo r2 = r0.getNetworkInfo(r2)     // Catch:{ NullPointerException -> 0x000e }
            return r2
        L_0x000e:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5991zn0.d(android.net.Network):android.net.NetworkInfo");
    }

    public boolean e(Network network) {
        Socket socket = new Socket();
        try {
            P21 Y = P21.Y();
            try {
                network.bindSocket(socket);
                Y.close();
                try {
                    socket.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (IOException unused2) {
            try {
                socket.close();
            } catch (IOException unused3) {
            }
            return false;
        } catch (Throwable th2) {
            try {
                socket.close();
            } catch (IOException unused4) {
            }
            throw th2;
        }
    }
}
