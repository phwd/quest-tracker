package X;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1xK  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12351xK extends AbstractC12411xQ implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ExecutorScheduler$ExecutorWorker";
    public final AnonymousClass1xU A00 = new AnonymousClass1xU();
    public final C12431xa<Runnable> A01;
    public final Executor A02;
    public final AtomicInteger A03 = new AtomicInteger();
    public volatile boolean A04;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A04) {
            this.A04 = true;
            this.A00.dispose();
            if (this.A03.getAndIncrement() == 0) {
                this.A01.clear();
            }
        }
    }

    public final void run() {
        C12431xa<Runnable> r3 = this.A01;
        int i = 1;
        while (!this.A04) {
            Runnable poll = r3.poll();
            if (poll == null) {
                if (this.A04) {
                    break;
                }
                i = this.A03.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                poll.run();
            }
        }
        r3.clear();
    }

    public RunnableC12351xK(Executor executor) {
        this.A02 = executor;
        this.A01 = new C12431xa<>();
    }
}
