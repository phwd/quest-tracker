package X;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

/* renamed from: X.8W  reason: invalid class name */
public final class AnonymousClass8W implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.NetworkUtil$3";
    public final /* synthetic */ C0740gP A00;
    public final /* synthetic */ AnonymousClass8X A01;

    public AnonymousClass8W(AnonymousClass8X r1, C0740gP gPVar) {
        this.A01 = r1;
        this.A00 = gPVar;
    }

    public final void run() {
        AnonymousClass8X r3;
        ConnectivityManager connectivityManager;
        NetworkCapabilities networkCapabilities;
        C0139Dd.A09("OacrNetworkUtil", "addStatusCallback");
        if (Build.VERSION.SDK_INT < 26 || (connectivityManager = (r3 = this.A01).A01) == null) {
            AnonymousClass8X.A02(this.A01, this.A00);
            return;
        }
        if (connectivityManager.getActiveNetwork() == null) {
            networkCapabilities = null;
        } else {
            networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        }
        AnonymousClass8X.A01(r3, AnonymousClass8X.A00(networkCapabilities), null);
    }
}
