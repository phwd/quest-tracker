package X;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

@SuppressLint({"NewApi"})
/* renamed from: X.08W  reason: invalid class name */
public final class AnonymousClass08W {
    public static final AnonymousClass066<String, Typeface> A00 = new AnonymousClass066<>(16);
    public static final AnonymousClass08Y A01;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            A01 = new C03860dP();
        } else if (i >= 28) {
            A01 = new AnonymousClass0Gy();
        } else if (i >= 26) {
            A01 = new AnonymousClass0MX();
        } else {
            Method method = C03870dQ.A02;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                A01 = new C03870dQ();
            } else {
                A01 = new C03880dR();
            }
        }
    }

    public static String A00(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
