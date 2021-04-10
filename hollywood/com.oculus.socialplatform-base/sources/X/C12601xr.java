package X;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1xr  reason: invalid class name and case insensitive filesystem */
public final class C12601xr extends AbstractC12411xQ implements AbstractC12271xB {
    public final PriorityBlockingQueue<C12621xt> A00 = new PriorityBlockingQueue<>();
    public final AtomicInteger A01 = new AtomicInteger();
    public final AtomicInteger A02 = new AtomicInteger();
    public volatile boolean A03;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A03 = true;
    }

    public static final AbstractC12271xB A00(C12601xr r5, Runnable runnable, long j) {
        if (!r5.A03) {
            C12621xt r2 = new C12621xt(runnable, Long.valueOf(j), r5.A01.incrementAndGet());
            PriorityBlockingQueue<C12621xt> priorityBlockingQueue = r5.A00;
            priorityBlockingQueue.add(r2);
            AtomicInteger atomicInteger = r5.A02;
            if (atomicInteger.getAndIncrement() != 0) {
                return new AnonymousClass1XS(new RunnableC12651xw(r5, r2));
            }
            int i = 1;
            while (true) {
                if (r5.A03) {
                    priorityBlockingQueue.clear();
                    break;
                }
                C12621xt poll = priorityBlockingQueue.poll();
                if (poll == null) {
                    i = atomicInteger.addAndGet(-i);
                    if (i == 0) {
                        break;
                    }
                } else if (!poll.A03) {
                    poll.A02.run();
                }
            }
        }
        return AnonymousClass1z1.INSTANCE;
    }
}
