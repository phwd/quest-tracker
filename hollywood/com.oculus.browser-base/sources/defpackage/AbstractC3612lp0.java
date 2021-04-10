package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.ContextThemeWrapper;

/* renamed from: lp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3612lp0 {
    public static int a() {
        int f = NU0.f8549a.f("ui_theme_setting", -1);
        if (f == -1) {
            return Build.VERSION.SDK_INT < 29 ? 1 : 0;
        }
        return f;
    }

    public static Context b(Context context, int i, boolean z) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        configuration.uiMode = (z ? 32 : 16) | (configuration.uiMode & -49);
        contextThemeWrapper.applyOverrideConfiguration(configuration);
        return contextThemeWrapper;
    }
}
