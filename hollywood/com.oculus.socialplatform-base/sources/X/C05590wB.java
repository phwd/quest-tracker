package X;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(24)
@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0wB  reason: invalid class name and case insensitive filesystem */
public final class C05590wB extends AnonymousClass05G {
    public static final Class<?> A00;
    public static final Constructor<?> A01;
    public static final Method A02;
    public static final Method A03;

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: java.lang.Class<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r7v1, resolved type: java.lang.Class<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.reflect.Method */
    /* JADX WARN: Multi-variable type inference failed */
    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls2.getConstructor(new Class[0]);
            Class cls3 = Integer.TYPE;
            Method method3 = cls2.getMethod("addFontWeightStyle", ByteBuffer.class, cls3, List.class, cls3, Boolean.TYPE);
            constructor = constructor2;
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls2, 1).getClass());
            method = method3;
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = constructor;
            method2 = constructor;
            method = constructor;
        }
        A01 = constructor;
        A00 = cls;
        A02 = method;
        A03 = method2;
    }

    @Override // X.AnonymousClass05G
    @Nullable
    public final Typeface A05(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull AnonymousClass06C[] r13, int i) {
        Object obj;
        Typeface typeface;
        boolean z;
        try {
            obj = A01.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj != null) {
            C000502v r8 = new C000502v();
            int length = r13.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    AnonymousClass06C r5 = r13[i2];
                    Uri uri = r5.A03;
                    ByteBuffer byteBuffer = (ByteBuffer) r8.get(uri);
                    if (byteBuffer == null) {
                        byteBuffer = AnonymousClass05H.A01(context, cancellationSignal, uri);
                        r8.put(uri, byteBuffer);
                    }
                    if (byteBuffer == null) {
                        break;
                    }
                    try {
                        z = ((Boolean) A02.invoke(obj, byteBuffer, Integer.valueOf(r5.A01), null, Integer.valueOf(r5.A02), Boolean.valueOf(r5.A04))).booleanValue();
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                        z = false;
                    }
                    if (!z) {
                        break;
                    }
                    i2++;
                } else {
                    try {
                        Object newInstance = Array.newInstance(A00, 1);
                        Array.set(newInstance, 0, obj);
                        typeface = (Typeface) A03.invoke(null, newInstance);
                    } catch (IllegalAccessException | InvocationTargetException unused3) {
                        typeface = null;
                    }
                    if (typeface != null) {
                        return Typeface.create(typeface, i);
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0099 A[SYNTHETIC] */
    @Override // X.AnonymousClass05G
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Typeface A06(android.content.Context r16, X.C05630wH r17, android.content.res.Resources r18, int r19) {
        /*
        // Method dump skipped, instructions count: 154
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05590wB.A06(android.content.Context, X.0wH, android.content.res.Resources, int):android.graphics.Typeface");
    }
}
