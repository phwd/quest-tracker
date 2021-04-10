package defpackage;

import android.util.Log;
import java.util.Locale;

/* renamed from: Ua0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1220Ua0 {
    public static void a(String str, String str2, Object... objArr) {
        Throwable c = c(objArr);
        String b = b(str2, c, objArr);
        if (c != null) {
            Log.e(e(str), b, c);
        } else {
            Log.e(e(str), b);
        }
    }

    public static String b(String str, Throwable th, Object... objArr) {
        if (objArr != null) {
            return ((th != null || objArr.length <= 0) && objArr.length <= 1) ? str : String.format(Locale.US, str, objArr);
        }
        return str;
    }

    public static Throwable c(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (!(obj instanceof Throwable)) {
            return null;
        }
        return (Throwable) obj;
    }

    public static void d(String str, String str2, Object... objArr) {
        Throwable c = c(objArr);
        String b = b(str2, c, objArr);
        if (c != null) {
            Log.i(e(str), b, c);
        } else {
            Log.i(e(str), b);
        }
    }

    public static String e(String str) {
        if (str.startsWith("cr_")) {
            return str;
        }
        int i = 0;
        if (str.startsWith("cr.")) {
            i = 3;
        }
        StringBuilder i2 = AbstractC2531fV.i("cr_");
        i2.append(str.substring(i, str.length()));
        return i2.toString();
    }

    public static void f(String str, String str2, Object... objArr) {
        Throwable c = c(objArr);
        String b = b(str2, c, objArr);
        if (c != null) {
            Log.w(e(str), b, c);
        } else {
            Log.w(e(str), b);
        }
    }

    public static void g(String str, String str2, Object... objArr) {
        Throwable c = c(objArr);
        String b = b(str2, c, objArr);
        if (c != null) {
            Log.wtf(e(str), b, c);
        } else {
            Log.wtf(e(str), b);
        }
    }
}
