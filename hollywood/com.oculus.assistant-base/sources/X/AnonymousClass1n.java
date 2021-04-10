package X;

import com.google.common.base.Throwables;

/* renamed from: X.1n  reason: invalid class name */
public final class AnonymousClass1n extends SH implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator$NeverSuccessfulListenableFutureTask";
    public final Runnable A00;

    public final void run() {
        try {
            this.A00.run();
        } catch (Throwable th) {
            setException(th);
            Throwables.propagate(th);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public AnonymousClass1n(Runnable runnable) {
        if (runnable != null) {
            this.A00 = runnable;
            return;
        }
        throw null;
    }
}
