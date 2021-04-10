package defpackage;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;

/* renamed from: cG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1985cG {
    public static boolean a(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    public static Point b(String str, Display display) {
        String str2;
        if (display.getDisplayId() == 0) {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                str2 = (String) cls.getMethod("get", String.class).invoke(cls, str);
            } catch (Exception unused) {
                str2 = null;
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    String[] split = str2.trim().split("x", -1);
                    if (split.length == 2) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        if (parseInt > 0 && parseInt2 > 0) {
                            return new Point(parseInt, parseInt2);
                        }
                    }
                    throw new NumberFormatException();
                } catch (NumberFormatException unused2) {
                }
            }
        }
        return null;
    }
}
