package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.02Q  reason: invalid class name */
public class AnonymousClass02Q implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ C07540sx A01;

    public AnonymousClass02Q(C07540sx r3) {
        this.A01 = r3;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.A00.getAndIncrement())));
        return thread;
    }
}
