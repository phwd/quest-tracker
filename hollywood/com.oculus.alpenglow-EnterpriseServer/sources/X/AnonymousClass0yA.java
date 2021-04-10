package X;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: X.0yA  reason: invalid class name */
public abstract class AnonymousClass0yA<V> extends AnonymousClass10D implements Future<V> {
    public final Future<V> A00() {
        if (!(this instanceof RunnableC08760xm)) {
            return ((RunnableC08770xn) this).A00;
        }
        return ((RunnableC08760xm) this).A00;
    }

    public final boolean cancel(boolean z) {
        if (!(this instanceof RunnableC08760xm)) {
            return A00().cancel(z);
        }
        RunnableC08760xm<V> r5 = (RunnableC08760xm) this;
        ExecutorServiceC08580xU r4 = r5.A01;
        synchronized (r4) {
            PriorityQueue<AnonymousClass0yV<?>> priorityQueue = r4.A02;
            Iterator<AnonymousClass0yV<?>> it = priorityQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnonymousClass0yV<?> next = it.next();
                if (next.A01 == r5) {
                    priorityQueue.remove(next);
                    ExecutorServiceC08580xU.A01(r4);
                    break;
                }
            }
        }
        return r5.A00.cancel(z);
    }

    public final boolean isCancelled() {
        return A00().isCancelled();
    }

    public final boolean isDone() {
        return A00().isDone();
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        return A00().get();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return A00().get(j, timeUnit);
    }
}
