package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.2d  reason: invalid class name */
public class AnonymousClass2d implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ UG A01;

    public AnonymousClass2d(UG ug) {
        this.A01 = ug;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.A00.getAndIncrement())));
        return thread;
    }
}
