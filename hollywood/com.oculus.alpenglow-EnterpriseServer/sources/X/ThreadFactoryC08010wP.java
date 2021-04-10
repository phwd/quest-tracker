package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0wP  reason: invalid class name and case insensitive filesystem */
public class ThreadFactoryC08010wP implements ThreadFactory {
    public final AtomicInteger A00 = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable) {
        return new C08190wj(runnable, AnonymousClass006.A01("RtiExecutor #", this.A00.getAndIncrement()));
    }
}
