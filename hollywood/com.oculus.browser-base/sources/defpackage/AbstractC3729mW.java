package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: mW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3729mW {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f10427a = new AtomicBoolean();
    public static final AtomicBoolean b = new AtomicBoolean();

    @Deprecated
    public static void a(Context context) {
        if (!f10427a.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException unused) {
            }
        }
    }

    @Deprecated
    public static void b(Context context, int i) {
        UV uv = UV.b;
        int b2 = uv.b(context, i);
        if (b2 != 0) {
            Intent a2 = uv.a(context, b2, "e");
            StringBuilder sb = new StringBuilder(57);
            sb.append("GooglePlayServices not available due to error ");
            sb.append(b2);
            Log.e("GooglePlayServicesUtil", sb.toString());
            if (a2 == null) {
                throw new C3216jW(b2);
            }
            throw new C3387kW(b2, "Google Play Services not available", a2);
        }
    }

    @Deprecated
    public static boolean c(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return d(context, "com.google.android.gms");
        }
        return false;
    }

    public static boolean d(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        try {
            for (PackageInstaller.SessionInfo sessionInfo : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(sessionInfo.getAppPackageName())) {
                    return true;
                }
            }
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            if (applicationInfo.enabled) {
                Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
                if (!(applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile")))) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
        }
    }
}
