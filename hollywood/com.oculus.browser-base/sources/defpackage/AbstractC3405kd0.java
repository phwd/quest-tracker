package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewParent;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: kd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3405kd0 {
    public static XA a(int i) {
        if (i == 0) {
            return new JN0();
        }
        if (i != 1) {
            return new JN0();
        }
        return new C5556xC();
    }

    public static void b(View view, float f) {
        Drawable background = view.getBackground();
        if (background instanceof C3234jd0) {
            C3234jd0 jd0 = (C3234jd0) background;
            C3064id0 id0 = jd0.H;
            if (id0.o != f) {
                id0.o = f;
                jd0.s();
            }
        }
    }

    public static void c(View view, C3234jd0 jd0) {
        EK ek = jd0.H.b;
        if (ek != null && ek.f7954a) {
            float f = 0.0f;
            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                f += ((View) parent).getElevation();
            }
            C3064id0 id0 = jd0.H;
            if (id0.n != f) {
                id0.n = f;
                jd0.s();
            }
        }
    }
}
