package X;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* renamed from: X.0G1  reason: invalid class name */
public final class AnonymousClass0G1 implements Executor {
    public Runnable A00;
    public final ArrayDeque<Runnable> A01 = new ArrayDeque<>();
    public final Executor A02;

    public final synchronized void A00() {
        Runnable poll = this.A01.poll();
        this.A00 = poll;
        if (poll != null) {
            this.A02.execute(poll);
        }
    }

    public final synchronized void execute(Runnable runnable) {
        this.A01.offer(new AnonymousClass0G0(this, runnable));
        if (this.A00 == null) {
            A00();
        }
    }

    public AnonymousClass0G1(@NonNull Executor executor) {
        this.A02 = executor;
    }
}
