package X;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;

/* renamed from: X.1CS  reason: invalid class name */
public final class AnonymousClass1CS {
    public static final int[] A01 = {16843375};
    public static final String[] A02 = {"android.widget.", "android.view.", "android.webkit."};
    public static final C000502v<String, Constructor<? extends View>> A03 = new C000502v<>();
    public static final Class<?>[] A04 = {Context.class, AttributeSet.class};
    public final Object[] A00 = new Object[2];

    public static View A00(AnonymousClass1CS r4, Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        String str3;
        C000502v<String, Constructor<? extends View>> r3 = A03;
        Constructor<? extends View> constructor = r3.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = AnonymousClass006.A07(str2, str);
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(A04);
            r3.put(str, constructor);
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(r4.A00);
    }
}
