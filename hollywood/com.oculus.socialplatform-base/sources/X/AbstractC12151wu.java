package X;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1wu  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC12151wu extends AtomicReference<Future<?>> implements AbstractC12271xB, AbstractC12181wx {
    public static final FutureTask<Void> A00;
    public static final FutureTask<Void> A01;
    public static final long serialVersionUID = 1811839108042568751L;
    public final Runnable runnable;
    public Thread runner;

    static {
        Runnable runnable2 = C137220e.A0B;
        A01 = new FutureTask<>(runnable2, null);
        A00 = new FutureTask<>(runnable2, null);
    }

    public AbstractC12151wu(Runnable runnable2) {
        this.runnable = runnable2;
    }

    public final void A00(Future<?> future) {
        Object obj;
        do {
            obj = get();
            if (obj == A01) {
                return;
            }
            if (obj == A00) {
                boolean z = false;
                if (this.runner != Thread.currentThread()) {
                    z = true;
                }
                future.cancel(z);
                return;
            }
        } while (!compareAndSet(obj, future));
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future future = (Future) get();
        if (future != A01 && future != (futureTask = A00) && compareAndSet(future, futureTask) && future != null) {
            boolean z = false;
            if (this.runner != Thread.currentThread()) {
                z = true;
            }
            future.cancel(z);
        }
    }
}
