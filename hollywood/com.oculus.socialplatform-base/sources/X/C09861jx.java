package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

/* renamed from: X.1jx  reason: invalid class name and case insensitive filesystem */
public final class C09861jx implements AbstractC10441mf {
    public final Deque<Runnable> A00;
    public final Executor A01;

    @Override // X.AbstractC10441mf
    public final synchronized void A1N(Runnable runnable) {
        this.A01.execute(runnable);
    }

    @Override // X.AbstractC10441mf
    public final synchronized void A94(Runnable runnable) {
        this.A00.remove(runnable);
    }

    public C09861jx(Executor executor) {
        if (executor != null) {
            this.A01 = executor;
            this.A00 = new ArrayDeque();
            return;
        }
        throw null;
    }
}
