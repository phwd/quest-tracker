package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.05p  reason: invalid class name and case insensitive filesystem */
public class ThreadFactoryC005705p implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ C04010do A01;

    public ThreadFactoryC005705p(C04010do r3) {
        this.A01 = r3;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.A00.getAndIncrement())));
        return thread;
    }
}
