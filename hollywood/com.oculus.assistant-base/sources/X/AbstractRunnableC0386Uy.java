package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.Uy  reason: case insensitive filesystem */
public abstract class AbstractRunnableC0386Uy extends AtomicReference implements Runnable {
    public static final Runnable A00 = new RunnableC0385Ux();
    public static final Runnable A01 = new RunnableC0385Ux();
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.InterruptibleTask";

    public final void run() {
        Thread currentThread = Thread.currentThread();
        Object obj = null;
        if (compareAndSet(null, currentThread)) {
            C1194uv uvVar = (C1194uv) this;
            boolean z = !uvVar.this$0.isDone();
            if (z) {
                try {
                    obj = uvVar.callable.call();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, A00)) {
                        while (get() == A01) {
                            Thread.yield();
                        }
                    }
                    uvVar.this$0.setException(th);
                    return;
                }
            }
            if (!compareAndSet(currentThread, A00)) {
                while (get() == A01) {
                    Thread.yield();
                }
            }
            if (z) {
                uvVar.this$0.set(obj);
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == A00) {
            str = "running=[DONE]";
        } else if (runnable == A01) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = AnonymousClass08.A05("running=[RUNNING ON ", ((Thread) runnable).getName(), "]");
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return AnonymousClass08.A05(str, ", ", ((C1194uv) this).callable.toString());
    }
}
