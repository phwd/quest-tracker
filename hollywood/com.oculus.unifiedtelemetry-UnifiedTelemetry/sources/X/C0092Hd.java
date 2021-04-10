package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* renamed from: X.Hd  reason: case insensitive filesystem */
public final class C0092Hd {
    @Nullable
    public static final Method A00;
    @Nullable
    public static final Method A01;
    @Nullable
    public static final Method A02;
    @Nullable
    public static final Method A03;
    @Nullable
    public static final Method A04;
    public static volatile boolean A05 = true;

    @Nullable
    public static Object A01(@Nullable Method method, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException unused) {
            A05 = false;
            return null;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (!(targetException instanceof RuntimeException) && !(targetException instanceof Error)) {
                return null;
            }
            throw targetException;
        }
    }

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            C0091Hc hc = new C0091Hc(cls.getMethod("addChangeCallback", Runnable.class), cls.getMethod("get", String.class), cls.getMethod("getBoolean", String.class, Boolean.TYPE), cls.getMethod("getLong", String.class, Long.TYPE), cls.getMethod("set", String.class, String.class));
            A00 = hc.A00;
            A02 = hc.A02;
            A01 = hc.A01;
            A03 = hc.A03;
            A04 = hc.A04;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public static long A00(String str) {
        Number number;
        if (!A05 || (number = (Number) A01(A03, str, 0L)) == null) {
            return 0;
        }
        return number.longValue();
    }

    public static String A02(String str) {
        String str2;
        if (!A05 || (str2 = (String) A01(A02, str)) == null) {
            return "";
        }
        return str2;
    }
}
