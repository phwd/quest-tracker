package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* renamed from: pv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4315pv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC5846yv1 f11100a;
    public static final Property b = new C3973nv1(Float.class, "translationAlpha");

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f11100a = new C5676xv1();
        } else {
            f11100a = new C5506wv1();
        }
        new C4144ov1(Rect.class, "clipBounds");
    }

    public static float a(View view) {
        return f11100a.b(view);
    }

    public static void b(View view, int i, int i2, int i3, int i4) {
        f11100a.d(view, i, i2, i3, i4);
    }
}
