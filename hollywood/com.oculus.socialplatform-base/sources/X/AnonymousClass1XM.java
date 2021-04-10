package X;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: X.1XM  reason: invalid class name */
public abstract class AnonymousClass1XM<V> extends AnonymousClass1Hu implements Future<V> {
    public final Future<V> A00() {
        if (!(this instanceof AnonymousClass1X8)) {
            return ((AnonymousClass1X9) this).A00;
        }
        return ((AnonymousClass1X8) this).A00;
    }

    public final boolean cancel(boolean z) {
        if (!(this instanceof AnonymousClass1X8)) {
            return A00().cancel(z);
        }
        AnonymousClass1X8<V> r5 = (AnonymousClass1X8) this;
        AnonymousClass1X5 r4 = r5.A01;
        synchronized (r4) {
            PriorityQueue<AnonymousClass1XC<?>> priorityQueue = r4.A02;
            Iterator<AnonymousClass1XC<?>> it = priorityQueue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnonymousClass1XC<?> next = it.next();
                if (next.A01 == r5) {
                    priorityQueue.remove(next);
                    AnonymousClass1X5.A02(r4);
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
