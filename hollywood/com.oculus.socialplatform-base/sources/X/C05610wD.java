package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0wD  reason: invalid class name and case insensitive filesystem */
public class C05610wD extends AnonymousClass05G {
    public static Class<?> A00;
    public static Constructor<?> A01;
    public static Method A02;
    public static Method A03;
    public static boolean A04;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a A[SYNTHETIC, Splitter:B:14:0x003a] */
    @Override // X.AnonymousClass05G
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface A05(android.content.Context r6, android.os.CancellationSignal r7, @androidx.annotation.NonNull X.AnonymousClass06C[] r8, int r9) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05610wD.A05(android.content.Context, android.os.CancellationSignal, X.06C[], int):android.graphics.Typeface");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Class<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r5v1, resolved type: java.lang.Class<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.reflect.Method */
    /* JADX WARN: Multi-variable type inference failed */
    public static void A01() {
        Class<?> cls;
        Method method;
        Method method2;
        if (!A04) {
            A04 = true;
            Constructor<?> constructor = null;
            try {
                Class<?> cls2 = Class.forName("android.graphics.FontFamily");
                Constructor<?> constructor2 = cls2.getConstructor(new Class[0]);
                Method method3 = cls2.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
                constructor = constructor2;
                method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls2, 1).getClass());
                method = method3;
                cls = cls2;
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
                method2 = constructor;
                cls = constructor;
                method = constructor;
            }
            A01 = constructor;
            A00 = cls;
            A02 = method;
            A03 = method2;
        }
    }

    @Override // X.AnonymousClass05G
    public Typeface A06(Context context, C05630wH r12, Resources resources, int i) {
        A01();
        try {
            Object newInstance = A01.newInstance(new Object[0]);
            AnonymousClass04m[] r8 = r12.A00;
            for (AnonymousClass04m r2 : r8) {
                File A002 = AnonymousClass05H.A00(context);
                if (A002 == null) {
                    return null;
                }
                try {
                    if (AnonymousClass05H.A02(A002, resources, r2.A00)) {
                        String path = A002.getPath();
                        int i2 = r2.A02;
                        boolean z = r2.A04;
                        A01();
                        try {
                            if (((Boolean) A02.invoke(newInstance, path, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue()) {
                                A002.delete();
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    A002.delete();
                }
            }
            A01();
            try {
                Object newInstance2 = Array.newInstance(A00, 1);
                Array.set(newInstance2, 0, newInstance);
                return (Typeface) A03.invoke(null, newInstance2);
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
