package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: Oo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Oo1 extends Mo1 {
    public final Class g;
    public final Constructor h;
    public final Method i;
    public final Method j;
    public final Method k;
    public final Method l;
    public final Method m;

    public Oo1() {
        Method method;
        Method method2;
        Method method3;
        Constructor<?> constructor;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(new Class[0]);
            method3 = n(cls2);
            method2 = o(cls2);
            method = cls2.getMethod("freeze", new Class[0]);
            method4 = cls2.getMethod("abortCreation", new Class[0]);
            method5 = p(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            StringBuilder i2 = AbstractC2531fV.i("Unable to collect necessary methods for class ");
            i2.append(e.getClass().getName());
            Log.e("TypefaceCompatApi26Impl", i2.toString(), e);
            method5 = null;
            method4 = null;
            constructor = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.g = cls;
        this.h = constructor;
        this.i = method3;
        this.j = method2;
        this.k = method;
        this.l = method4;
        this.m = method5;
    }

    private Object m() {
        try {
            return this.h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // defpackage.To1, defpackage.Mo1
    public Typeface a(Context context, FR fr, Resources resources, int i2) {
        if (!l()) {
            return super.a(context, fr, resources, i2);
        }
        Object m2 = m();
        if (m2 == null) {
            return null;
        }
        GR[] grArr = fr.f8016a;
        for (GR gr : grArr) {
            if (!i(context, m2, gr.f8090a, gr.e, gr.b, gr.c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(gr.d))) {
                h(m2);
                return null;
            }
        }
        if (!k(m2)) {
            return null;
        }
        return j(m2);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:67:0x00cf */
    @Override // defpackage.To1, defpackage.Mo1
    public Typeface b(Context context, CancellationSignal cancellationSignal, PR[] prArr, int i2) {
        Typeface j2;
        boolean z;
        if (prArr.length < 1) {
            return null;
        }
        if (!l()) {
            PR pr = (PR) To1.e(prArr, i2, new Ro1(this));
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(pr.f8691a, "r", cancellationSignal);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(pr.c).setItalic(pr.d).build();
                    openFileDescriptor.close();
                    return build;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } catch (IOException unused) {
                return null;
            }
        } else {
            C4595rb0 rb0 = RR.f8830a;
            HashMap hashMap = new HashMap();
            for (PR pr2 : prArr) {
                if (pr2.e == 0) {
                    Uri uri = pr2.f8691a;
                    if (!hashMap.containsKey(uri)) {
                        hashMap.put(uri, Uo1.d(context, cancellationSignal, uri));
                    }
                }
            }
            Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
            Object m2 = m();
            if (m2 == null) {
                return null;
            }
            int length = prArr.length;
            int i3 = 0;
            boolean z2 = false;
            while (i3 < length) {
                PR pr3 = prArr[i3];
                ByteBuffer byteBuffer = (ByteBuffer) unmodifiableMap.get(pr3.f8691a);
                if (byteBuffer != null) {
                    try {
                        z = ((Boolean) this.j.invoke(m2, byteBuffer, Integer.valueOf(pr3.b), null, Integer.valueOf(pr3.c), Integer.valueOf(pr3.d ? 1 : 0))).booleanValue();
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                        z = false;
                    }
                    if (!z) {
                        h(m2);
                        return null;
                    }
                    z2 = true;
                }
                i3++;
                z2 = z2;
            }
            if (!z2) {
                h(m2);
                return null;
            } else if (k(m2) && (j2 = j(m2)) != null) {
                return Typeface.create(j2, i2);
            } else {
                return null;
            }
        }
        throw th;
    }

    @Override // defpackage.To1
    public Typeface d(Context context, Resources resources, int i2, String str, int i3) {
        if (!l()) {
            return super.d(context, resources, i2, str, i3);
        }
        Object m2 = m();
        if (m2 == null) {
            return null;
        }
        if (!i(context, m2, str, 0, -1, -1, null)) {
            h(m2);
            return null;
        } else if (!k(m2)) {
            return null;
        } else {
            return j(m2);
        }
    }

    public final void h(Object obj) {
        try {
            this.l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean i(Context context, Object obj, String str, int i2, int i3, int i4, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.i.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface j(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.g, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.m.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean k(Object obj) {
        try {
            return ((Boolean) this.k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean l() {
        if (this.i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.i != null;
    }

    public Method n(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public Method o(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public Method p(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
