package defpackage;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;

/* renamed from: yv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5846yv1 {

    /* renamed from: a  reason: collision with root package name */
    public static Field f11709a;
    public static boolean b;

    public abstract void a(View view);

    public abstract float b(View view);

    public abstract void c(View view);

    public abstract void d(View view, int i, int i2, int i3, int i4);

    public abstract void e(View view, float f);

    public void f(View view, int i) {
        if (!b) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f11709a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            b = true;
        }
        Field field = f11709a;
        if (field != null) {
            try {
                f11709a.setInt(view, i | (field.getInt(view) & -13));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public abstract void g(View view, Matrix matrix);

    public abstract void h(View view, Matrix matrix);
}
