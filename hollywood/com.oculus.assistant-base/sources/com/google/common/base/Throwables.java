package com.google.common.base;

import java.io.IOException;
import java.lang.reflect.Method;

public final class Throwables {
    public static final Method getStackTraceDepthMethod;
    public static final Method getStackTraceElementMethod;
    public static final Object jla;

    public static Object getJLA() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static RuntimeException propagate(Throwable th) {
        if (th instanceof RuntimeException) {
            throw th;
        } else if (!(th instanceof Error)) {
            throw new RuntimeException(th);
        } else {
            throw th;
        }
    }

    public static void propagateIfPossible(Throwable th, Class cls) {
        if (IOException.class.isInstance(th)) {
            throw ((Throwable) IOException.class.cast(th));
        } else if (th instanceof RuntimeException) {
            throw th;
        } else if (th instanceof Error) {
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ExcHandler: IllegalAccessException | UnsupportedOperationException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:14:0x0044] */
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
            if (r0 == 0) goto L_0x0058
            goto L_0x002e
        L_0x0011:
            r0 = 2
            java.lang.Class[] r3 = new java.lang.Class[r0]
            java.lang.Class<java.lang.Throwable> r1 = java.lang.Throwable.class
            r0 = 0
            r3[r0] = r1
            java.lang.Class r1 = java.lang.Integer.TYPE
            r0 = 1
            r3[r0] = r1
            java.lang.String r2 = "getStackTraceElement"
            java.lang.String r1 = "sun.misc.JavaLangAccess"
            r0 = 0
            java.lang.Class r0 = java.lang.Class.forName(r1, r0, r4)     // Catch:{ ThreadDeath -> 0x005b, all -> 0x002c }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ ThreadDeath -> 0x005b, all -> 0x002c }
            goto L_0x000a
        L_0x002c:
            r0 = r4
            goto L_0x000a
        L_0x002e:
            java.lang.String r2 = "getStackTraceDepth"
            r6 = 1
            java.lang.Class[] r1 = new java.lang.Class[r6]
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            r5 = 0
            r1[r5] = r0
            java.lang.String r0 = "sun.misc.JavaLangAccess"
            java.lang.Class r0 = java.lang.Class.forName(r0, r5, r4)     // Catch:{ ThreadDeath -> 0x0055 }
            java.lang.reflect.Method r3 = r0.getMethod(r2, r1)     // Catch:{ ThreadDeath -> 0x0055 }
            if (r3 == 0) goto L_0x0058
            java.lang.Object r2 = getJLA()     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            java.lang.Object[] r1 = new java.lang.Object[r6]     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            java.lang.Throwable r0 = new java.lang.Throwable     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            r0.<init>()     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            r1[r5] = r0     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            r3.invoke(r2, r1)     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
            goto L_0x0057
        L_0x0055:
            r0 = move-exception
            throw r0     // Catch:{ IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058, IllegalAccessException | UnsupportedOperationException | InvocationTargetException -> 0x0058 }
        L_0x0057:
            r4 = r3
        L_0x0058:
            com.google.common.base.Throwables.getStackTraceDepthMethod = r4
            return
        L_0x005b:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Throwables.<clinit>():void");
    }
}
