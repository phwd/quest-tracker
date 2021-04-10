package X;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
/* renamed from: X.05z  reason: invalid class name and case insensitive filesystem */
public final class RunnableC006305z extends AnonymousClass0BX<Void> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator$NeverSuccessfulListenableFutureTask";
    public final Runnable A00;

    public final void run() {
        try {
            this.A00.run();
        } catch (Throwable th) {
            setException(th);
            if ((th instanceof RuntimeException) || (th instanceof Error)) {
                throw th;
            }
            throw new RuntimeException(th);
        }
    }

    public RunnableC006305z(Runnable runnable) {
        if (runnable != null) {
            this.A00 = runnable;
            return;
        }
        throw null;
    }
}
