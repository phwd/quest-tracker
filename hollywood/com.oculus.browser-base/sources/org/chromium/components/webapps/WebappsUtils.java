package org.chromium.components.webapps;

import android.content.pm.ShortcutManager;
import android.os.Build;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebappsUtils {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10904a;
    public static boolean b;

    public static boolean a() {
        if (!b) {
            if (Build.VERSION.SDK_INT >= 26) {
                ShortcutManager shortcutManager = (ShortcutManager) ContextUtils.getApplicationContext().getSystemService(ShortcutManager.class);
                P21 f0 = P21.f0();
                try {
                    f10904a = shortcutManager.isRequestPinShortcutSupported();
                    f0.close();
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            b = true;
        }
        return f10904a;
        throw th;
    }

    public static String queryFirstWebApkPackage(String str) {
        return AbstractC2612fx1.c(ContextUtils.getApplicationContext(), str);
    }
}
