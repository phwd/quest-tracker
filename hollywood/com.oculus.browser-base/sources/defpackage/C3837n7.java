package defpackage;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.autofill.AutofillManager;

/* renamed from: n7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3837n7 {
    public static void a(Activity activity) {
        AutofillManager autofillManager = (AutofillManager) activity.getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.cancel();
        }
    }

    public static Context b(Context context, String str) {
        P21 f0 = P21.f0();
        try {
            Context createContextForSplit = context.createContextForSplit(str);
            f0.close();
            return createContextForSplit;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static String[] c(ApplicationInfo applicationInfo) {
        return applicationInfo.splitNames;
    }

    public static long d(ClipDescription clipDescription) {
        return clipDescription.getTimestamp();
    }

    public static boolean e(Configuration configuration) {
        return configuration.isScreenWideColorGamut();
    }

    public static boolean f(Display display) {
        return display.isWideColorGamut();
    }

    public static void g(View view) {
        AutofillManager autofillManager = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.notifyValueChanged(view);
        }
    }

    public static void h(Window window, int i) {
        window.setColorMode(i);
    }

    public static void i(View view, boolean z) {
        view.setDefaultFocusHighlightEnabled(z);
    }
}
