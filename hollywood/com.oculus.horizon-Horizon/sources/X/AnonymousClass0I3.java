package X;

import android.os.Trace;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* renamed from: X.0I3  reason: invalid class name */
public final class AnonymousClass0I3 {
    public static final long A00;
    @Nullable
    public static final Method A01;
    @Nullable
    public static final Method A02;
    public static volatile boolean A03 = true;

    static {
        try {
            Method method = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            Method method2 = Trace.class.getMethod("setAppTracingAllowed", Boolean.TYPE);
            Field field = Trace.class.getField("TRACE_TAG_APP");
            if (field.getType() == Long.TYPE) {
                AnonymousClass0I2 r1 = new AnonymousClass0I2(method, method2, field.getLong(null));
                A01 = r1.A01;
                A02 = r1.A02;
                A00 = r1.A00;
            }
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException unused) {
        }
    }

    @Nullable
    public static Object A00(Method method, Object... objArr) {
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException unused) {
            A03 = false;
            return null;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (!(targetException instanceof RuntimeException) && !(targetException instanceof Error)) {
                return null;
            }
            throw targetException;
        }
    }
}
