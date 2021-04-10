package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.89  reason: invalid class name */
public final class AnonymousClass89 {
    public static final Method A00;
    public static final Method A01;
    public static final Method A02;
    public static final Method A03;
    public static final Method A04;
    public static volatile boolean A05 = true;

    public static Object A01(Method method, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(null, objArr);
            } catch (IllegalAccessException unused) {
                A05 = false;
                return null;
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();
                if (targetException instanceof RuntimeException) {
                    throw targetException;
                } else if (targetException instanceof Error) {
                    throw targetException;
                }
            }
        }
        return null;
    }

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            AnonymousClass88 r6 = new AnonymousClass88(cls.getMethod("addChangeCallback", Runnable.class), cls.getMethod("get", String.class), cls.getMethod("getBoolean", String.class, Boolean.TYPE), cls.getMethod("getLong", String.class, Long.TYPE), cls.getMethod("set", String.class, String.class));
            A00 = r6.A00;
            A02 = r6.A02;
            A01 = r6.A01;
            A03 = r6.A03;
            A04 = r6.A04;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public static long A00(String str) {
        if (A05) {
            Number number = (Number) A01(A03, str, 0L);
            if (number != null) {
                return number.longValue();
            }
        }
        return 0;
    }

    public static String A02(String str) {
        if (A05) {
            String str2 = (String) A01(A02, str);
            if (str2 != null) {
                return str2;
            }
        }
        return OacrConstants.AUTO_SPEECH_DOMAIN;
    }
}
