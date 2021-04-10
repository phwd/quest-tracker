package defpackage;

import J.N;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappGeolocationBridge;

/* renamed from: B20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class B20 implements Runnable {
    public final C20 F;
    public final String G;
    public final Bundle H;

    public B20(C20 c20, String str, Bundle bundle) {
        this.F = c20;
        this.G = str;
        this.H = bundle;
    }

    public void run() {
        C20 c20 = this.F;
        String str = this.G;
        Bundle bundle = this.H;
        Objects.requireNonNull(c20);
        if (TextUtils.equals(str, "onNewLocationAvailable")) {
            InstalledWebappGeolocationBridge installedWebappGeolocationBridge = c20.f7780a;
            Objects.requireNonNull(installedWebappGeolocationBridge);
            if (bundle != null && installedWebappGeolocationBridge.f10623a != 0) {
                N.M5uE1cdn(installedWebappGeolocationBridge.f10623a, bundle.getDouble("latitude"), bundle.getDouble("longitude"), ((double) bundle.getLong("timeStamp")) / 1000.0d, bundle.containsKey("altitude"), bundle.getDouble("altitude"), bundle.containsKey("accuracy"), bundle.getDouble("accuracy"), bundle.containsKey("bearing"), bundle.getDouble("bearing"), bundle.containsKey("speed"), bundle.getDouble("speed"));
            }
        } else if (TextUtils.equals(str, "onNewLocationError")) {
            String str2 = "";
            if (bundle != null) {
                str2 = bundle.getString("message", str2);
            }
            long j = c20.f7780a.f10623a;
            if (j != 0) {
                N.M243l30e(j, str2);
            }
        }
    }
}
