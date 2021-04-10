package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* renamed from: VI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VI {
    public static void a(Drawable drawable, int i) {
        drawable.setTint(i);
    }

    public static void b(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static void c(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }

    public static Drawable d(Drawable drawable) {
        return drawable instanceof AbstractC4156oz1 ? ((AbstractC4327pz1) ((AbstractC4156oz1) drawable)).F : drawable;
    }
}
