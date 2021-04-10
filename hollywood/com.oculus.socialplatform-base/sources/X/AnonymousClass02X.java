package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.02X  reason: invalid class name */
public class AnonymousClass02X implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ AnonymousClass0wm A01;

    public AnonymousClass02X(AnonymousClass0wm r3) {
        this.A01 = r3;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.A00.getAndIncrement())));
        return thread;
    }
}
