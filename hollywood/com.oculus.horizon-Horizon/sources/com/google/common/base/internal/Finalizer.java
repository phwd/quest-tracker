package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Finalizer implements Runnable {
    @NullableDecl
    public static final Constructor<Thread> bigThreadConstructor;
    @NullableDecl
    public static final Field inheritableThreadLocals;
    public static final Logger logger = Logger.getLogger(Finalizer.class.getName());
    public final WeakReference<Class<?>> finalizableReferenceClassReference;
    public final PhantomReference<Object> frqReference;
    public final ReferenceQueue<Object> queue;

    static {
        Field field;
        Constructor<Thread> bigThreadConstructor2 = getBigThreadConstructor();
        bigThreadConstructor = bigThreadConstructor2;
        if (bigThreadConstructor2 == null) {
            field = getInheritableThreadLocalsField();
        } else {
            field = null;
        }
        inheritableThreadLocals = field;
    }

    @NullableDecl
    public static Constructor<Thread> getBigThreadConstructor() {
        try {
            return Thread.class.getConstructor(ThreadGroup.class, Runnable.class, String.class, Long.TYPE, Boolean.TYPE);
        } catch (Throwable unused) {
            return null;
        }
    }

    @NullableDecl
    private Method getFinalizeReferentMethod() {
        Class<?> cls = this.finalizableReferenceClassReference.get();
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod("finalizeReferent", new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    @NullableDecl
    public static Field getInheritableThreadLocalsField() {
        try {
            Field declaredField = Thread.class.getDeclaredField("inheritableThreadLocals");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            logger.log(Level.INFO, "Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:0|1) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:2:0x000a, LOOP_START, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r1 = this;
        L_0x0000:
            java.lang.ref.ReferenceQueue<java.lang.Object> r0 = r1.queue     // Catch:{ InterruptedException -> 0x0000 }
            java.lang.ref.Reference r0 = r0.remove()     // Catch:{ InterruptedException -> 0x0000 }
            boolean r0 = r1.cleanUp(r0)     // Catch:{ InterruptedException -> 0x0000 }
            if (r0 != 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.internal.Finalizer.run():void");
    }

    public Finalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
        this.queue = referenceQueue;
        this.finalizableReferenceClassReference = new WeakReference<>(cls);
        this.frqReference = phantomReference;
    }

    private boolean cleanUp(Reference<?> reference) {
        Method finalizeReferentMethod = getFinalizeReferentMethod();
        if (finalizeReferentMethod != null) {
            do {
                reference.clear();
                if (reference != this.frqReference) {
                    try {
                        finalizeReferentMethod.invoke(reference, new Object[0]);
                    } catch (Throwable th) {
                        logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
                    }
                    reference = this.queue.poll();
                }
            } while (reference != null);
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057 A[Catch:{ all -> 0x005b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void startFinalizer(java.lang.Class<?> r8, java.lang.ref.ReferenceQueue<java.lang.Object> r9, java.lang.ref.PhantomReference<java.lang.Object> r10) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.internal.Finalizer.startFinalizer(java.lang.Class, java.lang.ref.ReferenceQueue, java.lang.ref.PhantomReference):void");
    }
}
