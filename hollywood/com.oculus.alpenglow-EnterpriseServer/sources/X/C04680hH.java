package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.0hH  reason: invalid class name and case insensitive filesystem */
public final class C04680hH<T> {
    public final String A00;
    public final Class<?> A01;
    public final Class[] A02;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;)Ljava/lang/reflect/Method; */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if ((r2.getModifiers() & 1) == 0) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Method A00(X.C04680hH r4, java.lang.Class r5) {
        /*
            java.lang.String r1 = r4.A00
            r3 = 0
            if (r1 == 0) goto L_0x0024
            java.lang.Class[] r0 = r4.A02
            java.lang.reflect.Method r2 = r5.getMethod(r1, r0)     // Catch:{ NoSuchMethodException -> 0x0013 }
            int r0 = r2.getModifiers()     // Catch:{ NoSuchMethodException -> 0x0014 }
            r0 = r0 & 1
            if (r0 != 0) goto L_0x0014
        L_0x0013:
            r2 = r3
        L_0x0014:
            if (r2 == 0) goto L_0x0025
            java.lang.Class<?> r1 = r4.A01
            if (r1 == 0) goto L_0x0025
            java.lang.Class r0 = r2.getReturnType()
            boolean r0 = r1.isAssignableFrom(r0)
            if (r0 != 0) goto L_0x0025
        L_0x0024:
            return r3
        L_0x0025:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04680hH.A00(X.0hH, java.lang.Class):java.lang.reflect.Method");
    }

    public C04680hH(Class<?> cls, String str, Class... clsArr) {
        this.A01 = cls;
        this.A00 = str;
        this.A02 = clsArr;
    }

    public final Object A01(T t, Object... objArr) {
        try {
            Method A002 = A00(this, t.getClass());
            if (A002 != null) {
                try {
                    return A002.invoke(t, objArr);
                } catch (IllegalAccessException e) {
                    AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + A002);
                    assertionError.initCause(e);
                    throw assertionError;
                }
            } else {
                throw new AssertionError("Method " + this.A00 + " not supported for object " + ((Object) t));
            }
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw targetException;
            }
            AssertionError assertionError2 = new AssertionError("Unexpected exception");
            assertionError2.initCause(targetException);
            throw assertionError2;
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (TT;[Ljava/lang/Object;)Ljava/lang/Object; */
    public final void A02(Object obj, Object... objArr) {
        try {
            Method A002 = A00(this, obj.getClass());
            if (A002 != null) {
                try {
                    A002.invoke(obj, objArr);
                } catch (IllegalAccessException unused) {
                }
            }
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw targetException;
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }
}
