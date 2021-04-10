package com.android.okhttp.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* access modifiers changed from: package-private */
public class OptionalMethod {
    private final String methodName;
    private final Class[] methodParams;
    private final Class returnType;

    public OptionalMethod(Class cls, String str, Class... clsArr) {
        this.returnType = cls;
        this.methodName = str;
        this.methodParams = clsArr;
    }

    public boolean isSupported(Object obj) {
        return getMethod(obj.getClass()) != null;
    }

    public Object invokeOptional(Object obj, Object... objArr) {
        Method method = getMethod(obj.getClass());
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    public Object invokeOptionalWithoutCheckedException(Object obj, Object... objArr) {
        try {
            return invokeOptional(obj, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError((Object) "Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    public Object invoke(Object obj, Object... objArr) {
        Method method = getMethod(obj.getClass());
        if (method != null) {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException e) {
                AssertionError assertionError = new AssertionError((Object) ("Unexpectedly could not call: " + method));
                assertionError.initCause(e);
                throw assertionError;
            }
        } else {
            throw new AssertionError((Object) ("Method " + this.methodName + " not supported for object " + obj));
        }
    }

    public Object invokeWithoutCheckedException(Object obj, Object... objArr) {
        try {
            return invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError((Object) "Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    private Method getMethod(Class cls) {
        Class cls2;
        String str = this.methodName;
        if (str == null) {
            return null;
        }
        Method publicMethod = getPublicMethod(cls, str, this.methodParams);
        if (publicMethod == null || (cls2 = this.returnType) == null || cls2.isAssignableFrom(publicMethod.getReturnType())) {
            return publicMethod;
        }
        return null;
    }

    private static Method getPublicMethod(Class cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) != 0) {
                    return method;
                }
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
        }
        return null;
    }
}
