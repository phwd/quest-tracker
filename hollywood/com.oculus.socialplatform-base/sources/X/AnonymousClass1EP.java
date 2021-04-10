package X;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1EP  reason: invalid class name */
public final class AnonymousClass1EP {
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
