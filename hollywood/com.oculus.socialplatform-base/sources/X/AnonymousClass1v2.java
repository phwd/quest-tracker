package X;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1v2  reason: invalid class name */
public final class AnonymousClass1v2 implements Executor {
    @GuardedBy("this")
    public boolean A00 = false;
    public final Queue<AnonymousClass1v3> A01 = new ConcurrentLinkedQueue();
    public final Executor A02;

    public static void A00(AnonymousClass1v2 r2) {
        AnonymousClass1v3 poll;
        synchronized (r2) {
            if (!r2.A00 && (poll = r2.A01.poll()) != null) {
                r2.A00 = true;
                r2.A02.execute(poll);
            }
        }
    }

    public final void execute(Runnable runnable) {
        this.A01.add(new AnonymousClass1v3(this, runnable));
        A00(this);
    }

    public AnonymousClass1v2(AnonymousClass1v4 r2) {
        this.A02 = r2.A00;
    }
}
