package X;

import com.oculus.config.updater.ConfigUpdaterDumperPlugin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* renamed from: X.0I1  reason: invalid class name */
public final class AnonymousClass0I1 {
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
            AnonymousClass0I0 r6 = new AnonymousClass0I0(cls.getMethod("addChangeCallback", Runnable.class), cls.getMethod("get", String.class), cls.getMethod("getBoolean", String.class, Boolean.TYPE), cls.getMethod("getLong", String.class, Long.TYPE), cls.getMethod(ConfigUpdaterDumperPlugin.COMMAND_SET, String.class, String.class));
            A00 = r6.A00;
            A02 = r6.A02;
            A01 = r6.A01;
            A03 = r6.A03;
            A04 = r6.A04;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public static long A00(String str, long j) {
        if (A05) {
            Number number = (Number) A01(A03, str, Long.valueOf(j));
            if (number != null) {
                return number.longValue();
            }
        }
        return j;
    }

    public static String A02(String str) {
        if (A05) {
            String str2 = (String) A01(A02, str);
            if (str2 != null) {
                return str2;
            }
        }
        return "";
    }

    public static String A03(String str, String str2) {
        String A022 = A02(str);
        if (A022 == null || A022.isEmpty()) {
            return str2;
        }
        return A022;
    }
}
