package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: hz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2960hz1 extends C3814mz1 {
    public static boolean c = false;
    public static Method d;
    public static Class e;
    public static Class f;
    public static Field g;
    public static Field h;
    public final WindowInsets i;
    public X10 j = null;
    public C3985nz1 k;
    public X10 l;

    public AbstractC2960hz1(C3985nz1 nz1, WindowInsets windowInsets) {
        super(nz1);
        this.i = windowInsets;
    }

    public static void l(Exception exc) {
        StringBuilder i2 = AbstractC2531fV.i("Failed to get visible insets. (Reflection error). ");
        i2.append(exc.getMessage());
        Log.e("WindowInsetsCompat", i2.toString(), exc);
    }

    @Override // defpackage.C3814mz1
    public void d(View view) {
        if (Build.VERSION.SDK_INT < 30) {
            if (!c) {
                try {
                    d = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                    e = Class.forName("android.view.ViewRootImpl");
                    Class<?> cls = Class.forName("android.view.View$AttachInfo");
                    f = cls;
                    g = cls.getDeclaredField("mVisibleInsets");
                    h = e.getDeclaredField("mAttachInfo");
                    g.setAccessible(true);
                    h.setAccessible(true);
                } catch (ClassNotFoundException e2) {
                    l(e2);
                } catch (NoSuchMethodException e3) {
                    l(e3);
                } catch (NoSuchFieldException e4) {
                    l(e4);
                }
                c = true;
            }
            Method method = d;
            X10 x10 = null;
            if (!(method == null || f == null || g == null)) {
                try {
                    Object invoke = method.invoke(view, new Object[0]);
                    if (invoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    } else {
                        Rect rect = (Rect) g.get(h.get(invoke));
                        if (rect != null) {
                            x10 = X10.a(rect.left, rect.top, rect.right, rect.bottom);
                        }
                    }
                } catch (IllegalAccessException e5) {
                    l(e5);
                } catch (InvocationTargetException e6) {
                    l(e6);
                }
            }
            if (x10 == null) {
                x10 = X10.f9186a;
            }
            this.l = x10;
            return;
        }
        throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
    }

    @Override // defpackage.C3814mz1
    public final X10 g() {
        if (this.j == null) {
            this.j = X10.a(this.i.getSystemWindowInsetLeft(), this.i.getSystemWindowInsetTop(), this.i.getSystemWindowInsetRight(), this.i.getSystemWindowInsetBottom());
        }
        return this.j;
    }

    @Override // defpackage.C3814mz1
    public C3985nz1 h(int i2, int i3, int i4, int i5) {
        AbstractC2789gz1 gz1;
        C3985nz1 h2 = C3985nz1.h(this.i);
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 30) {
            gz1 = new C2618fz1(h2);
        } else if (i6 >= 29) {
            gz1 = new C2447ez1(h2);
        } else {
            gz1 = new C2276dz1(h2);
        }
        gz1.c(C3985nz1.e(g(), i2, i3, i4, i5));
        gz1.b(C3985nz1.e(f(), i2, i3, i4, i5));
        return gz1.a();
    }

    @Override // defpackage.C3814mz1
    public boolean j() {
        return this.i.isRound();
    }

    @Override // defpackage.C3814mz1
    public void k(C3985nz1 nz1) {
        this.k = nz1;
    }
}
