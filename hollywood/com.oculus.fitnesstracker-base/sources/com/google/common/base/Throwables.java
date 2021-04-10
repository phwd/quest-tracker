package com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Throwables {
    private static final Method getStackTraceDepthMethod;
    private static final Method getStackTraceElementMethod;
    private static final Object jla;

    public static void throwIfUnchecked(Throwable th) {
        Preconditions.checkNotNull(th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    @CanIgnoreReturnValue
    @Deprecated
    public static RuntimeException propagate(Throwable th) {
        throwIfUnchecked(th);
        throw new RuntimeException(th);
    }

    static {
        Method method;
        Object jla2 = getJLA();
        jla = jla2;
        Method method2 = null;
        if (jla2 == null) {
            method = null;
        } else {
            method = getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
        }
        getStackTraceElementMethod = method;
        if (jla != null) {
            method2 = getSizeMethod();
        }
        getStackTraceDepthMethod = method2;
    }

    private static Object getJLA() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method getSizeMethod() {
        try {
            Method jlaMethod = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (jlaMethod == null) {
                return null;
            }
            jlaMethod.invoke(getJLA(), new Throwable());
            return jlaMethod;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    private static Method getJlaMethod(String str, Class<?>... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }
}
