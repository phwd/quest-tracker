package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

/* renamed from: n8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3840n8 {

    /* renamed from: a  reason: collision with root package name */
    public static final PorterDuff.Mode f10473a = PorterDuff.Mode.SRC_IN;
    public static C3840n8 b;
    public C4904tM0 c;

    public static synchronized C3840n8 a() {
        C3840n8 n8Var;
        synchronized (C3840n8.class) {
            if (b == null) {
                e();
            }
            n8Var = b;
        }
        return n8Var;
    }

    public static synchronized PorterDuffColorFilter c(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter g;
        synchronized (C3840n8.class) {
            g = C4904tM0.g(i, mode);
        }
        return g;
    }

    public static synchronized void e() {
        synchronized (C3840n8.class) {
            if (b == null) {
                C3840n8 n8Var = new C3840n8();
                b = n8Var;
                n8Var.c = C4904tM0.c();
                C4904tM0 tm0 = b.c;
                C3669m8 m8Var = new C3669m8();
                synchronized (tm0) {
                    tm0.j = m8Var;
                }
            }
        }
    }

    public static void f(Drawable drawable, C0392Gi1 gi1, int[] iArr) {
        PorterDuff.Mode mode = C4904tM0.f11339a;
        if (!XI.a(drawable) || drawable.mutate() == drawable) {
            boolean z = gi1.d;
            if (z || gi1.c) {
                PorterDuffColorFilter porterDuffColorFilter = null;
                ColorStateList colorStateList = z ? gi1.f8104a : null;
                PorterDuff.Mode mode2 = gi1.c ? gi1.b : C4904tM0.f11339a;
                if (!(colorStateList == null || mode2 == null)) {
                    porterDuffColorFilter = C4904tM0.g(colorStateList.getColorForState(iArr, 0), mode2);
                }
                drawable.setColorFilter(porterDuffColorFilter);
                return;
            }
            drawable.clearColorFilter();
        }
    }

    public synchronized Drawable b(Context context, int i) {
        return this.c.e(context, i);
    }

    public synchronized ColorStateList d(Context context, int i) {
        return this.c.h(context, i);
    }
}
