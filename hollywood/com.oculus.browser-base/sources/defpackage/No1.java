package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/* renamed from: No1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class No1 extends To1 {
    public static final Class b;
    public static final Constructor c;
    public static final Method d;
    public static final Method e;

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            Class<?> cls2 = Integer.TYPE;
            method = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method2 = null;
            method = null;
        }
        c = constructor;
        b = cls;
        d = method;
        e = method2;
    }

    public static boolean f(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface g(Object obj) {
        try {
            Object newInstance = Array.newInstance(b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // defpackage.To1
    public Typeface a(Context context, FR fr, Resources resources, int i) {
        Object obj;
        MappedByteBuffer mappedByteBuffer;
        try {
            obj = c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        GR[] grArr = fr.f8016a;
        for (GR gr : grArr) {
            int i2 = gr.f;
            File c2 = Uo1.c(context);
            if (c2 != null) {
                try {
                    if (Uo1.a(c2, resources, i2)) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(c2);
                            try {
                                FileChannel channel = fileInputStream.getChannel();
                                mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                                fileInputStream.close();
                                c2.delete();
                                if (!(mappedByteBuffer == null && f(obj, mappedByteBuffer, gr.e, gr.b, gr.c))) {
                                    return null;
                                }
                            } catch (Throwable th) {
                                AbstractC0754Mh1.f8495a.a(th, th);
                            }
                        } catch (IOException unused2) {
                            mappedByteBuffer = null;
                        }
                    }
                } finally {
                    c2.delete();
                }
            }
            mappedByteBuffer = null;
            if (mappedByteBuffer == null) {
                return null;
            }
        }
        return g(obj);
        throw th;
    }

    @Override // defpackage.To1
    public Typeface b(Context context, CancellationSignal cancellationSignal, PR[] prArr, int i) {
        Object obj;
        try {
            obj = c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        BW0 bw0 = new BW0();
        for (PR pr : prArr) {
            Uri uri = pr.f8691a;
            ByteBuffer byteBuffer = (ByteBuffer) bw0.get(uri);
            if (byteBuffer == null) {
                byteBuffer = Uo1.d(context, cancellationSignal, uri);
                bw0.put(uri, byteBuffer);
            }
            if (byteBuffer == null || !f(obj, byteBuffer, pr.b, pr.c, pr.d)) {
                return null;
            }
        }
        Typeface g = g(obj);
        if (g == null) {
            return null;
        }
        return Typeface.create(g, i);
    }
}
