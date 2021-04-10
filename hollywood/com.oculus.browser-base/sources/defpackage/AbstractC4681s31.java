package defpackage;

import org.chromium.base.ContextUtils;

/* renamed from: s31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4681s31 {
    public static String a(String str, String str2) {
        return AbstractC2531fV.f(str, str2);
    }

    public static void b(String str) {
        ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.subscription_flags", 0).edit().remove(str).apply();
    }
}
