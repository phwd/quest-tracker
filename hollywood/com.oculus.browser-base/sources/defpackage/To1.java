package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: To1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class To1 {

    /* renamed from: a  reason: collision with root package name */
    public ConcurrentHashMap f8985a = new ConcurrentHashMap();

    public static Object e(Object[] objArr, int i, So1 so1) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        Object obj = null;
        int i3 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int abs = (Math.abs(so1.a(obj2) - i2) * 2) + (so1.b(obj2) == z ? 0 : 1);
            if (obj == null || i3 > abs) {
                obj = obj2;
                i3 = abs;
            }
        }
        return obj;
    }

    public abstract Typeface a(Context context, FR fr, Resources resources, int i);

    public abstract Typeface b(Context context, CancellationSignal cancellationSignal, PR[] prArr, int i);

    public Typeface c(Context context, InputStream inputStream) {
        File c = Uo1.c(context);
        if (c == null) {
            return null;
        }
        try {
            if (!Uo1.b(c, inputStream)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(c.getPath());
            c.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            c.delete();
        }
    }

    public Typeface d(Context context, Resources resources, int i, String str, int i2) {
        File c = Uo1.c(context);
        if (c == null) {
            return null;
        }
        try {
            if (!Uo1.a(c, resources, i)) {
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(c.getPath());
            c.delete();
            return createFromFile;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            c.delete();
        }
    }
}
