package X;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xR  reason: invalid class name */
public final class AnonymousClass1xR implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.IoScheduler$CachedWorkerPool";
    public final long A00;
    public final AnonymousClass1xU A01;
    public final ConcurrentLinkedQueue<AnonymousClass1xY> A02;
    public final Future<?> A03;
    public final ScheduledExecutorService A04;
    public final ThreadFactory A05;

    public AnonymousClass1xR(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        long j2;
        ScheduledFuture<?> scheduledFuture;
        if (timeUnit != null) {
            j2 = timeUnit.toNanos(j);
        } else {
            j2 = 0;
        }
        this.A00 = j2;
        this.A02 = new ConcurrentLinkedQueue<>();
        this.A01 = new AnonymousClass1xU();
        this.A05 = threadFactory;
        ScheduledExecutorService scheduledExecutorService = null;
        if (timeUnit != null) {
            scheduledExecutorService = Executors.newScheduledThreadPool(1, C12421xS.A02);
            long j3 = this.A00;
            scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, j3, j3, TimeUnit.NANOSECONDS);
        } else {
            scheduledFuture = null;
        }
        this.A04 = scheduledExecutorService;
        this.A03 = scheduledFuture;
    }

    public final void run() {
        ConcurrentLinkedQueue<AnonymousClass1xY> concurrentLinkedQueue = this.A02;
        if (!concurrentLinkedQueue.isEmpty()) {
            long nanoTime = System.nanoTime();
            Iterator<AnonymousClass1xY> it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                AnonymousClass1xY next = it.next();
                if (next.A00 > nanoTime) {
                    return;
                }
                if (concurrentLinkedQueue.remove(next)) {
                    this.A01.A95(next);
                }
            }
        }
    }
}
