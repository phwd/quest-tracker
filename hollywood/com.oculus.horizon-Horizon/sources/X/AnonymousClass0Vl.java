package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Vl  reason: invalid class name */
public class AnonymousClass0Vl implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable) {
        return new AnonymousClass0Vn(runnable, AnonymousClass006.A01("RtiExecutor #", this.A00.getAndIncrement()));
    }
}
