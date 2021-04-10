package X;

import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
/* renamed from: X.08s  reason: invalid class name and case insensitive filesystem */
public class RunnableC002708s<V> extends AnonymousClass0IS<V> implements AbstractScheduledFutureC06380mz<V>, Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledFutureImpl";
    public final AnonymousClass0n6<V> A00;
    public final /* synthetic */ AnonymousClass08r A01;

    @Override // X.AnonymousClass0n9
    public final boolean cancel(boolean z) {
        AnonymousClass08r r4 = this.A01;
        synchronized (r4) {
            PriorityQueue<AnonymousClass0XF<?>> priorityQueue = r4.A02;
            Iterator<AnonymousClass0XF<?>> it = priorityQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnonymousClass0XF<?> next = it.next();
                if (next.A01 == this) {
                    priorityQueue.remove(next);
                    AnonymousClass08r.A01(r4);
                    break;
                }
            }
        }
        return this.A00.cancel(z);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void run() {
        this.A00.run();
    }

    @Override // X.AnonymousClass0X9, X.AnonymousClass0n9
    public final /* bridge */ /* synthetic */ Object A00() {
        return this.A00;
    }

    @Override // X.AnonymousClass0n9
    public final /* bridge */ /* synthetic */ Future A01() {
        return this.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableC002708s(AnonymousClass08r r2, Runnable runnable, V v) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new AnonymousClass0n6<>(runnable, v);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableC002708s(AnonymousClass08r r2, Callable<V> callable) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new AnonymousClass0n6<>(callable);
    }
}
