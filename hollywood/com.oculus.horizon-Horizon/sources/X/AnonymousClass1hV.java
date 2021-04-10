package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

/* renamed from: X.1hV  reason: invalid class name */
public final class AnonymousClass1hV implements AnonymousClass1uO {
    public final Deque<Runnable> A00;
    public final Executor A01;

    @Override // X.AnonymousClass1uO
    public final synchronized void A1C(Runnable runnable) {
        this.A01.execute(runnable);
    }

    @Override // X.AnonymousClass1uO
    public final synchronized void A89(Runnable runnable) {
        this.A00.remove(runnable);
    }

    public AnonymousClass1hV(Executor executor) {
        if (executor != null) {
            this.A01 = executor;
            this.A00 = new ArrayDeque();
            return;
        }
        throw null;
    }
}
