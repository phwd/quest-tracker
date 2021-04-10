package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;

/* renamed from: X.02z  reason: invalid class name */
public final class AnonymousClass02z {
    public static final int[] A01 = {16843375};
    public static final String[] A02 = {"android.widget.", "android.view.", "android.webkit."};
    public static final AnonymousClass06D<String, Constructor<? extends View>> A03 = new AnonymousClass06D<>();
    public static final Class<?>[] A04 = {Context.class, AttributeSet.class};
    public final Object[] A00 = new Object[2];

    public static Context A00(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass18N.A0O, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.recycle();
        if (resourceId == 0 || ((context instanceof AnonymousClass03F) && ((AnonymousClass03F) context).A00 == resourceId)) {
            return context;
        }
        return new AnonymousClass03F(context, resourceId);
    }

    public static View A01(AnonymousClass02z r4, Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        String str3;
        AnonymousClass06D<String, Constructor<? extends View>> r3 = A03;
        Constructor<? extends View> constructor = r3.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = AnonymousClass006.A05(str2, str);
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
