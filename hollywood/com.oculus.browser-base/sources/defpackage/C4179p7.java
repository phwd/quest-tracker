package defpackage;

import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.net.LinkProperties;

/* renamed from: p7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4179p7 {
    public static long a(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }

    public static String b(LinkProperties linkProperties) {
        return linkProperties.getPrivateDnsServerName();
    }

    public static boolean c(LocationManager locationManager) {
        return locationManager.isLocationEnabled();
    }

    public static boolean d(LinkProperties linkProperties) {
        return linkProperties.isPrivateDnsActive();
    }
}
