package defpackage;

import J.N;
import android.os.Build;

/* renamed from: Ks1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Ks1 {
    public static int a(String str) {
        if (!str.isEmpty()) {
            String[] split = str.replaceAll("[^\\d.]", "").split("\\.");
            if (split.length == 4) {
                try {
                    return Integer.parseInt(split[0]);
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Application version incorrectly formatted");
                }
            } else {
                throw new IllegalArgumentException("Application version incorrectly formatted");
            }
        } else {
            throw new IllegalArgumentException("Application version incorrectly formatted");
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= (N.M09VlOh_("KitKatSupported") ? 19 : 21);
    }
}
