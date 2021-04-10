package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* renamed from: ru0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4652ru0 {
    public static int a(Context context, String str) {
        if (context == null) {
            AbstractC1220Ua0.d("PackageUtils", AbstractC2531fV.f("Context is null when getting package version : ", str), new Object[0]);
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static boolean b(Context context, String str) {
        if (context == null) {
            AbstractC1220Ua0.d("PackageUtils", AbstractC2531fV.f("Context is null when checking if package is installed : ", str), new Object[0]);
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
