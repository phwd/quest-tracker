package org.chromium.chrome.browser;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlayServicesVersionInfo {
    public static String getGmsInfo() {
        int i;
        Context applicationContext = ContextUtils.getApplicationContext();
        long j = (long) SV.e;
        try {
            i = applicationContext.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = 0;
        }
        long j2 = (long) i;
        Objects.requireNonNull(YM.f9268a);
        ContextUtils.getApplicationContext();
        ContextUtils.getApplicationContext();
        return String.format(Locale.US, "SDK=%s; Installed=%s; Access=%s", Long.valueOf(j), Long.valueOf(j2), "none");
    }
}
