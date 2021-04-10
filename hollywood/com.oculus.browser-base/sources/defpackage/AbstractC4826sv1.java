package defpackage;

import android.graphics.Rect;
import android.view.View;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: sv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4826sv1 {

    /* renamed from: a  reason: collision with root package name */
    public static Method f11309a;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            f11309a = declaredMethod;
            if (!declaredMethod.isAccessible()) {
                f11309a.setAccessible(true);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    public static boolean a(View view) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return view.getLayoutDirection() == 1;
    }
}
