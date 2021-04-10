package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class Throwables {
    @NullableDecl
    @GwtIncompatible
    public static final Method getStackTraceDepthMethod;
    @NullableDecl
    @GwtIncompatible
    public static final Method getStackTraceElementMethod;
    @NullableDecl
    @GwtIncompatible
    public static final Object jla;

    @NullableDecl
    @GwtIncompatible
    public static Object getJLA() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @GwtIncompatible
    @Deprecated
    public static <X extends Throwable> void propagateIfInstanceOf(@NullableDecl Throwable th, Class<X> cls) throws Throwable {
        if (th != null && cls.isInstance(th)) {
            throw cls.cast(th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ExcHandler: IllegalAccessException | UnsupportedOperationException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:14:0x003e] */
    static {
        /*
            java.lang.Object r0 = getJLA()
            com.google.common.base.Throwables.jla = r0
            r4 = 0
            if (r0 != 0) goto L_0x0011
            r0 = r4
        L_0x000a:
            com.google.common.base.Throwables.getStackTraceElementMethod = r0
            java.lang.Object r0 = com.google.common.base.Throwables.jla
            if (r0 == 0) goto L_0x0052
            goto L_0x0029
        L_0x0011:
            java.lang.Class<java.lang.Throwable> r1 = java.lang.Throwable.class
            java.lang.Class r0 = java.lang.Integer.TYPE
            java.lang.Class[] r3 = new java.lang.Class[]{r1, r0}
            java.lang.String r2 = "getStackTraceElement"
            java.lang.String r1 = "sun.misc.JavaLangAccess"
            r0 = 0
            java.lang.Class r0 = java.lang.Class.forName(r1, r0, r4)     // Catch:{ ThreadDeath -> 0x0055, all -> 0x0027 }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ ThreadDeath -> 0x0055, all -> 0x0027 }
            goto L_0x000a
        L_0x0027:
            r0 = r4
            goto L_0x000a
        L_0x0029:
            java.lang.String r3 = "getStackTraceDepth"
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            r2 = 0
            java.lang.Class[] r1 = new java.lang.Class[]{r0}
            java.lang.String r0 = "sun.misc.JavaLangAccess"
            java.lang.Class r0 = java.lang.Class.forName(r0, r2, r4)     // Catch:{ ThreadDeath -> 0x004f }
            java.lang.reflect.Method r2 = r0.getMethod(r3, r1)     // Catch:{ ThreadDeath -> 0x004f }
            if (r2 == 0) goto L_0x0052
            java.lang.Object r1 = getJLA()     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
            r0.<init>()     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
            r2.invoke(r1, r0)     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
            goto L_0x0051
        L_0x004f:
            r0 = move-exception
            throw r0     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0052 }
        L_0x0051:
            r4 = r2
        L_0x0052:
            com.google.common.base.Throwables.getStackTraceDepthMethod = r4
            return
        L_0x0055:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Throwables.<clinit>():void");
    }
}
