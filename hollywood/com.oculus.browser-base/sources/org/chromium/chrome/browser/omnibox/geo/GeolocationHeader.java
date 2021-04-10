package org.chromium.chrome.browser.omnibox.geo;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import java.util.Locale;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GeolocationHeader {

    /* renamed from: a  reason: collision with root package name */
    public static long f10724a = Long.MAX_VALUE;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r1v4, types: [dI0] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r0v55 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01be, code lost:
        if (r12 == true) goto L_0x0265;
     */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x02b9  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x02e8  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x02f4  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x04ff  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b5  */
    /* JADX WARNING: Unknown variable types count: 10 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r25, org.chromium.chrome.browser.profiles.Profile r26, org.chromium.chrome.browser.tab.Tab r27) {
        /*
        // Method dump skipped, instructions count: 1322
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.geo.GeolocationHeader.a(java.lang.String, org.chromium.chrome.browser.profiles.Profile, org.chromium.chrome.browser.tab.Tab):java.lang.String");
    }

    public static String b(String str, Tab tab) {
        Profile a2 = Profile.a(tab.l());
        if (a2 == null) {
            return null;
        }
        return a(str, a2, tab);
    }

    public static int c() {
        try {
            int i = Settings.Secure.getInt(ContextUtils.getApplicationContext().getContentResolver(), "location_mode");
            if (i == 3) {
                return 0;
            }
            if (i == 1) {
                return 2;
            }
            if (i == 2) {
                return 1;
            }
            return 3;
        } catch (Settings.SettingNotFoundException unused) {
            AbstractC1220Ua0.a("GeolocationHeader", "Error getting the LOCATION_MODE", new Object[0]);
            return 3;
        }
    }

    public static Integer d(Profile profile, Uri uri) {
        return new OB0(5, uri.toString(), null, profile.g()).a(profile);
    }

    public static void e() {
        LocationManager locationManager;
        Location lastKnownLocation;
        if (hasGeolocationPermission()) {
            if (f10724a == Long.MAX_VALUE) {
                f10724a = SystemClock.elapsedRealtime();
            }
            Context applicationContext = ContextUtils.getApplicationContext();
            Object obj = ThreadUtils.f10596a;
            if (AbstractC3385kV.c(applicationContext, "android.permission.ACCESS_COARSE_LOCATION") && AbstractC3385kV.f10283a == null && (((lastKnownLocation = (locationManager = (LocationManager) applicationContext.getSystemService("location")).getLastKnownLocation("network")) == null || AbstractC3385kV.b(lastKnownLocation) > 300000) && locationManager.isProviderEnabled("network"))) {
                C3214jV jVVar = new C3214jV(locationManager, null);
                AbstractC3385kV.f10283a = jVVar;
                try {
                    locationManager.requestSingleUpdate("network", jVVar, (Looper) null);
                } catch (NullPointerException unused) {
                    AbstractC3385kV.f10283a.d = true;
                }
            }
            Lv1.b(ContextUtils.getApplicationContext());
        }
    }

    public static void f(int i) {
        AbstractC3364kK0.g("Geolocation.HeaderSentOrNot", i, 8);
    }

    public static boolean g(Iv1 iv1) {
        if (!(iv1 == null || iv1.c == null)) {
            String str = iv1.b;
            if (str == null || str.indexOf(95) < 0) {
                return false;
            }
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            if (lowerCase.contains("_nomap") || lowerCase.contains("_optout")) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static String getGeoHeader(String str, Profile profile) {
        if (profile == null) {
            return null;
        }
        return a(str, profile, null);
    }

    public static boolean hasGeolocationPermission() {
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION", Process.myPid(), Process.myUid()) == 0;
    }
}
