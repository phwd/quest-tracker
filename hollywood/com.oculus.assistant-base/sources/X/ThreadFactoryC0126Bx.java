package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.Bx  reason: case insensitive filesystem */
public final class ThreadFactoryC0126Bx implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, AnonymousClass08.A04("gcm-task#", Integer.toString(this.A00.getAndIncrement())));
        thread.setPriority(4);
        return thread;
    }
}
