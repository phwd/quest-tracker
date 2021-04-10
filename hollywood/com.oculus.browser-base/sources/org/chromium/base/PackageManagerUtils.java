package org.chromium.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.TransactionTooLargeException;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PackageManagerUtils {
    public static void a(RuntimeException runtimeException, Intent intent) {
        if ((runtimeException instanceof NullPointerException) || (runtimeException.getCause() instanceof TransactionTooLargeException)) {
            StringBuilder i = AbstractC2531fV.i("Could not resolve Activity for intent ");
            i.append(intent.toString());
            AbstractC1220Ua0.a("PackageManagerUtils", i.toString(), runtimeException);
            return;
        }
        throw runtimeException;
    }

    public static List b() {
        return c(new Intent("android.intent.action.VIEW", Uri.parse("http://")).addCategory("android.intent.category.BROWSABLE"), 131072);
    }

    public static List c(Intent intent, int i) {
        try {
            P21 f0 = P21.f0();
            try {
                List<ResolveInfo> queryIntentActivities = ContextUtils.getApplicationContext().getPackageManager().queryIntentActivities(intent, i);
                f0.close();
                return queryIntentActivities;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (RuntimeException e) {
            a(e, intent);
            return Collections.emptyList();
        }
    }

    public static ResolveInfo d(Intent intent, int i) {
        try {
            P21 g0 = P21.g0();
            try {
                ResolveInfo resolveActivity = ContextUtils.getApplicationContext().getPackageManager().resolveActivity(intent, i);
                g0.close();
                return resolveActivity;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (RuntimeException e) {
            a(e, intent);
            return null;
        }
    }

    public static ResolveInfo e() {
        return d(new Intent("android.intent.action.VIEW", Uri.parse("http://")).addCategory("android.intent.category.BROWSABLE"), 0);
    }

    public static String getSourceDir(String str) {
        try {
            return ContextUtils.getApplicationContext().getPackageManager().getApplicationInfo(str, 0).sourceDir;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
