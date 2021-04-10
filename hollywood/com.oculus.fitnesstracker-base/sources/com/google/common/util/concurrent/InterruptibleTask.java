package com.google.common.util.concurrent;

import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
@ReflectionSupport(ReflectionSupport.Level.FULL)
public abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    static final Runnable DONE = new DoNothingRunnable((byte) 0);
    static final Runnable INTERRUPTING = new DoNothingRunnable((byte) 0);

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptibly(T t, Throwable th);

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String toPendingString();

    static final class DoNothingRunnable implements Runnable {
        public final void run() {
        }

        private DoNothingRunnable() {
        }

        /* synthetic */ DoNothingRunnable(byte b) {
            this();
        }
    }

    InterruptibleTask() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.util.concurrent.InterruptibleTask<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet(null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, DONE)) {
                        while (get() == INTERRUPTING) {
                            Thread.yield();
                        }
                    }
                    if (z) {
                        afterRanInterruptibly(null, th);
                        return;
                    }
                    return;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, DONE)) {
                while (get() == INTERRUPTING) {
                    Thread.yield();
                }
            }
            if (z) {
                afterRanInterruptibly(obj, null);
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }
}
