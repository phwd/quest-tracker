package X;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.05m  reason: invalid class name and case insensitive filesystem */
public final class C005405m {
    public static Method A00;

    static {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            A00 = declaredMethod;
            if (!declaredMethod.isAccessible()) {
                A00.setAccessible(true);
            }
        } catch (NoSuchMethodException unused) {
        }
    }
}
