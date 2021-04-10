package X;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0wM  reason: invalid class name */
public final class AnonymousClass0wM implements Executor {
    @GuardedBy("this")
    public boolean A00 = false;
    public final Queue<RunnableC08040wT> A01 = new ConcurrentLinkedQueue();
    public final Executor A02;

    public static void A01(AnonymousClass0wM r2) {
        RunnableC08040wT poll;
        synchronized (r2) {
            if (!r2.A00 && (poll = r2.A01.poll()) != null) {
                r2.A00 = true;
                r2.A02.execute(poll);
            }
        }
    }

    public static AnonymousClass0wM A00() {
        if (C08000wO.A03 == null) {
            synchronized (C08000wO.class) {
                if (C08000wO.A03 == null) {
                    C08000wO.A03 = new ThreadPoolExecutor(C08000wO.A00, 128, 1, TimeUnit.SECONDS, C08000wO.A01, C08000wO.A02);
                }
            }
        }
        return new AnonymousClass0wM(new AnonymousClass0vL(C08000wO.A03));
    }

    public final void execute(Runnable runnable) {
        this.A01.add(new RunnableC08040wT(this, runnable));
        A01(this);
    }

    public AnonymousClass0wM(AnonymousClass0vL r2) {
        this.A02 = r2.A00;
    }
}
