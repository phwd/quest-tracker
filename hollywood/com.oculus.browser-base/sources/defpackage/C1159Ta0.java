package defpackage;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Ta0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1159Ta0 {

    /* renamed from: a  reason: collision with root package name */
    public static C1159Ta0 f8966a;

    public static C1159Ta0 a() {
        Object obj = ThreadUtils.f10596a;
        if (f8966a == null) {
            f8966a = new C1159Ta0();
        }
        return f8966a;
    }

    public Intent b() {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(268435456);
        return intent;
    }

    public boolean c() {
        return d("android.permission.ACCESS_COARSE_LOCATION") || d("android.permission.ACCESS_FINE_LOCATION");
    }

    public final boolean d(String str) {
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), str, Process.myPid(), Process.myUid()) == 0;
    }

    public boolean e() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (Build.VERSION.SDK_INT < 28) {
            return Settings.Secure.getInt(applicationContext.getContentResolver(), "location_mode", 0) != 0;
        }
        LocationManager locationManager = (LocationManager) applicationContext.getSystemService("location");
        return locationManager != null && C4179p7.c(locationManager);
    }
}
