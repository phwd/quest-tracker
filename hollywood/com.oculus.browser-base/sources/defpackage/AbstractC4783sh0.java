package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import com.oculus.browser.R;

/* renamed from: sh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4783sh0 {
    public static Context a(Context context, int i, boolean z) {
        if (i == 0) {
            i = i(context, !z ? R.attr.f3820_resource_name_obfuscated_RES_2130968828 : R.attr.f1690_resource_name_obfuscated_RES_2130968615);
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        return i(contextThemeWrapper, R.attr.f6510_resource_name_obfuscated_RES_2130969097) != 0 ? new ContextThemeWrapper(contextThemeWrapper, g(contextThemeWrapper)) : contextThemeWrapper;
    }

    public static int b(Context context) {
        int i = i(context, R.attr.f6510_resource_name_obfuscated_RES_2130969097);
        return i == 0 ? g(context) : i;
    }

    public static int c(Context context, int i) {
        if (AbstractC1331Vv.b(-1, h(context, i, R.attr.f3160_resource_name_obfuscated_RES_2130968762)) >= 3.0d) {
            return -1;
        }
        return -570425344;
    }

    public static float d(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16842803, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }

    public static Drawable e(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (j(context)) {
            Object obj = K2.f8337a;
            drawable.setTint(context.getColor(R.color.f13770_resource_name_obfuscated_RES_2131100067));
        }
        obtainStyledAttributes.recycle();
        return drawable;
    }

    public static Drawable f(Context context, int i) {
        Object obj = K2.f8337a;
        Drawable drawable = context.getDrawable(i);
        if (j(context)) {
            drawable.setTint(context.getColor(R.color.f13770_resource_name_obfuscated_RES_2131100067));
        }
        return drawable;
    }

    public static int g(Context context) {
        return j(context) ? c(context, 0) == -570425344 ? R.style.f73440_resource_name_obfuscated_RES_2132017917 : R.style.f73450_resource_name_obfuscated_RES_2132017918 : c(context, 0) == -570425344 ? R.style.f73460_resource_name_obfuscated_RES_2132017919 : R.style.f73430_resource_name_obfuscated_RES_2132017916;
    }

    public static int h(Context context, int i, int i2) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        if (typedValue.resourceId != 0) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return typedValue.data;
    }

    public static int i(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public static boolean j(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(R.attr.f5300_resource_name_obfuscated_RES_2130968976, typedValue, true) && typedValue.data != 0;
    }

    public static void k(Context context, Dialog dialog) {
        View decorView = dialog.getWindow().getDecorView();
        int i = j(context) ? R.color.f13730_resource_name_obfuscated_RES_2131100063 : R.color.f13720_resource_name_obfuscated_RES_2131100062;
        Object obj = K2.f8337a;
        decorView.setBackgroundColor(context.getColor(i));
    }

    public static void l(Context context, ProgressBar progressBar) {
        if (progressBar.isIndeterminate()) {
            int i = j(context) ? R.color.f13690_resource_name_obfuscated_RES_2131100059 : R.color.f13680_resource_name_obfuscated_RES_2131100058;
            Object obj = K2.f8337a;
            progressBar.getIndeterminateDrawable().setColorFilter(context.getColor(i), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void m(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, View view) {
        int c = c(context, 0);
        if (Color.alpha(c) != 255) {
            c = AbstractC1331Vv.e(c, ((Integer) view.getTag()).intValue());
        }
        mediaRouteVolumeSlider.a(c, c);
    }
}
