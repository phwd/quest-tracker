package org.chromium.ui.base;

import java.util.Locale;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocalizationUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f11018a;

    public static String getDisplayNameForLocale(Locale locale, Locale locale2) {
        return locale.getDisplayName(locale2);
    }

    public static Locale getJavaLocale(String str, String str2, String str3) {
        return new Locale(str, str2, str3);
    }

    public static boolean isLayoutRtl() {
        if (f11018a == null) {
            boolean z = true;
            if (ContextUtils.getApplicationContext().getResources().getConfiguration().getLayoutDirection() != 1) {
                z = false;
            }
            f11018a = Boolean.valueOf(z);
        }
        return f11018a.booleanValue();
    }
}
