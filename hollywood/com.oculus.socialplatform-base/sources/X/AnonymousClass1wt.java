package X;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1wt  reason: invalid class name */
public final class AnonymousClass1wt implements Callable<Void>, AbstractC12271xB {
    public static final FutureTask<Void> A05 = new FutureTask<>(C137220e.A0B, null);
    public Thread A00;
    public final Runnable A01;
    public final ExecutorService A02;
    public final AtomicReference<Future<?>> A03 = new AtomicReference<>();
    public final AtomicReference<Future<?>> A04 = new AtomicReference<>();

    public final void A00(Future<?> future) {
        AtomicReference<Future<?>> atomicReference;
        Future<?> future2;
        do {
            atomicReference = this.A03;
            future2 = atomicReference.get();
            if (future2 == A05) {
                boolean z = false;
                if (this.A00 != Thread.currentThread()) {
                    z = true;
                }
                future.cancel(z);
                return;
            }
        } while (!atomicReference.compareAndSet(future2, future));
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        AtomicReference<Future<?>> atomicReference = this.A03;
        FutureTask<Void> futureTask = A05;
        Future<?> andSet = atomicReference.getAndSet(futureTask);
        boolean z = true;
        if (!(andSet == null || andSet == futureTask)) {
            boolean z2 = false;
            if (this.A00 != Thread.currentThread()) {
                z2 = true;
            }
            andSet.cancel(z2);
        }
        Future<?> andSet2 = this.A04.getAndSet(futureTask);
        if (andSet2 != null && andSet2 != futureTask) {
            if (this.A00 == Thread.currentThread()) {
                z = false;
            }
            andSet2.cancel(z);
        }
    }

    public AnonymousClass1wt(Runnable runnable, ExecutorService executorService) {
        this.A01 = runnable;
        this.A02 = executorService;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Void call() throws Exception {
        Thread currentThread = Thread.currentThread();
        this.A00 = currentThread;
        try {
            this.A01.run();
            Future<?> submit = this.A02.submit(this);
            while (true) {
                AtomicReference<Future<?>> atomicReference = this.A04;
                Future<?> future = atomicReference.get();
                if (future != A05) {
                    if (atomicReference.compareAndSet(future, submit)) {
                        break;
                    }
                } else {
                    boolean z = false;
                    if (this.A00 != currentThread) {
                        z = true;
                    }
                    submit.cancel(z);
                }
            }
            this.A00 = null;
            return null;
        } catch (Throwable th) {
            this.A00 = null;
            AnonymousClass1y3.A01(th);
            return null;
        }
    }
}
