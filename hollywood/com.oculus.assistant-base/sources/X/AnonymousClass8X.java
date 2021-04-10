package X;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: X.8X  reason: invalid class name */
public final class AnonymousClass8X {
    public static volatile AnonymousClass8X A05;
    public ConnectivityManager.NetworkCallback A00;
    public final ConnectivityManager A01;
    public final Handler A02 = new Handler(Looper.getMainLooper());
    public final Set A03 = new CopyOnWriteArraySet();
    public final C0409Wl A04 = new C0409Wl("android.net.conn.CONNECTIVITY_CHANGE", new C0743gS(this));

    public static /* synthetic */ int A00(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null || !networkCapabilities.hasCapability(12)) {
            return 0;
        }
        if (networkCapabilities.hasTransport(1)) {
            return 1;
        }
        if (networkCapabilities.hasTransport(0)) {
            return 2;
        }
        return 3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r1 != 5) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AnonymousClass8X r4, X.C0740gP r5) {
        /*
            android.net.ConnectivityManager r0 = r4.A01
            android.net.NetworkInfo r1 = r0.getActiveNetworkInfo()
            r3 = 3
            r2 = 2
            r0 = 1
            if (r1 == 0) goto L_0x0025
            int r1 = r1.getType()
            if (r1 == 0) goto L_0x0023
            if (r1 == r0) goto L_0x0021
            if (r1 == r2) goto L_0x0023
            if (r1 == r3) goto L_0x0023
            r0 = 4
            if (r1 == r0) goto L_0x0023
            r0 = 5
            if (r1 == r0) goto L_0x0023
        L_0x001d:
            A01(r4, r3, r5)
            return
        L_0x0021:
            r3 = 1
            goto L_0x001d
        L_0x0023:
            r3 = 2
            goto L_0x001d
        L_0x0025:
            r3 = 0
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8X.A02(X.8X, X.gP):void");
    }

    public AnonymousClass8X(Context context) {
        IntentFilter intentFilter;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.A01 = connectivityManager;
        if (connectivityManager == null) {
            C0139Dd.A0A("OacrNetworkUtil", "Unable to get ConnectivityManager");
        }
        if (Build.VERSION.SDK_INT < 26 || this.A01 == null) {
            Context applicationContext = context.getApplicationContext();
            C0409Wl wl = this.A04;
            synchronized (wl) {
                if (wl.A00 == null) {
                    wl.A00 = new IntentFilter();
                    AnonymousClass0m r4 = wl.A01;
                    int size = r4.size();
                    for (int i = 0; i < size; i++) {
                        wl.A00.addAction((String) r4.A02[i << 1]);
                    }
                }
                intentFilter = wl.A00;
            }
            applicationContext.registerReceiver(wl, intentFilter);
            return;
        }
        NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addCapability(16).build();
        AnonymousClass8V r2 = new AnonymousClass8V(this);
        this.A00 = r2;
        this.A01.registerNetworkCallback(build, r2, this.A02);
    }

    public static void A01(AnonymousClass8X r3, int i, C0740gP gPVar) {
        C0139Dd.A0G("OacrNetworkUtil", "new network status %s", Integer.valueOf(i));
        if (gPVar != null) {
            C0740gP.A16.post(new C0729gD(gPVar, i));
            return;
        }
        for (C0740gP gPVar2 : r3.A03) {
            C0740gP.A16.post(new C0729gD(gPVar2, i));
        }
    }
}
