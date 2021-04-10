package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Throwables;

@GwtIncompatible
/* renamed from: X.00b  reason: invalid class name */
public final class AnonymousClass00b extends AnonymousClass06Z<Void> implements Runnable {
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

    public AnonymousClass00b(Runnable runnable) {
        if (runnable != null) {
            this.A00 = runnable;
            return;
        }
        throw null;
    }
}
