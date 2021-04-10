package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.os.UserManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ContextUtils;

/* renamed from: j7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3153j7 {
    public static int a(Context context, String str, int i, int i2) {
        try {
            return context.checkPermission(str, i, i2);
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public static byte[] b(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Drawable c(Resources resources, int i) {
        return d(resources, i, 0);
    }

    public static Drawable d(Resources resources, int i, int i2) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        if (i2 == 0) {
            try {
                return resources.getDrawable(i, null);
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        } else {
            Drawable drawableForDensity = resources.getDrawableForDensity(i, i2, null);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return drawableForDensity;
        }
    }

    public static List e(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            return C2983i7.a(activity);
        }
        return new ArrayList();
    }

    public static boolean f() {
        return ((UserManager) ContextUtils.getApplicationContext().getSystemService("user")).isDemoUser();
    }

    public static void g(Window window, int i) {
        if (Build.VERSION.SDK_INT < 26 && i == -16777216 && window.getNavigationBarColor() == -16777216) {
            window.clearFlags(Integer.MIN_VALUE);
        } else {
            window.addFlags(Integer.MIN_VALUE);
        }
        window.setStatusBarColor(i);
    }

    public static void h(View view, boolean z) {
        int systemUiVisibility = view.getSystemUiVisibility();
        view.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & -8193);
    }

    public static void i(TextView textView, int i) {
        textView.setTextAppearance(textView.getContext(), i);
    }
}
