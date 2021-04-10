package org.chromium.chrome.browser.cookies;

import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CookiesFetcher {
    public static boolean a() {
        try {
            if (Profile.b().e()) {
                return false;
            }
            new HA().d(AbstractC2032cb.b);
            return true;
        } catch (RuntimeException e) {
            AbstractC0754Mh1.f8495a.b(e);
            return false;
        }
    }

    public static CanonicalCookie createCookie(String str, String str2, String str3, String str4, long j, long j2, long j3, boolean z, boolean z2, int i, int i2, boolean z3, int i3, int i4) {
        return new CanonicalCookie(str, str2, str3, str4, j, j2, j3, z, z2, i, i2, z3, i3, i4);
    }

    public static CanonicalCookie[] createCookiesArray(int i) {
        return new CanonicalCookie[i];
    }

    public static void onCookieFetchFinished(CanonicalCookie[] canonicalCookieArr) {
        new IA(canonicalCookieArr).d(AbstractC2032cb.b);
    }
}
