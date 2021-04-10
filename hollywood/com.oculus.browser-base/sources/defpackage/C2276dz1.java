package defpackage;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* renamed from: dz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2276dz1 extends AbstractC2789gz1 {
    public static Field b = null;
    public static boolean c = false;
    public static Constructor d = null;
    public static boolean e = false;
    public WindowInsets f;

    public C2276dz1() {
        if (!c) {
            try {
                b = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e2) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
            }
            c = true;
        }
        Field field = b;
        WindowInsets windowInsets = null;
        if (field != null) {
            try {
                WindowInsets windowInsets2 = (WindowInsets) field.get(null);
                if (windowInsets2 != null) {
                    windowInsets = new WindowInsets(windowInsets2);
                    this.f = windowInsets;
                }
            } catch (ReflectiveOperationException e3) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
            }
        }
        if (!e) {
            try {
                d = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e4) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
            }
            e = true;
        }
        Constructor constructor = d;
        if (constructor != null) {
            try {
                windowInsets = (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
            }
        }
        this.f = windowInsets;
    }

    @Override // defpackage.AbstractC2789gz1
    public C3985nz1 a() {
        return C3985nz1.h(this.f);
    }

    @Override // defpackage.AbstractC2789gz1
    public void c(X10 x10) {
        WindowInsets windowInsets = this.f;
        if (windowInsets != null) {
            this.f = windowInsets.replaceSystemWindowInsets(x10.b, x10.c, x10.d, x10.e);
        }
    }

    public C2276dz1(C3985nz1 nz1) {
        this.f = nz1.g();
    }
}
