package X;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;
import org.apache.commons.cli.HelpFormatter;

@SuppressLint({"NewApi"})
/* renamed from: X.05E  reason: invalid class name */
public final class AnonymousClass05E {
    public static final AnonymousClass02o<String, Typeface> A00 = new AnonymousClass02o<>(16);
    public static final AnonymousClass05G A01;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            A01 = new C05570w9();
        } else if (i >= 28) {
            A01 = new AnonymousClass0Iv();
        } else if (i >= 26) {
            A01 = new AnonymousClass0WG();
        } else {
            Method method = C05590wB.A02;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                A01 = new C05590wB();
            } else {
                A01 = new C05610wD();
            }
        }
    }

    public static String A00(Resources resources, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getResourcePackageName(i));
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(i);
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(i2);
        return sb.toString();
    }
}
