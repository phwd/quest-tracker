package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: Mo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Mo1 extends To1 {
    public static Class b = null;
    public static Constructor c = null;
    public static Method d = null;
    public static Method e = null;
    public static boolean f = false;

    public static boolean f(Object obj, String str, int i, boolean z) {
        g();
        try {
            return ((Boolean) d.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void g() {
        Method method;
        Class<?> cls;
        Method method2;
        if (!f) {
            f = true;
            Constructor<?> constructor = null;
            try {
                cls = Class.forName("android.graphics.FontFamily");
                Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
                method = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
                method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
                constructor = constructor2;
            } catch (ClassNotFoundException | NoSuchMethodException e2) {
                Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
                method2 = null;
                cls = null;
                method = null;
            }
            c = constructor;
            b = cls;
            d = method;
            e = method2;
        }
    }

    @Override // defpackage.To1
    public Typeface a(Context context, FR fr, Resources resources, int i) {
        g();
        try {
            Object newInstance = c.newInstance(new Object[0]);
            GR[] grArr = fr.f8016a;
            for (GR gr : grArr) {
                File c2 = Uo1.c(context);
                if (c2 == null) {
                    return null;
                }
                try {
                    if (!Uo1.a(c2, resources, gr.f)) {
                        c2.delete();
                        return null;
                    } else if (!f(newInstance, c2.getPath(), gr.b, gr.c)) {
                        return null;
                    } else {
                        c2.delete();
                    }
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    c2.delete();
                }
            }
            g();
            try {
                Object newInstance2 = Array.newInstance(b, 1);
                Array.set(newInstance2, 0, newInstance);
                return (Typeface) e.invoke(null, newInstance2);
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055 A[SYNTHETIC, Splitter:B:18:0x0055] */
    @Override // defpackage.To1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface b(android.content.Context r4, android.os.CancellationSignal r5, defpackage.PR[] r6, int r7) {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Mo1.b(android.content.Context, android.os.CancellationSignal, PR[], int):android.graphics.Typeface");
    }
}
