package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class Hw {
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
    public static Object A00(@Nullable Method method, Object... objArr) {
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
            Hv hv = new Hv(cls.getMethod("addChangeCallback", Runnable.class), cls.getMethod("get", String.class), cls.getMethod("getBoolean", String.class, Boolean.TYPE), cls.getMethod("getLong", String.class, Long.TYPE), cls.getMethod("set", String.class, String.class));
            A00 = hv.A00;
            A03 = hv.A02;
            A02 = hv.A01;
            A01 = hv.A03;
            A04 = hv.A04;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public static String A01(String str) {
        String str2;
        if (!A05 || (str2 = (String) A00(A03, str)) == null) {
            return "";
        }
        return str2;
    }
}
