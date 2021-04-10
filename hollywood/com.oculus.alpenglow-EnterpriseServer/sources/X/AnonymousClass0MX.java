package X;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0MX  reason: invalid class name */
public class AnonymousClass0MX extends C03880dR {
    public final Constructor<?> A00;
    public final Method A01;
    public final Method A02;
    public final Method A03;
    public final Method A04;
    public final Class<?> A05;
    public final Method A06;

    private boolean A00(Context context, Object obj, String str, int i, int i2, int i3, @Nullable FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.A02.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    @Override // X.AnonymousClass08Y
    @Nullable
    public final Typeface A02(Context context, Resources resources, int i, String str, int i2) {
        Object obj;
        boolean z;
        Method method = this.A02;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        boolean z2 = false;
        if (method != null) {
            z2 = true;
        }
        if (!z2) {
            return super.A02(context, resources, i, str, i2);
        }
        try {
            obj = this.A00.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj != null) {
            if (!A00(context, obj, str, 0, -1, -1, null)) {
                try {
                    this.A01.invoke(obj, new Object[0]);
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
                return null;
            }
            try {
                z = ((Boolean) this.A04.invoke(obj, new Object[0])).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused3) {
                z = false;
            }
            if (z) {
                return A07(obj);
            }
        }
        return null;
    }

    @Override // X.C03880dR, X.AnonymousClass08Y
    @Nullable
    public final Typeface A05(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull AnonymousClass09U[] r13, int i) {
        Object obj;
        Object obj2;
        boolean z;
        Typeface A07;
        boolean z2;
        int length = r13.length;
        if (length >= 1) {
            Method method = this.A02;
            if (method == null) {
                Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
            }
            boolean z3 = false;
            if (method != null) {
                z3 = true;
            }
            if (!z3) {
                AnonymousClass09U A042 = A04(r13, i);
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(A042.A03, "r", cancellationSignal);
                if (openFileDescriptor != null) {
                    try {
                        Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(A042.A02).setItalic(A042.A04).build();
                        openFileDescriptor.close();
                        return build;
                    } catch (Throwable unused) {
                    }
                }
            } else {
                HashMap hashMap = new HashMap();
                for (AnonymousClass09U r1 : r13) {
                    if (r1.A00 == 0) {
                        Uri uri = r1.A03;
                        if (!hashMap.containsKey(uri)) {
                            hashMap.put(uri, AnonymousClass08Z.A01(context, cancellationSignal, uri));
                        }
                    }
                }
                Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
                try {
                    obj2 = this.A00.newInstance(new Object[0]);
                    obj = obj2;
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
                    obj2 = null;
                    obj = null;
                }
                if (obj2 != null) {
                    boolean z4 = false;
                    for (AnonymousClass09U r5 : r13) {
                        Object obj3 = unmodifiableMap.get(r5.A03);
                        if (obj3 != null) {
                            try {
                                z2 = ((Boolean) this.A03.invoke(obj, obj3, Integer.valueOf(r5.A01), null, Integer.valueOf(r5.A02), Integer.valueOf(r5.A04 ? 1 : 0))).booleanValue();
                            } catch (IllegalAccessException | InvocationTargetException unused3) {
                                z2 = false;
                            }
                            if (!z2) {
                                try {
                                    this.A01.invoke(obj, new Object[0]);
                                    return null;
                                } catch (IOException unused4) {
                                }
                            } else {
                                z4 = true;
                            }
                        }
                    }
                    if (!z4) {
                        this.A01.invoke(obj, new Object[0]);
                        return null;
                    }
                    try {
                        z = ((Boolean) this.A04.invoke(obj, new Object[0])).booleanValue();
                    } catch (IllegalAccessException | InvocationTargetException unused5) {
                        z = false;
                    }
                    if (z && (A07 = A07(obj)) != null) {
                        return Typeface.create(A07, i);
                    }
                }
            }
        }
        return null;
        throw th;
    }

    @Override // X.C03880dR, X.AnonymousClass08Y
    @Nullable
    public final Typeface A06(Context context, C03900dT r15, Resources resources, int i) {
        Object obj;
        boolean z;
        Method method = this.A02;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        boolean z2 = false;
        if (method != null) {
            z2 = true;
        }
        if (!z2) {
            return super.A06(context, r15, resources, i);
        }
        try {
            obj = this.A00.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj != null) {
            AnonymousClass084[] r3 = r15.A00;
            for (AnonymousClass084 r0 : r3) {
                if (!A00(context, obj, r0.A05, r0.A01, r0.A02, r0.A04 ? 1 : 0, FontVariationAxis.fromFontVariationSettings(r0.A03))) {
                    try {
                        this.A01.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                    }
                    return null;
                }
            }
            try {
                z = ((Boolean) this.A04.invoke(obj, new Object[0])).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused3) {
                z = false;
            }
            if (z) {
                return A07(obj);
            }
        }
        return null;
    }

    @Nullable
    public Typeface A07(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.A05, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.A06.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method A08(Class<?> cls) throws NoSuchMethodException {
        Class<?> cls2 = Array.newInstance(cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r6v1, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r7v1, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: java.lang.reflect.Method */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.reflect.Method */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0MX() {
        Method method;
        Method method2;
        Constructor<?> constructor;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(new Class[0]);
            Class cls3 = Integer.TYPE;
            Method method6 = cls2.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls3, Boolean.TYPE, cls3, cls3, cls3, FontVariationAxis[].class);
            Method method7 = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls3, FontVariationAxis[].class, cls3, cls3);
            Method method8 = cls2.getMethod("freeze", new Class[0]);
            Method method9 = cls2.getMethod("abortCreation", new Class[0]);
            method5 = A08(cls2);
            cls = cls2;
            method4 = method9;
            method3 = method8;
            method2 = method6;
            method = method7;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", AnonymousClass006.A05("Unable to collect necessary methods for class ", e.getClass().getName()), e);
            method5 = cls;
            constructor = cls;
            method2 = cls;
            method = cls;
            method3 = cls;
            method4 = cls;
        }
        this.A05 = cls;
        this.A00 = constructor;
        this.A02 = method2;
        this.A03 = method;
        this.A04 = method3;
        this.A01 = method4;
        this.A06 = method5;
    }
}
